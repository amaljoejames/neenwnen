import React, { Suspense, useRef, useEffect } from 'react';
import { Canvas, useFrame, useThree } from '@react-three/fiber';
import { Stars, Html, useProgress, Float } from '@react-three/drei';
import { EffectComposer, Bloom, Vignette } from '@react-three/postprocessing';
import * as THREE from 'three';
import gsap from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';
import Sun from '../components/planets/Sun';
import Earth from '../components/planets/Earth';
import Uranus from '../components/planets/Uranus';
import Neptune from '../components/planets/Neptune';
import { Mercury, Venus, Mars, Jupiter, Saturn } from '../components/planets/OtherPlanets';
import ParticleField from '../systems/ParticleField';
import TextReveal from '../components/ui/TextReveal';
import ScrollReveal from '../components/ui/ScrollReveal';
import MagneticButton from '../components/ui/MagneticButton';
import gravityEngine from '../systems/GravityEngine';
import { useNavigate } from 'react-router-dom';

gsap.registerPlugin(ScrollTrigger);

// Orbit rings (visual guides)
function OrbitRing({ radius, opacity = 0.04 }) {
  return (
    <mesh rotation={[Math.PI / 2, 0, 0]}>
      <ringGeometry args={[radius - 0.02, radius + 0.02, 256]} />
      <meshBasicMaterial color="white" transparent opacity={opacity} side={THREE.DoubleSide} />
    </mesh>
  );
}

// Scroll-driven camera flythrough the solar system
function SolarSystemCamera({ scrollContainerRef }) {
  const { camera } = useThree();
  const progress = useRef(0);
  const cameraTarget = useRef(new THREE.Vector3(0, 5, 25));
  const lookTarget = useRef(new THREE.Vector3(0, 0, 0));

  useEffect(() => {
    const el = scrollContainerRef?.current;
    if (!el) return;

    const ctx = gsap.context(() => {
      ScrollTrigger.create({
        trigger: el,
        start: 'top top',
        end: 'bottom bottom',
        scrub: 2,
        onUpdate: (self) => {
          progress.current = self.progress;
        },
      });
    });
    return () => ctx.revert();
  }, [scrollContainerRef]);

  useFrame(() => {
    const p = progress.current;

    // Camera path through solar system — controlled by scroll
    // p=0: Wide overview
    // p=0.15: Zoom to Sun
    // p=0.25: Mercury/Venus
    // p=0.4: Earth
    // p=0.55: Mars/Jupiter
    // p=0.7: Saturn
    // p=0.82: Uranus
    // p=0.95: Neptune

    let targetPos, targetLook;

    if (p < 0.1) {
      // Overview
      const t = p / 0.1;
      targetPos = new THREE.Vector3(0, 20 - t * 10, 45 - t * 20);
      targetLook = new THREE.Vector3(0, 0, 0);
    } else if (p < 0.2) {
      // Sun approach
      const t = (p - 0.1) / 0.1;
      targetPos = new THREE.Vector3(-5 * t, 10 - t * 6, 25 - t * 15);
      targetLook = new THREE.Vector3(0, 0, 0);
    } else if (p < 0.35) {
      // Inner planets → Earth
      const t = (p - 0.2) / 0.15;
      targetPos = new THREE.Vector3(-3 + t * 8, 4 - t * 2, 10 + t * 2);
      targetLook = new THREE.Vector3(5 + t * 3, 0, 0);
    } else if (p < 0.5) {
      // Earth close-up
      const t = (p - 0.35) / 0.15;
      targetPos = new THREE.Vector3(5 + t * 2, 2 - t, 12 - t * 3);
      targetLook = new THREE.Vector3(8, 0, 0);
    } else if (p < 0.65) {
      // Mars → Jupiter
      const t = (p - 0.5) / 0.15;
      targetPos = new THREE.Vector3(7 + t * 8, 2 + t * 3, 9 + t * 5);
      targetLook = new THREE.Vector3(12 + t * 8, 0, 0);
    } else if (p < 0.75) {
      // Saturn
      const t = (p - 0.65) / 0.1;
      targetPos = new THREE.Vector3(15 + t * 8, 5, 14 - t * 2);
      targetLook = new THREE.Vector3(26, 0, 0);
    } else if (p < 0.88) {
      // Uranus
      const t = (p - 0.75) / 0.13;
      targetPos = new THREE.Vector3(23 + t * 10, 4 - t, 12 + t * 2);
      targetLook = new THREE.Vector3(35, 0, 0);
    } else {
      // Neptune
      const t = (p - 0.88) / 0.12;
      targetPos = new THREE.Vector3(33 + t * 8, 3, 14 - t * 2);
      targetLook = new THREE.Vector3(44, 0, 0);
    }

    // Spring physics camera (heavy inertia)
    cameraTarget.current.lerp(targetPos, 0.04);
    lookTarget.current.lerp(targetLook, 0.04);

    camera.position.copy(cameraTarget.current);

    // Mouse parallax
    camera.position.x += gravityEngine.normalizedMouse.x * 1.5;
    camera.position.y += gravityEngine.normalizedMouse.y * 1.0;

    camera.lookAt(lookTarget.current);
  });

  return null;
}

