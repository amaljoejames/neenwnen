import React, { Suspense, useRef, useEffect } from 'react';
import { Canvas, useFrame, useThree } from '@react-three/fiber';
import { Stars, Html, useProgress } from '@react-three/drei';
import { EffectComposer, Bloom, Vignette } from '@react-three/postprocessing';
import * as THREE from 'three';
import gsap from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';
import Uranus from '../components/planets/Uranus';
import ParticleField from '../systems/ParticleField';
import MagneticButton from '../components/ui/MagneticButton';
import GlassCard from '../components/ui/GlassCard';
import gravityEngine from '../systems/GravityEngine';

gsap.registerPlugin(ScrollTrigger);

function UranusCamera({ scrollRef }) {
  const { camera } = useThree();
  const progress = useRef(0);
  useEffect(() => {
    if (!scrollRef?.current) return;
    const ctx = gsap.context(() => {
      ScrollTrigger.create({ trigger: scrollRef.current, start: 'top top', end: 'bottom bottom', scrub: 2,
        onUpdate: (self) => { progress.current = self.progress; },
      });
    });
    return () => ctx.revert();
  }, [scrollRef]);

  useFrame(() => {
    const p = progress.current;
    const angle = p * Math.PI * 0.6;
    const dist = THREE.MathUtils.lerp(6, 11, p);
    const tx = Math.sin(angle) * dist;
    const tz = Math.cos(angle) * dist;
    const ty = THREE.MathUtils.lerp(-1, 4, p);
    camera.position.x += (tx - camera.position.x) * 0.04;
    camera.position.y += (ty - camera.position.y) * 0.04;
    camera.position.z += (tz - camera.position.z) * 0.04;
    camera.position.x += gravityEngine.normalizedMouse.x * 0.5;
    camera.position.y += gravityEngine.normalizedMouse.y * 0.3;
    camera.lookAt(0, 0, 0);
  });
  return null;
}

function Loader() {
  const { progress } = useProgress();
  return <Html center><div style={{ color: 'white', fontSize: 14, opacity: 0.6 }}>Loading Uranus... {progress.toFixed(0)}%</div></Html>;
}

