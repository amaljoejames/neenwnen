import React, { Suspense, useRef, useEffect, useState, useCallback } from 'react';
import { Canvas, useFrame, useThree } from '@react-three/fiber';
import { Environment, Stars, Float, useProgress, Html } from '@react-three/drei';
import { EffectComposer, Bloom, ChromaticAberration, Vignette } from '@react-three/postprocessing';
import { BlendFunction } from 'postprocessing';
import * as THREE from 'three';
import gsap from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';
import Earth from '../components/planets/Earth';
import ParticleField from '../systems/ParticleField';
import TextReveal from '../components/ui/TextReveal';
import MagneticButton from '../components/ui/MagneticButton';
import ScrollReveal from '../components/ui/ScrollReveal';
import GlassCard from '../components/ui/GlassCard';
import { useCursor } from '../systems/CursorSystem';
import { useNavigate } from 'react-router-dom';
import gravityEngine from '../systems/GravityEngine';

gsap.registerPlugin(ScrollTrigger);

// Scroll-driven camera + Earth animation
function ScrollScene({ scrollContainerRef }) {
  const { camera } = useThree();
  const earthRef = useRef();
  const scrollProgress = useRef(0);

  useEffect(() => {
    const el = scrollContainerRef?.current;
    if (!el) return;

    // Scroll-driven animation via GSAP ScrollTrigger
    const ctx = gsap.context(() => {
      // Phase 1: Hero — Earth centered, zoomed in
      ScrollTrigger.create({
        trigger: el,
        start: 'top top',
        end: 'bottom bottom',
        scrub: 1.5, // Heavy scrub = more inertia feel
        onUpdate: (self) => {
          scrollProgress.current = self.progress;
        },
      });
    });

    return () => ctx.revert();
  }, [scrollContainerRef]);

  useFrame((state, delta) => {
    const p = scrollProgress.current;

    // Camera path: follows scroll progress
    // Phase 0-0.2: Hero (looking at Earth close)
    // Phase 0.2-0.5: Pull back to show space
    // Phase 0.5-0.8: Orbit around Earth
    // Phase 0.8-1.0: Final CTA section

    const t = p;

    // Camera position interpolation
    const camX = THREE.MathUtils.lerp(0, -3, Math.min(t * 3, 1)) + Math.sin(t * Math.PI) * 2;
    const camY = THREE.MathUtils.lerp(0, 1.5, Math.min(t * 2, 1)) - t * 0.5;
    const camZ = THREE.MathUtils.lerp(8, 12, Math.min(t * 2.5, 1));

    // Spring the camera (Active Theory weight)
    camera.position.x += (camX - camera.position.x) * 0.05;
    camera.position.y += (camY - camera.position.y) * 0.05;
    camera.position.z += (camZ - camera.position.z) * 0.05;

    // Camera always looks toward Earth center with slight offset
    const lookX = THREE.MathUtils.lerp(2, 0, Math.min(t * 2, 1));
    const lookY = THREE.MathUtils.lerp(-0.3, 0, Math.min(t * 2, 1));
    camera.lookAt(lookX, lookY, 0);

    // Mouse parallax on camera
    camera.position.x += gravityEngine.normalizedMouse.x * 0.3;
    camera.position.y += gravityEngine.normalizedMouse.y * 0.2;
  });

  return null;
}

// Loading indicator inside Canvas
function Loader() {
  const { progress } = useProgress();
  return (
    <Html center>
      <div style={{
        color: 'white',
        fontFamily: 'var(--font-display)',
        fontSize: 14,
        letterSpacing: '0.1em',
        opacity: 0.6,
      }}>
        Loading textures... {progress.toFixed(0)}%
      </div>
    </Html>
  );
}

