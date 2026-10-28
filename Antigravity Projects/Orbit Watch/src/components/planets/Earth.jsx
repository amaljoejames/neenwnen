import React, { useRef, useMemo } from 'react';
import { useFrame } from '@react-three/fiber';
import * as THREE from 'three';
import gravityEngine from '../../systems/GravityEngine';
import { getTexture } from '../../systems/TextureGenerator';

// Atmosphere Fresnel shader
const AtmosphereShader = {
  vertexShader: `
    varying vec3 vNormal;
    varying vec3 vPosition;
    void main() {
      vNormal = normalize(normalMatrix * normal);
      vPosition = (modelViewMatrix * vec4(position, 1.0)).xyz;
      gl_Position = projectionMatrix * modelViewMatrix * vec4(position, 1.0);
    }
  `,
  fragmentShader: `
    uniform vec3 uColor;
    uniform float uIntensity;
    varying vec3 vNormal;
    varying vec3 vPosition;
    void main() {
      float fresnel = pow(1.0 - abs(dot(vNormal, normalize(-vPosition))), 3.0);
      gl_FragColor = vec4(uColor, fresnel * uIntensity);
    }
  `,
};

export default function Earth({ position = [0, 0, 0], scale = 2.5, autoRotate = true }) {
  const earthRef = useRef();
  const groupRef = useRef();
  const tiltTarget = useRef({ x: 0, y: 0 });

  const dayMap = useMemo(() => getTexture('earth'), []);
  const nightMap = useMemo(() => getTexture('earthNight'), []);

  useFrame((state, delta) => {
    if (!earthRef.current) return;

    if (autoRotate) {
      earthRef.current.rotation.y += delta * 0.08;
    }

    const targetTiltX = gravityEngine.normalizedMouse.y * 0.2;
    const targetTiltY = gravityEngine.normalizedMouse.x * 0.2;
    tiltTarget.current.x += (targetTiltX - tiltTarget.current.x) * 0.03;
    tiltTarget.current.y += (targetTiltY - tiltTarget.current.y) * 0.03;

    if (groupRef.current) {
      groupRef.current.rotation.x = tiltTarget.current.x + 0.4;
      groupRef.current.rotation.z = -tiltTarget.current.y * 0.3;
    }
  });

  return (
    <group ref={groupRef} position={position}>
      <mesh ref={earthRef} scale={scale}>
        <sphereGeometry args={[1, 64, 64]} />
        <meshPhongMaterial
          map={dayMap}
          emissiveMap={nightMap}
          emissive={new THREE.Color('#ffcc66')}
          emissiveIntensity={1.5}
          shininess={15}
          bumpMap={dayMap}
          bumpScale={0.01}
        />
      </mesh>

      {/* Atmosphere glow */}
      <mesh scale={scale * 1.12}>
        <sphereGeometry args={[1, 64, 64]} />
        <shaderMaterial
          args={[{
            uniforms: {
              uColor: { value: new THREE.Color('#4da6ff') },
              uIntensity: { value: 0.7 },
            },
            vertexShader: AtmosphereShader.vertexShader,
            fragmentShader: AtmosphereShader.fragmentShader,
          }]}
          transparent
          side={THREE.BackSide}
          depthWrite={false}
        />
      </mesh>
    </group>
  );
}
