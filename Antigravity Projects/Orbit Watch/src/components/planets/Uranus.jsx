import React, { useRef, useMemo } from 'react';
import { useFrame } from '@react-three/fiber';
import * as THREE from 'three';
import gravityEngine from '../../systems/GravityEngine';
import { getTexture } from '../../systems/TextureGenerator';

export default function Uranus({ position = [0, 0, 0], scale = 2.2 }) {
  const planetRef = useRef();
  const groupRef = useRef();
  const tiltTarget = useRef({ x: 0, y: 0 });

  const uranusMap = useMemo(() => getTexture('uranus'), []);
  const ringMap = useMemo(() => getTexture('uranusRing'), []);

  useFrame((state, delta) => {
    if (!planetRef.current) return;
    planetRef.current.rotation.y += delta * 0.04;

    const targetX = gravityEngine.normalizedMouse.y * 0.12;
    const targetY = gravityEngine.normalizedMouse.x * 0.12;
    tiltTarget.current.x += (targetX - tiltTarget.current.x) * 0.03;
    tiltTarget.current.y += (targetY - tiltTarget.current.y) * 0.03;

    if (groupRef.current) {
      groupRef.current.rotation.x = 1.69 + tiltTarget.current.x;
      groupRef.current.rotation.z = tiltTarget.current.y;
    }
  });

  return (
    <group ref={groupRef} position={position} rotation={[1.69, 0, 0]}>
      <mesh ref={planetRef} scale={scale}>
        <sphereGeometry args={[1, 64, 64]} />
        <meshStandardMaterial map={uranusMap} roughness={0.7} metalness={0.05} />
      </mesh>

      {/* Atmosphere */}
      <mesh scale={scale * 1.08}>
        <sphereGeometry args={[1, 64, 64]} />
        <shaderMaterial
          args={[{
            uniforms: { uColor: { value: new THREE.Color('#7dd3fc') }, uIntensity: { value: 0.5 } },
            vertexShader: `varying vec3 vN; varying vec3 vP; void main(){ vN=normalize(normalMatrix*normal); vP=(modelViewMatrix*vec4(position,1.0)).xyz; gl_Position=projectionMatrix*modelViewMatrix*vec4(position,1.0); }`,
            fragmentShader: `uniform vec3 uColor; uniform float uIntensity; varying vec3 vN; varying vec3 vP; void main(){ float f=pow(1.0-abs(dot(vN,normalize(-vP))),3.0); gl_FragColor=vec4(uColor,f*uIntensity); }`,
          }]}
          transparent side={THREE.BackSide} depthWrite={false}
        />
      </mesh>

      {/* Ring system */}
      <mesh rotation={[Math.PI / 2, 0, 0]}>
        <ringGeometry args={[scale * 1.4, scale * 2.0, 128]} />
        <meshBasicMaterial map={ringMap} transparent opacity={0.5} side={THREE.DoubleSide} depthWrite={false} />
      </mesh>
    </group>
  );
}