export default function Home() {
  const navigate = useNavigate();
  const { setCursorState } = useCursor();
  const scrollContainerRef = useRef(null);

  // GSAP scroll-triggered DOM animations
  useEffect(() => {
    const ctx = gsap.context(() => {
      // Hero text parallax
      gsap.to('.hero-title', {
        y: -100,
        opacity: 0,
        scrollTrigger: {
          trigger: '.hero-section',
          start: 'top top',
          end: '60% top',
          scrub: 1,
        },
      });

      gsap.to('.hero-subtitle', {
        y: -60,
        opacity: 0,
        scrollTrigger: {
          trigger: '.hero-section',
          start: '10% top',
          end: '60% top',
          scrub: 1,
        },
      });

      gsap.to('.hero-cta', {
        y: -40,
        opacity: 0,
        scrollTrigger: {
          trigger: '.hero-section',
          start: '15% top',
          end: '50% top',
          scrub: 1,
        },
      });

      // Edition cards staggered entrance
      gsap.fromTo('.edition-card', {
        y: 120,
        opacity: 0,
        scale: 0.9,
        rotateX: 15,
      }, {
        y: 0,
        opacity: 1,
        scale: 1,
        rotateX: 0,
        stagger: 0.15,
        scrollTrigger: {
          trigger: '.editions-section',
          start: 'top 80%',
          end: 'top 30%',
          scrub: 1,
        },
      });

      // Tech stats count-up parallax
      gsap.fromTo('.tech-stat', {
        y: 80,
        opacity: 0,
      }, {
        y: 0,
        opacity: 1,
        stagger: 0.1,
        scrollTrigger: {
          trigger: '.tech-section',
          start: 'top 75%',
          end: 'top 35%',
          scrub: 1,
        },
      });

      // Tech visual orbital rings spin-in
      gsap.fromTo('.tech-rings', {
        scale: 0.3,
        opacity: 0,
        rotation: -180,
      }, {
        scale: 1,
        opacity: 1,
        rotation: 0,
        scrollTrigger: {
          trigger: '.tech-section',
          start: 'top 80%',
          end: 'top 20%',
          scrub: 1.5,
        },
      });
    }, scrollContainerRef);

    return () => ctx.revert();
  }, []);

  return (
    <div ref={scrollContainerRef} className="page-content">
      {/* ── PERSISTENT 3D CANVAS ── */}
      <div style={{
        position: 'fixed',
        inset: 0,
        zIndex: 1,
        pointerEvents: 'none',
      }}>
        <Canvas
          camera={{ position: [0, 0, 8], fov: 45 }}
          dpr={[1, 1.5]}
          gl={{ antialias: true, alpha: true, powerPreference: 'high-performance' }}
          style={{ background: 'transparent' }}
        >
          <Suspense fallback={<Loader />}>
            <ambientLight intensity={0.15} />
            <directionalLight position={[5, 3, 5]} intensity={1.5} color="#ffffff" />
            <pointLight position={[-5, -3, -5]} intensity={0.4} color="#7c3aed" />
            <pointLight position={[3, 2, 3]} intensity={0.2} color="#06b6d4" />

            <Earth position={[2.5, -0.3, 0]} scale={2.5} />
            <ParticleField intensity={0.7} />
            <Stars radius={50} depth={40} count={3000} factor={3} saturation={0} fade speed={0.5} />

            <ScrollScene scrollContainerRef={scrollContainerRef} />

            <EffectComposer>
              <Bloom intensity={0.4} luminanceThreshold={0.6} luminanceSmoothing={0.9} mipmapBlur />
              <ChromaticAberration blendFunction={BlendFunction.NORMAL} offset={[0.0003, 0.0003]} />
              <Vignette darkness={0.5} offset={0.3} />
            </EffectComposer>
          </Suspense>
        </Canvas>
      </div>

      {/* ── HERO SECTION ── */}
      <section className="hero-section full-viewport" style={{ position: 'relative', zIndex: 2 }}>
        <div style={{
          position: 'relative',
          height: '100%',
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          padding: '0 var(--space-8)',
          maxWidth: 800,
        }}>
          <div className="label hero-title" style={{ marginBottom: 'var(--space-4)', willChange: 'transform' }}>
            ENGINEERED FOR THE COSMOS
          </div>

          <h1 className="display-hero gradient-text hero-title" style={{ willChange: 'transform' }}>
            TIME.
          </h1>
          <h1 className="display-hero hero-title" style={{ color: 'var(--star-white)', willChange: 'transform' }}>
            REDEFINED.
          </h1>

          <p className="body-lg hero-subtitle" style={{
            maxWidth: 480,
            marginTop: 'var(--space-8)',
            color: 'var(--star-dim)',
            willChange: 'transform',
          }}>
            Precision instruments inspired by the gravitational mechanics of our solar system. Each timepiece is a universe on your wrist.
          </p>

          <div className="hero-cta" style={{
            marginTop: 'var(--space-10)',
            display: 'flex',
            gap: 'var(--space-4)',
            willChange: 'transform',
          }}>
            <MagneticButton variant="primary" size="lg" onClick={() => navigate('/collection')}>
              Explore Collection
            </MagneticButton>
            <MagneticButton variant="ghost" size="lg" onClick={() => navigate('/explore')}>
              Explore Space
            </MagneticButton>
          </div>
        </div>

        {/* Scroll indicator */}
        <div className="hero-subtitle" style={{
          position: 'absolute',
          bottom: 'var(--space-10)',
          left: '50%',
          transform: 'translateX(-50%)',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          gap: 'var(--space-2)',
          willChange: 'transform',
        }}>
          <span className="body-sm" style={{ letterSpacing: '0.15em', textTransform: 'uppercase', fontSize: '0.7rem' }}>
            Scroll to explore
          </span>
          <div style={{
            width: 20,
            height: 32,
            borderRadius: 10,
            border: '1px solid rgba(255,255,255,0.2)',
            display: 'flex',
            justifyContent: 'center',
            paddingTop: 6,
          }}>
            <div style={{
              width: 3,
              height: 8,
              borderRadius: 2,
              background: 'var(--star-white)',
              animation: 'scrollDot 2s ease-in-out infinite',
            }} />
          </div>
        </div>
      </section>

      {/* ── EDITIONS SECTION — scroll-driven entrance ── */}
      <section className="editions-section section" style={{
        position: 'relative',
        zIndex: 2,
        background: 'linear-gradient(180deg, transparent 0%, rgba(3,0,20,0.95) 15%, rgba(3,0,20,0.98) 100%)',
        paddingTop: 'var(--space-32)',
      }}>
        <div className="container">
          <ScrollReveal>
            <div className="label" style={{ marginBottom: 'var(--space-4)', textAlign: 'center' }}>
              PLANETARY EDITIONS
            </div>
            <h2 className="display-lg" style={{ textAlign: 'center' }}>
              <span className="gradient-text">Three worlds.</span> Three timepieces.
            </h2>
          </ScrollReveal>

          <div style={{
            display: 'grid',
            gridTemplateColumns: 'repeat(3, 1fr)',
            gap: 'var(--space-6)',
            marginTop: 'var(--space-16)',
            perspective: '1000px',
          }}>
            {/* Earth Edition Card */}
            <div className="edition-card" style={{ willChange: 'transform, opacity', transformStyle: 'preserve-3d' }}>
              <GlassCard
                className="glass"
                onClick={() => navigate('/earth')}
                style={{
                  padding: 'var(--space-8)',
                  minHeight: 420,
                  display: 'flex',
                  flexDirection: 'column',
                  justifyContent: 'flex-end',
                  background: 'linear-gradient(180deg, rgba(30,144,255,0.08) 0%, rgba(3,0,20,0.95) 100%)',
                  border: '1px solid rgba(30,144,255,0.12)',
                }}
              >
                <div style={{
                  width: 90,
                  height: 90,
                  borderRadius: '50%',
                  background: 'radial-gradient(circle at 35% 35%, #4da6ff, #0a3060)',
                  marginBottom: 'var(--space-6)',
                  boxShadow: '0 0 40px rgba(30,144,255,0.3), inset 0 -2px 6px rgba(0,0,0,0.5)',
                }} />
                <h3 className="display-md" style={{ marginBottom: 'var(--space-2)' }}>Earth</h3>
                <p className="body-sm">Blue Marble Edition — City lights illuminate as day turns to night.</p>
                <div className="label" style={{ marginTop: 'var(--space-4)', color: 'var(--earth-blue)' }}>EXPLORE →</div>
              </GlassCard>
            </div>

            {/* Uranus Edition Card */}
            <div className="edition-card" style={{ willChange: 'transform, opacity', transformStyle: 'preserve-3d' }}>
              <GlassCard
                className="glass"
                onClick={() => navigate('/uranus')}
                style={{
                  padding: 'var(--space-8)',
                  minHeight: 420,
                  display: 'flex',
                  flexDirection: 'column',
                  justifyContent: 'flex-end',
                  background: 'linear-gradient(180deg, rgba(125,211,252,0.08) 0%, rgba(3,0,20,0.95) 100%)',
                  border: '1px solid rgba(125,211,252,0.12)',
                }}
              >
                <div style={{
                  width: 90,
                  height: 90,
                  borderRadius: '50%',
                  background: 'radial-gradient(circle at 35% 35%, #7dd3fc, #1a5060)',
                  marginBottom: 'var(--space-6)',
                  boxShadow: '0 0 40px rgba(125,211,252,0.3), inset 0 -2px 6px rgba(0,0,0,0.5)',
                  position: 'relative',
                }}>
                  <div style={{
                    position: 'absolute', top: '50%', left: '50%',
                    width: 120, height: 30,
                    border: '1px solid rgba(125,211,252,0.3)', borderRadius: '50%',
                    transform: 'translate(-50%, -50%) rotate(97deg)',
                  }} />
                </div>
                <h3 className="display-md" style={{ marginBottom: 'var(--space-2)' }}>Uranus</h3>
                <p className="body-sm">Ice Crown Edition — 97° axial tilt reflected in asymmetric design.</p>
                <div className="label" style={{ marginTop: 'var(--space-4)', color: 'var(--uranus-ice)' }}>EXPLORE →</div>
              </GlassCard>
            </div>

            {/* Neptune Edition Card */}
            <div className="edition-card" style={{ willChange: 'transform, opacity', transformStyle: 'preserve-3d' }}>
              <GlassCard
                className="glass"
                onClick={() => navigate('/neptune')}
                style={{
                  padding: 'var(--space-8)',
                  minHeight: 420,
                  display: 'flex',
                  flexDirection: 'column',
                  justifyContent: 'flex-end',
                  background: 'linear-gradient(180deg, rgba(59,130,246,0.08) 0%, rgba(3,0,20,0.95) 100%)',
                  border: '1px solid rgba(59,130,246,0.12)',
                }}
              >
                <div style={{
                  width: 90,
                  height: 90,
                  borderRadius: '50%',
                  background: 'radial-gradient(circle at 35% 35%, #3b82f6, #0a1840)',
                  marginBottom: 'var(--space-6)',
                  boxShadow: '0 0 40px rgba(59,130,246,0.3), inset 0 -2px 6px rgba(0,0,0,0.5)',
                }} />
                <h3 className="display-md" style={{ marginBottom: 'var(--space-2)' }}>Neptune</h3>
                <p className="body-sm">Storm Chaser Edition — The Great Dark Spot animates across the dial.</p>
                <div className="label" style={{ marginTop: 'var(--space-4)', color: 'var(--neptune-deep)' }}>EXPLORE →</div>
              </GlassCard>
            </div>
          </div>
        </div>
      </section>

      {/* ── TECHNOLOGY TEASER — scroll-driven parallax ── */}
      <section className="tech-section section" style={{
        position: 'relative',
        zIndex: 2,
        background: 'rgba(3,0,20,0.98)',
      }}>
        <div className="container" style={{
          display: 'grid',
          gridTemplateColumns: '1fr 1fr',
          gap: 'var(--space-16)',
          alignItems: 'center',
        }}>
          <div>
            <ScrollReveal>
              <div className="label" style={{ marginBottom: 'var(--space-4)', color: 'var(--nebula-violet)' }}>
                ENGINEERED PRECISION
              </div>
              <h2 className="display-lg">Gravity-defying mechanics</h2>
            </ScrollReveal>

            <ScrollReveal delay={0.2}>
              <p className="body-lg" style={{ marginTop: 'var(--space-6)', maxWidth: 500 }}>
                Our proprietary Orbital Movement™ suspends the tourbillon in a magnetic field, eliminating 97% of positional error.
              </p>
            </ScrollReveal>

            <div style={{
              display: 'grid',
              gridTemplateColumns: '1fr 1fr',
              gap: 'var(--space-6)',
              marginTop: 'var(--space-10)',
            }}>
              {[
                { value: '312', label: 'Components' },
                { value: '±0.003s', label: 'Daily Variance' },
                { value: '72h', label: 'Power Reserve' },
                { value: '300m', label: 'Water Resistance' },
              ].map((stat, i) => (
                <div key={i} className="tech-stat" style={{ willChange: 'transform, opacity' }}>
                  <div className="display-md gradient-text" style={{ fontWeight: 700 }}>{stat.value}</div>
                  <div className="body-sm" style={{ marginTop: 'var(--space-1)' }}>{stat.label}</div>
                </div>
              ))}
            </div>

            <ScrollReveal delay={0.4}>
              <div style={{ marginTop: 'var(--space-10)' }}>
                <MagneticButton variant="secondary" onClick={() => navigate('/technology')}>
                  Discover the Movement →
                </MagneticButton>
              </div>
            </ScrollReveal>
          </div>

          {/* Animated orbital rings visualization */}
          <div className="tech-rings" style={{
            width: '100%',
            aspectRatio: '1',
            maxWidth: 500,
            borderRadius: 'var(--radius-2xl)',
            background: 'linear-gradient(135deg, rgba(10,10,46,0.8), rgba(3,0,20,0.9))',
            border: '1px solid var(--glass-border)',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            position: 'relative',
            overflow: 'hidden',
            willChange: 'transform, opacity',
          }}>
            {[1, 1.4, 1.8, 2.2].map((s, i) => (
              <div key={i} style={{
                position: 'absolute',
                width: `${s * 120}px`,
                height: `${s * 120}px`,
                borderRadius: '50%',
                border: `1px solid rgba(124, 58, 237, ${0.08 + i * 0.04})`,
                animation: `spin ${10 + i * 5}s ${i % 2 === 0 ? '' : 'reverse'} linear infinite`,
              }}>
                <div style={{
                  position: 'absolute',
                  top: 0,
                  left: '50%',
                  width: 4 + i,
                  height: 4 + i,
                  borderRadius: '50%',
                  background: i % 2 === 0 ? 'var(--nebula-purple)' : 'var(--plasma-cyan)',
                  boxShadow: i % 2 === 0 ? '0 0 12px var(--nebula-glow)' : '0 0 12px var(--plasma-glow)',
                  transform: 'translate(-50%, -50%)',
                }} />
              </div>
            ))}
            <div style={{
              width: 20,
              height: 20,
              borderRadius: '50%',
              background: 'radial-gradient(circle, var(--plasma-cyan), var(--nebula-purple))',
              boxShadow: '0 0 30px var(--plasma-glow), 0 0 60px var(--nebula-glow)',
              zIndex: 5,
            }} />
          </div>
        </div>
      </section>

      {/* ── FOOTER ── */}
      <footer style={{
        position: 'relative',
        zIndex: 2,
        borderTop: '1px solid var(--glass-border)',
        padding: 'var(--space-8) 0',
        background: 'rgba(3,0,20,0.99)',
      }}>
        <div className="container">
          <div style={{
            display: 'flex',
            justifyContent: 'space-between',
            alignItems: 'center',
          }}>
            <div>
              <div style={{
                fontFamily: 'var(--font-display)',
                fontSize: 'var(--text-xl)',
                fontWeight: 700,
                letterSpacing: '0.1em',
                background: 'linear-gradient(135deg, var(--star-white), var(--plasma-cyan))',
                WebkitBackgroundClip: 'text',
                WebkitTextFillColor: 'transparent',
                marginBottom: 'var(--space-2)',
              }}>
                ORBIT
              </div>
              <p className="body-sm">Time. Redefined.</p>
            </div>
            <div className="body-sm">© 2026 Orbit Watch</div>
          </div>
        </div>
      </footer>

      <style>{`
        @keyframes spin {
          from { transform: rotate(0deg); }
          to { transform: rotate(360deg); }
        }
        @keyframes scrollDot {
          0%, 100% { transform: translateY(0); opacity: 1; }
          50% { transform: translateY(8px); opacity: 0.3; }
        }
        @media (max-width: 768px) {
          .editions-section div[style*="grid-template-columns: repeat(3"] {
            grid-template-columns: 1fr !important;
          }
          .tech-section .container {
            grid-template-columns: 1fr !important;
          }
        }
      `}</style>
    </div>
  );
}