function Loader() {
  const { progress } = useProgress();
  return <Html center><div style={{ color: 'white', fontFamily: 'Inter', fontSize: 14, opacity: 0.6 }}>Loading solar system... {progress.toFixed(0)}%</div></Html>;
}

export default function Explore() {
  const navigate = useNavigate();
  const scrollRef = useRef(null);

  // GSAP scroll-triggered DOM text animations
  useEffect(() => {
    const ctx = gsap.context(() => {
      // Each planet info panel fades in/out as you scroll past
      document.querySelectorAll('.planet-info').forEach((el, i) => {
        gsap.fromTo(el, {
          opacity: 0,
          x: i % 2 === 0 ? -80 : 80,
        }, {
          opacity: 1,
          x: 0,
          scrollTrigger: {
            trigger: el,
            start: 'top 80%',
            end: 'top 40%',
            scrub: 1,
          },
        });

        gsap.to(el, {
          opacity: 0,
          y: -40,
          scrollTrigger: {
            trigger: el,
            start: 'bottom 40%',
            end: 'bottom 10%',
            scrub: 1,
          },
        });
      });
    }, scrollRef);
    return () => ctx.revert();
  }, []);

  const planets = [
    { name: 'The Sun', subtitle: 'Our Star', desc: 'A G-type main-sequence star, 4.6 billion years old. Surface temperature: 5,778 K.', color: '#ffaa33' },
    { name: 'Mercury', subtitle: 'The Swift Planet', desc: 'Smallest planet. Orbits the Sun in just 88 Earth days. No atmosphere.', color: '#888888' },
    { name: 'Venus', subtitle: 'Morning Star', desc: 'Earth\'s twin in size. Runaway greenhouse effect. Surface pressure: 90 atm.', color: '#dda055' },
    { name: 'Earth', subtitle: 'Blue Marble Edition', desc: 'Our home. The only known planet with life. City lights visible from orbit.', color: 'var(--earth-blue)', route: '/earth' },
    { name: 'Mars', subtitle: 'The Red Planet', desc: 'Home to Olympus Mons, the largest volcano. Thin CO₂ atmosphere.', color: '#cc4422' },
    { name: 'Jupiter', subtitle: 'Gas Giant', desc: 'The largest planet. The Great Red Spot has raged for over 400 years.', color: '#cc9966' },
    { name: 'Saturn', subtitle: 'The Ringed World', desc: 'Iconic ring system of ice and rock. Could float on water (density 0.687).', color: '#ddbb77' },
    { name: 'Uranus', subtitle: 'Ice Crown Edition', desc: 'Tilted 97° — it rolls through its orbit. Methane gives it the ice-blue color.', color: 'var(--uranus-ice)', route: '/uranus' },
    { name: 'Neptune', subtitle: 'Storm Chaser Edition', desc: 'Supersonic winds at 2,100 km/h. The Great Dark Spot drifts across deep blue.', color: 'var(--neptune-deep)', route: '/neptune' },
  ];

  return (
    <div ref={scrollRef} className="page-content">
      {/* Fixed 3D Canvas — solar system */}
      <div style={{
        position: 'fixed',
        inset: 0,
        zIndex: 1,
        pointerEvents: 'none',
      }}>
        <Canvas
          camera={{ position: [0, 20, 45], fov: 50 }}
          dpr={[1, 1.5]}
          gl={{ antialias: true, alpha: true, powerPreference: 'high-performance' }}
        >
          <Suspense fallback={<Loader />}>
            <ambientLight intensity={0.08} />
            <Stars radius={80} depth={50} count={4000} factor={4} saturation={0} fade speed={0.3} />

            {/* The Solar System — all real NASA textures */}
            <Sun position={[0, 0, 0]} scale={2.0} />
            <Mercury position={[4, 0, 0]} scale={0.25} />
            <Venus position={[5.5, 0, 0]} scale={0.45} />
            <Earth position={[8, 0, 0]} scale={0.5} />
            <Mars position={[11, 0, 0]} scale={0.35} />
            <Jupiter position={[17, 0, 0]} scale={1.4} />
            <Saturn position={[26, 0, 0]} scale={1.1} />
            <Uranus position={[35, 0, 0]} scale={0.7} />
            <Neptune position={[44, 0, 0]} scale={0.65} />

            {/* Orbit rings */}
            {[4, 5.5, 8, 11, 17, 26, 35, 44].map((r, i) => (
              <OrbitRing key={i} radius={r} opacity={0.03} />
            ))}

            <ParticleField intensity={0.3} />

            <SolarSystemCamera scrollContainerRef={scrollRef} />

            <EffectComposer>
              <Bloom intensity={0.7} luminanceThreshold={0.3} mipmapBlur />
              <Vignette darkness={0.6} offset={0.3} />
            </EffectComposer>
          </Suspense>
        </Canvas>
      </div>

      {/* Scroll-driven text panels — each takes 100vh */}
      <div style={{ position: 'relative', zIndex: 2, pointerEvents: 'none' }}>
        {planets.map((planet, i) => (
          <section
            key={i}
            style={{
              height: '100vh',
              display: 'flex',
              alignItems: i % 2 === 0 ? 'flex-start' : 'flex-end',
              justifyContent: 'center',
              flexDirection: 'column',
              padding: '0 var(--space-8)',
              maxWidth: 500,
              marginLeft: i % 2 === 0 ? 'var(--space-8)' : 'auto',
              marginRight: i % 2 === 0 ? 'auto' : 'var(--space-8)',
            }}
          >
            <div className="planet-info" style={{
              pointerEvents: 'auto',
              padding: 'var(--space-8)',
              background: 'rgba(3,0,20,0.7)',
              backdropFilter: 'blur(20px)',
              WebkitBackdropFilter: 'blur(20px)',
              border: '1px solid rgba(255,255,255,0.06)',
              borderRadius: 'var(--radius-xl)',
              willChange: 'transform, opacity',
            }}>
              <div className="label" style={{ color: planet.color, marginBottom: 'var(--space-2)' }}>
                {planet.subtitle}
              </div>
              <h2 className="display-lg" style={{ marginBottom: 'var(--space-3)' }}>
                {planet.name}
              </h2>
              <p className="body-base" style={{ marginBottom: planet.route ? 'var(--space-6)' : 0 }}>
                {planet.desc}
              </p>
              {planet.route && (
                <MagneticButton variant="primary" size="md" onClick={() => navigate(planet.route)}>
                  View {planet.name} Edition
                </MagneticButton>
              )}
            </div>
          </section>
        ))}
      </div>
    </div>
  );
}
