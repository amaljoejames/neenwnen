import React, { Suspense, useRef, useEffect } from 'react';
import { Canvas, useFrame, useThree } from '@react-three/fiber';
import { Stars, Html, useProgress } from '@react-three/drei';
import { EffectComposer, Bloom, Vignette } from '@react-three/postprocessing';
import * as THREE from 'three';
import gsap from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';
import Earth from '../components/planets/Earth';
import ParticleField from '../systems/ParticleField';
import TextReveal from '../components/ui/TextReveal';
import MagneticButton from '../components/ui/MagneticButton';
import ScrollReveal from '../components/ui/ScrollReveal';
import GlassCard from '../components/ui/GlassCard';
import gravityEngine from '../systems/GravityEngine';

gsap.registerPlugin(ScrollTrigger);

function EarthCamera({ scrollRef }) {
  const { camera } = useThree();
  const progress = useRef(0);

  useEffect(() => {
    if (!scrollRef?.current) return;
    const ctx = gsap.context(() => {
      ScrollTrigger.create({
        trigger: scrollRef.current,
        start: 'top top',
        end: 'bottom bottom',
        scrub: 2,
        onUpdate: (self) => { progress.current = self.progress; },
      });
    });
    return () => ctx.revert();
  }, [scrollRef]);

  useFrame(() => {
    const p = progress.current;
    // p=0: Close-up of Earth
    // p=0.3: Pull back
    // p=0.6: Orbit around
    // p=1: Wide shot
    const angle = p * Math.PI * 0.5;
    const dist = THREE.MathUtils.lerp(5, 10, p);
    const targetX = Math.sin(angle) * dist;
    const targetZ = Math.cos(angle) * dist;
    const targetY = THREE.MathUtils.lerp(0, 3, p);

    camera.position.x += (targetX - camera.position.x) * 0.04;
    camera.position.y += (targetY - camera.position.y) * 0.04;
    camera.position.z += (targetZ - camera.position.z) * 0.04;

    camera.position.x += gravityEngine.normalizedMouse.x * 0.5;
    camera.position.y += gravityEngine.normalizedMouse.y * 0.3;

    camera.lookAt(0, 0, 0);
  });

  return null;
}

function Loader() {
  const { progress } = useProgress();
  return <Html center><div style={{ color: 'white', fontSize: 14, opacity: 0.6 }}>Loading Earth... {progress.toFixed(0)}%</div></Html>;
}

