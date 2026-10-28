import React, { useRef, useMemo } from 'react';
import { useFrame, extend } from '@react-three/fiber';
import { shaderMaterial } from '@react-three/drei';
import * as THREE from 'three';
import gravityEngine from '../../systems/GravityEngine';
import { getTexture } from '../../systems/TextureGenerator';

// Neptune surface shader with animated Great Dark Spot
const NeptuneSurfaceMat = shaderMaterial(
  { uTime: 0, uMap: null },
  `varying vec2 vUv; varying vec3 vNormal; varying vec3 vPosition;
   void main() { vUv=uv; vNormal=normalize(normalMatrix*normal); vPosition=(modelViewMatrix*vec4(position,1.0)).xyz; gl_Position=projectionMatrix*modelViewMatrix*vec4(position,1.0); }`,
  `uniform float uTime; uniform sampler2D uMap;
   varying vec2 vUv; varying vec3 vNormal; varying vec3 vPosition;
   float hash(vec2 p){return fract(sin(dot(p,vec2(127.1,311.7)))*43758.5453);}
   float noise(vec2 p){vec2 i=floor(p),f=fract(p);f=f*f*(3.0-2.0*f);return mix(mix(hash(i),hash(i+vec2(1,0)),f.x),mix(hash(i+vec2(0,1)),hash(i+vec2(1,1)),f.x),f.y);}
   float fbm(vec2 p){float f=0.0;f+=0.5*noise(p);p*=2.02;f+=0.25*noise(p);p*=2.03;f+=0.125*noise(p);return f/0.875;}
   void main(){
     vec4 tex=texture2D(uMap,vUv); vec3 col=tex.rgb;
     vec2 sc=vec2(fract(0.35+uTime*0.003),0.6);vec2 d=vUv-sc;if(abs(d.x)>0.5)d.x-=sign(d.x);
     float sd=length(vec2(d.x*3.0,d.y*5.0));float v2=fbm(vec2(d.x*15.0+uTime*0.08,d.y*15.0+sin(uTime*0.04)*2.0));
     float sm=smoothstep(0.5,0.1,sd);col=mix(col,col*0.3,sm*0.6);col=mix(col,vec3(0.1,0.15,0.4),sm*v2*0.3);
     float fr=pow(1.0-abs(dot(vNormal,normalize(-vPosition))),2.5);col=mix(col,vec3(0.3,0.5,1.0),fr*0.25);
     gl_FragColor=vec4(col,1.0);
   }`
);

extend({ NeptuneSurfaceMat });

export default function Neptune({ position = [0, 0, 0], scale = 2.3 }) {
  const planetRef = useRef();
  const materialRef = useRef();
  const groupRef = useRef();
  const tiltTarget = useRef({ x: 0, y: 0 });

  const neptuneMap = useMemo(() => getTexture('neptune'), []);

  useFrame((state, delta) => {
    if (!planetRef.current) return;
    planetRef.current.rotation.y += delta * 0.05;
    if (materialRef.current) materialRef.current.uTime = state.clock.elapsedTime;

    const targetX = gravityEngine.normalizedMouse.y * 0.15;
    const targetY = gravityEngine.normalizedMouse.x * 0.15;
    tiltTarget.current.x += (targetX - tiltTarget.current.x) * 0.03;
    tiltTarget.current.y += (targetY - tiltTarget.current.y) * 0.03;

    if (groupRef.current) {
      groupRef.current.rotation.x = 0.49 + tiltTarget.current.x;
      groupRef.current.rotation.z = tiltTarget.current.y;
    }
  });

  return (
    <group ref={groupRef} position={position} rotation={[0.49, 0, 0]}>
      <mesh ref={planetRef} scale={scale}>
        <sphereGeometry args={[1, 64, 64]} />
        <neptuneSurfaceMat ref={materialRef} uMap={neptuneMap} />
      </mesh>

      {/* Atmosphere */}
      <mesh scale={scale * 1.12}>
        <sphereGeometry args={[1, 64, 64]} />
        <shaderMaterial
          args={[{
            uniforms: { uColor: { value: new THREE.Color('#3b82f6') }, uIntensity: { value: 0.6 } },
            vertexShader: `varying vec3 vN; varying vec3 vP; void main(){ vN=normalize(normalMatrix*normal); vP=(modelViewMatrix*vec4(position,1.0)).xyz; gl_Position=projectionMatrix*modelViewMatrix*vec4(position,1.0); }`,
            fragmentShader: `uniform vec3 uColor; uniform float uIntensity; varying vec3 vN; varying vec3 vP; void main(){ float f=pow(1.0-abs(dot(vN,normalize(-vP))),3.0); gl_FragColor=vec4(uColor,f*uIntensity); }`,
          }]}
          transparent side={THREE.BackSide} depthWrite={false}
        />
      </mesh>

      <TritonMoon parentScale={scale} />
    </group>
  );
}

function TritonMoon({ parentScale }) {
  const ref = useRef();
  useFrame((state) => {
    if (!ref.current) return;
    const t = state.clock.elapsedTime * 0.3;
    ref.current.position.x = Math.cos(t) * parentScale * 2.2;
    ref.current.position.z = Math.sin(t) * parentScale * 2.2;
    ref.current.position.y = Math.sin(t * 0.7) * 0.4;
  });
  return (
    <mesh ref={ref} scale={0.12}>
      <sphereGeometry args={[1, 16, 16]} />
      <meshStandardMaterial color="#aabbcc" roughness={0.8} />
    </mesh>
  );
}
