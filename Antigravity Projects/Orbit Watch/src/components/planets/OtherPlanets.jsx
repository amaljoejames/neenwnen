import React, { useRef, useMemo } from 'react';
import { useFrame } from '@react-three/fiber';
import * as THREE from 'three';
import { getTexture } from '../../systems/TextureGenerator';

export function Mars({ position, scale = 0.5 }) {
  const ref = useRef();
  const map = useMemo(() => getTexture('mars'), []);
  useFrame((_, d) => { if (ref.current) ref.current.rotation.y += d * 0.06; });
  return <mesh ref={ref} position={position} scale={scale}><sphereGeometry args={[1,48,48]} /><meshStandardMaterial map={map} roughness={0.9} /></mesh>;
}

export function Jupiter({ position, scale = 1.2 }) {
  const ref = useRef();
  const map = useMemo(() => getTexture('jupiter'), []);
  useFrame((_, d) => { if (ref.current) ref.current.rotation.y += d * 0.04; });
  return <mesh ref={ref} position={position} scale={scale}><sphereGeometry args={[1,64,64]} /><meshStandardMaterial map={map} roughness={0.8} /></mesh>;
}

export function Saturn({ position, scale = 1.0 }) {
  const ref = useRef();
  const map = useMemo(() => getTexture('saturn'), []);
  const ringTex = useMemo(() => getTexture('saturnRing'), []);
  useFrame((_, d) => { if (ref.current) ref.current.rotation.y += d * 0.03; });
  return (
    <group position={position} rotation={[0.47, 0, 0]}>
      <mesh ref={ref} scale={scale}><sphereGeometry args={[1,64,64]} /><meshStandardMaterial map={map} roughness={0.7} /></mesh>
      <mesh rotation={[Math.PI/2, 0, 0]}>
        <ringGeometry args={[scale*1.3, scale*2.2, 128]} />
        <meshBasicMaterial map={ringTex} transparent opacity={0.8} side={THREE.DoubleSide} depthWrite={false} />
      </mesh>
    </group>
  );
}

export function Venus({ position, scale = 0.6 }) {
  const ref = useRef();
  const map = useMemo(() => getTexture('venus'), []);
  useFrame((_, d) => { if (ref.current) ref.current.rotation.y += d * 0.02; });
  return <mesh ref={ref} position={position} scale={scale}><sphereGeometry args={[1,48,48]} /><meshStandardMaterial map={map} roughness={0.9} /></mesh>;
}

export function Mercury({ position, scale = 0.35 }) {
  const ref = useRef();
  const map = useMemo(() => getTexture('mercury'), []);
  useFrame((_, d) => { if (ref.current) ref.current.rotation.y += d * 0.03; });
  return <mesh ref={ref} position={position} scale={scale}><sphereGeometry args={[1,32,32]} /><meshStandardMaterial map={map} roughness={0.95} /></mesh>;
}