export default function UranusPage() {
  const scrollRef = useRef(null);

  useEffect(() => {
    const ctx = gsap.context(() => {
      gsap.to('.uranus-hero-text', { y: -80, opacity: 0, scrollTrigger: { trigger: '.uranus-hero', start: 'top top', end: '50% top', scrub: 1 } });
      gsap.fromTo('.uranus-spec', { y: 60, opacity: 0 }, { y: 0, opacity: 1, stagger: 0.1, scrollTrigger: { trigger: '.uranus-specs', start: 'top 80%', end: 'top 40%', scrub: 1 } });
      gsap.fromTo('.uranus-feature', { x: 60, opacity: 0 }, { x: 0, opacity: 1, stagger: 0.15, scrollTrigger: { trigger: '.uranus-features', start: 'top 75%', end: 'top 30%', scrub: 1 } });
    }, scrollRef);
    return () => ctx.revert();
  }, []);

  return (
    <div ref={scrollRef} className="page-content">
      <div style={{ position: 'fixed', inset: 0, zIndex: 1, pointerEvents: 'none' }}>
        <Canvas camera={{ position: [0, 0, 6], fov: 45 }} dpr={[1, 1.5]} gl={{ antialias: true, alpha: true, powerPreference: 'high-performance' }}>
          <Suspense fallback={<Loader />}>
            <ambientLight intensity={0.15} />
            <directionalLight position={[5, 3, 5]} intensity={1.2} />
            <pointLight position={[-3, 2, -3]} intensity={0.5} color="#7dd3fc" />
            <Uranus position={[0, 0, 0]} scale={2.4} />
            <ParticleField intensity={0.35} />
            <Stars radius={50} depth={30} count={2000} factor={3} saturation={0} fade speed={0.3} />
            <UranusCamera scrollRef={scrollRef} />
            <EffectComposer><Bloom intensity={0.5} luminanceThreshold={0.5} mipmapBlur /><Vignette darkness={0.5} offset={0.3} /></EffectComposer>
          </Suspense>
        </Canvas>
      </div>

      <section className="uranus-hero full-viewport" style={{ position: 'relative', zIndex: 2, display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', textAlign: 'center' }}>
        <div className="uranus-hero-text" style={{ willChange: 'transform' }}>
          <div className="label" style={{ marginBottom: 'var(--space-4)', color: 'var(--uranus-ice)' }}>ICE CROWN EDITION</div>
          <h1 className="display-hero">URANUS</h1>
          <p className="body-lg" style={{ maxWidth: 500, marginTop: 'var(--space-6)' }}>The only planet that rotates on its side. This asymmetry defines every element.</p>
        </div>
      </section>

      <section className="uranus-specs section" style={{ position: 'relative', zIndex: 2, background: 'linear-gradient(180deg, transparent, rgba(3,0,20,0.95) 20%, rgba(3,0,20,0.98))' }}>
        <div className="container">
          <div style={{ display: 'grid', gridTemplateColumns: 'repeat(4, 1fr)', gap: 'var(--space-6)' }}>
            {[
              { value: '97°', label: 'Axial Tilt', detail: 'Reflected in bezel angle' },
              { value: '-224°C', label: 'Surface Temp', detail: 'Ice-blue sapphire dial' },
              { value: '13', label: 'Known Rings', detail: 'Visible on dial edge' },
              { value: '27', label: 'Known Moons', detail: 'Hour markers' },
            ].map((spec, i) => (
              <div key={i} className="uranus-spec" style={{ willChange: 'transform, opacity' }}>
                <GlassCard className="glass" style={{ padding: 'var(--space-6)', textAlign: 'center' }}>
                  <div className="display-md" style={{ fontWeight: 700, color: 'var(--uranus-ice)' }}>{spec.value}</div>
                  <div className="body-base" style={{ marginTop: 'var(--space-2)', color: 'var(--star-white)' }}>{spec.label}</div>
                  <div className="body-sm" style={{ marginTop: 'var(--space-1)' }}>{spec.detail}</div>
                </GlassCard>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section className="uranus-features section" style={{ position: 'relative', zIndex: 2, background: 'rgba(3,0,20,0.98)' }}>
        <div className="container" style={{ maxWidth: 800 }}>
          <h2 className="display-lg" style={{ textAlign: 'center', marginBottom: 'var(--space-12)' }}><span style={{ color: 'var(--uranus-ice)' }}>Designed for</span> asymmetric beauty</h2>
          {[
            { title: 'Tilted Bezel Ring', desc: 'The outer bezel is angled at exactly 97° — a direct geometric reference to Uranus\'s extreme axial tilt.' },
            { title: 'Cryo-Sapphire Crystal', desc: 'Ice-blue tint that shifts hue based on viewing angle, mimicking the methane-rich atmosphere.' },
            { title: 'Ring Complications', desc: 'Thirteen concentric micro-engraved rings represent each of Uranus\'s known rings.' },
          ].map((f, i) => (
            <div key={i} className="uranus-feature" style={{ padding: 'var(--space-8)', borderLeft: '2px solid var(--uranus-ice)', marginBottom: 'var(--space-8)', willChange: 'transform, opacity' }}>
              <h3 className="display-md" style={{ marginBottom: 'var(--space-3)' }}>{f.title}</h3>
              <p className="body-lg">{f.desc}</p>
            </div>
          ))}
          <div style={{ textAlign: 'center', marginTop: 'var(--space-8)' }}>
            <MagneticButton variant="primary" size="lg">Configure Your Ice Crown Edition</MagneticButton>
          </div>
        </div>
      </section>
    </div>
  );
}
