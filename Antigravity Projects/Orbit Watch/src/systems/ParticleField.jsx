import React, { useRef, useMemo, useEffect } from 'react';
import { useFrame, useThree } from '@react-three/fiber';
import * as THREE from 'three';
import gravityEngine from './GravityEngine';

const PARTICLE_COUNT = 2000;
const GRAVITY_STRENGTH = 0.00015;
const SOFTENING = 0.5;
const DAMPING = 0.97;
const BOUNDS = 12;
const CURSOR_RADIUS = 3;

export default function ParticleField({ intensity = 1.0 }) {
  const meshRef = useRef();
  const { viewport } = useThree();

  // Initialize particle data
  const { positions, velocities, colors, sizes, basePositions } = useMemo(() => {
    const positions = new Float32Array(PARTICLE_COUNT * 3);
    const velocities = new Float32Array(PARTICLE_COUNT * 3);
    const colors = new Float32Array(PARTICLE_COUNT * 3);
    const sizes = new Float32Array(PARTICLE_COUNT);
    const basePositions = new Float32Array(PARTICLE_COUNT * 3);

    const nebulaColor = new THREE.Color('#7c3aed');
    const plasmaColor = new THREE.Color('#06b6d4');
    const starColor = new THREE.Color('#f0f0ff');

    for (let i = 0; i < PARTICLE_COUNT; i++) {
      const i3 = i * 3;

      // Distribute in a sphere-ish volume
      const theta = Math.random() * Math.PI * 2;
      const phi = Math.acos(2 * Math.random() - 1);
      const r = Math.pow(Math.random(), 0.6) * BOUNDS;

      positions[i3] = r * Math.sin(phi) * Math.cos(theta);
      positions[i3 + 1] = r * Math.sin(phi) * Math.sin(theta);
      positions[i3 + 2] = (Math.random() - 0.5) * 8;

      basePositions[i3] = positions[i3];
      basePositions[i3 + 1] = positions[i3 + 1];
      basePositions[i3 + 2] = positions[i3 + 2];

      velocities[i3] = 0;
      velocities[i3 + 1] = 0;
      velocities[i3 + 2] = 0;

      // Color: mix between nebula purple, plasma cyan, and starlight white
      const colorMix = Math.random();
      const color = new THREE.Color();
      if (colorMix < 0.3) {
        color.copy(nebulaColor);
      } else if (colorMix < 0.6) {
        color.copy(plasmaColor);
      } else {
        color.copy(starColor);
      }
      color.multiplyScalar(0.6 + Math.random() * 0.4);

      colors[i3] = color.r;
      colors[i3 + 1] = color.g;
      colors[i3 + 2] = color.b;

      sizes[i] = (0.5 + Math.random() * 2.0) * intensity;
    }

    return { positions, velocities, colors, sizes, basePositions };
  }, [intensity]);

  // Shader material for particles
  const shaderArgs = useMemo(() => ({
    uniforms: {
      uTime: { value: 0 },
      uPixelRatio: { value: Math.min(window.devicePixelRatio, 2) },
    },
    vertexShader: `
      attribute float aSize;
      attribute vec3 aColor;
      varying vec3 vColor;
      varying float vAlpha;
      uniform float uTime;
      uniform float uPixelRatio;
      
      void main() {
        vColor = aColor;
        
        vec4 mvPosition = modelViewMatrix * vec4(position, 1.0);
        
        // Depth-based alpha fade
        float depth = -mvPosition.z;
        vAlpha = smoothstep(12.0, 0.0, depth) * 0.8;
        
        // Size attenuation
        gl_PointSize = aSize * uPixelRatio * (300.0 / -mvPosition.z);
        gl_PointSize = max(gl_PointSize, 0.5);
        
        gl_Position = projectionMatrix * mvPosition;
      }
    `,
    fragmentShader: `
      varying vec3 vColor;
      varying float vAlpha;
      
      void main() {
        // Soft circular particle
        float dist = length(gl_PointCoord - vec2(0.5));
        if (dist > 0.5) discard;
        
        float alpha = smoothstep(0.5, 0.0, dist) * vAlpha;
        
        // Core glow
        float core = smoothstep(0.3, 0.0, dist);
        vec3 finalColor = mix(vColor, vec3(1.0), core * 0.5);
        
        gl_FragColor = vec4(finalColor, alpha);
      }
    `,
    transparent: true,
    depthWrite: false,
    blending: THREE.AdditiveBlending,
  }), []);

  useFrame((state, delta) => {
    if (!meshRef.current) return;

    const geometry = meshRef.current.geometry;
    const posAttr = geometry.attributes.position;
    const pos = posAttr.array;

    // Convert cursor from screen to 3D space
    const cursorX = gravityEngine.normalizedMouse.x * viewport.width * 0.5;
    const cursorY = gravityEngine.normalizedMouse.y * viewport.height * 0.5;

    const time = state.clock.elapsedTime;
    shaderArgs.uniforms.uTime.value = time;

    for (let i = 0; i < PARTICLE_COUNT; i++) {
      const i3 = i * 3;

      // Distance to cursor (2D, ignoring Z)
      const dx = pos[i3] - cursorX;
      const dy = pos[i3 + 1] - cursorY;
      const dist = Math.sqrt(dx * dx + dy * dy);

      if (dist < CURSOR_RADIUS && gravityEngine.isMoving) {
        // Gravity well: inverse-square attraction
        const force = GRAVITY_STRENGTH / (dist * dist + SOFTENING);
        velocities[i3] += -dx * force;
        velocities[i3 + 1] += -dy * force;
      }

      // Return force to base position (spring)
      const bx = basePositions[i3] - pos[i3];
      const by = basePositions[i3 + 1] - pos[i3 + 1];
      const bz = basePositions[i3 + 2] - pos[i3 + 2];
      velocities[i3] += bx * 0.001;
      velocities[i3 + 1] += by * 0.001;
      velocities[i3 + 2] += bz * 0.001;

      // Brownian drift
      velocities[i3] += (Math.random() - 0.5) * 0.0005;
      velocities[i3 + 1] += (Math.random() - 0.5) * 0.0005;
      velocities[i3 + 2] += (Math.random() - 0.5) * 0.0003;

      // Damping
      velocities[i3] *= DAMPING;
      velocities[i3 + 1] *= DAMPING;
      velocities[i3 + 2] *= DAMPING;

      // Update position
      pos[i3] += velocities[i3];
      pos[i3 + 1] += velocities[i3 + 1];
      pos[i3 + 2] += velocities[i3 + 2];
    }

    posAttr.needsUpdate = true;
  });

  return (
    <points ref={meshRef}>
      <bufferGeometry>
        <bufferAttribute
          attach="attributes-position"
          args={[positions, 3]}
          count={PARTICLE_COUNT}
          usage={THREE.DynamicDrawUsage}
        />
        <bufferAttribute
          attach="attributes-aSize"
          args={[sizes, 1]}
          count={PARTICLE_COUNT}
        />
        <bufferAttribute
          attach="attributes-aColor"
          args={[colors, 3]}
          count={PARTICLE_COUNT}
        />
      </bufferGeometry>
      <shaderMaterial args={[shaderArgs]} />
    </points>
  );
}