export default function EarthPage() {
  const scrollRef = useRef(null);

  useEffect(() => {
    const ctx = gsap.context(() => {
      gsap.to('.earth-hero-text', {
        y: -80, opacity: 0,
        scrollTrigger: { trigger: '.earth-hero', start: 'top top', end: '50% top', scrub: 1 },
      });
      gsap.fromTo('.earth-spec', { y: 60, opacity: 0 }, {
        y: 0, opacity: 1, stagger: 0.1,
        scrollTrigger: { trigger: '.earth-specs', start: 'top 80%', end: 'top 40%', scrub: 1 },
      });
      gsap.fromTo('.earth-feature', { x: -60, opacity: 0 }, {
        x: 0, opacity: 1, stagger: 0.15,
        scrollTrigger: { trigger: '.earth-features', start: 'top 75%', end: 'top 30%', scrub: 1 },
      });
    }, scrollRef);
    return () => ctx.revert();
  }, []);

  return (
    <div ref={scrollRef} className="page-content">
      {/* Fixed 3D Canvas */}
      <div style={{ position: 'fixed', inset: 0, zIndex: 1, pointerEvents: 'none' }}>
        <Canvas camera={{ position: [0, 0, 5], fov: 50 }} dpr={[1, 1.5]} gl={{ antialias: true, alpha: true, powerPreference: 'high-performance' }}>
          <Suspense fallback={<Loader />}>
            <ambientLight intensity={0.1} />
            <directionalLight position={[5, 3, 5]} intensity={1.8} />
            <pointLight position={[-3, -2, 3]} intensity={0.5} color="#1e90ff" />
            <Earth position={[0, 0, 0]} scale={2.8} />
            <ParticleField intensity={0.4} />
            <Stars radius={50} depth={30} count={2000} factor={3} saturation={0} fade speed={0.3} />
            <EarthCamera scrollRef={scrollRef} />
            <EffectComposer>
              <Bloom intensity={0.5} luminanceThreshold={0.5} mipmapBlur />
              <Vignette darkness={0.5} offset={0.3} />
            </EffectComposer>
          </Suspense>
        </Canvas>
      </div>

      {/* Hero */}
      <section className="earth-hero full-viewport" style={{ position: 'relative', zIndex: 2, display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', textAlign: 'center' }}>
        <div className="earth-hero-text" style={{ willChange: 'transform' }}>
          <div className="label" style={{ marginBottom: 'var(--space-4)', color: 'var(--earth-blue)' }}>BLUE MARBLE EDITION</div>
          <h1 className="display-hero">EARTH</h1>
          <p className="body-lg" style={{ maxWidth: 500, marginTop: 'var(--space-6)' }}>
            City lights illuminate the dial as day transitions to night. A living map of civilization.
          </p>
        </div>
      </section>

      {/* Specs — scroll-driven entrance */}
      <section className="earth-specs section" style={{ position: 'relative', zIndex: 2, background: 'linear-gradient(180deg, transparent, rgba(3,0,20,0.95) 20%, rgba(3,0,20,0.98))' }}>
        <div className="container">
          <div style={{ display: 'grid', gridTemplateColumns: 'repeat(4, 1fr)', gap: 'var(--space-6)' }}>
            {[
              { value: '42mm', label: 'Case', detail: 'Grade 5 Titanium' },
              { value: '8K', label: 'Dial Resolution', detail: 'Sapphire Crystal' },
              { value: '72h', label: 'Power Reserve', detail: 'Automatic + Solar' },
              { value: '300m', label: 'Water Resistance', detail: 'Deep Sea Rated' },
            ].map((spec, i) => (
              <div key={i} className="earth-spec" style={{ willChange: 'transform, opacity' }}>
                <GlassCard className="glass" style={{ padding: 'var(--space-6)', textAlign: 'center' }}>
                  <div className="display-md gradient-text" style={{ fontWeight: 700 }}>{spec.value}</div>
                  <div className="body-base" style={{ marginTop: 'var(--space-2)', color: 'var(--star-white)' }}>{spec.label}</div>
                  <div className="body-sm" style={{ marginTop: 'var(--space-1)' }}>{spec.detail}</div>
                </GlassCard>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Features — scroll-driven slide-in */}
      <section className="earth-features section" style={{ position: 'relative', zIndex: 2, background: 'rgba(3,0,20,0.98)' }}>
        <div className="container" style={{ maxWidth: 800 }}>
          <h2 className="display-lg" style={{ textAlign: 'center', marginBottom: 'var(--space-12)' }}>
            <span className="gradient-text">Where science</span> meets craftsmanship
          </h2>
          {[
            { title: 'City Light Dial', desc: 'Micro-LED technology illuminates major city clusters when ambient light drops — a living representation of Earth at night.' },
            { title: 'Orbital Movement™', desc: 'The tourbillon rotates on two axes, mimicking Earth\'s axial tilt and orbital path. Each rotation: 23h 56m.' },
            { title: 'Atmosphere Bezel', desc: 'Gradient sapphire ring transitions from ocean blue to atmospheric white — Earth\'s atmosphere as seen from orbit.' },
          ].map((f, i) => (
            <div key={i} className="earth-feature" style={{ padding: 'var(--space-8)', borderLeft: '2px solid var(--earth-blue)', marginBottom: 'var(--space-8)', willChange: 'transform, opacity' }}>
              <h3 className="display-md" style={{ marginBottom: 'var(--space-3)' }}>{f.title}</h3>
              <p className="body-lg">{f.desc}</p>
            </div>
          ))}
          <div style={{ textAlign: 'center', marginTop: 'var(--space-8)' }}>
            <MagneticButton variant="primary" size="lg">Configure Your Earth Edition</MagneticButton>
          </div>
        </div>
      </section>
    </div>
  );
}
