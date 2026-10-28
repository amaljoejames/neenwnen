import React, { useRef, useMemo } from 'react';
import { useFrame } from '@react-three/fiber';
import * as THREE from 'three';
import gravityEngine from '../../systems/GravityEngine';
import { getTexture } from '../../systems/TextureGenerator';

export default function Sun({ position = [0, 0, 0], scale = 1.5 }) {
  const meshRef = useRef();
  const sunMap = useMemo(() => getTexture('sun'), []);

  useFrame((state, delta) => {
    if (meshRef.current) meshRef.current.rotation.y += delta * 0.02;
  });

  return (
    <group position={position}>
      <mesh ref={meshRef} scale={scale}>
        <sphereGeometry args={[1, 48, 48]} />
        <meshBasicMaterial map={sunMap} />
      </mesh>

      {/* Sun glow */}
      <mesh scale={scale * 1.4}>
        <sphereGeometry args={[1, 32, 32]} />
        <shaderMaterial
          args={[{
            uniforms: { uColor: { value: new THREE.Color('#ffaa33') }, uIntensity: { value: 1.0 } },
            vertexShader: `varying vec3 vNormal; varying vec3 vPosition; void main() { vNormal = normalize(normalMatrix * normal); vPosition = (modelViewMatrix * vec4(position, 1.0)).xyz; gl_Position = projectionMatrix * modelViewMatrix * vec4(position, 1.0); }`,
            fragmentShader: `uniform vec3 uColor; uniform float uIntensity; varying vec3 vNormal; varying vec3 vPosition; void main() { float f = pow(1.0 - abs(dot(vNormal, normalize(-vPosition))), 2.0); gl_FragColor = vec4(uColor, f * uIntensity * 0.6); }`,
          }]}
          transparent side={THREE.BackSide} depthWrite={false}
        />
      </mesh>

      <pointLight intensity={3} color="#ffcc66" distance={50} decay={1} />
    </group>
  );
}
