import React from 'react';
import TextReveal from '../components/ui/TextReveal';
import ScrollReveal from '../components/ui/ScrollReveal';
import GlassCard from '../components/ui/GlassCard';
import MagneticButton from '../components/ui/MagneticButton';
import { useNavigate } from 'react-router-dom';

export default function Technology() {
  const navigate = useNavigate();

  return (
    <div className="page-content" style={{ paddingTop: 120 }}>
      {/* Hero */}
      <section className="section" style={{ minHeight: '60vh', display: 'flex', alignItems: 'center' }}>
        <div className="container" style={{ textAlign: 'center' }}>
          <ScrollReveal>
            <div className="label" style={{ marginBottom: 'var(--space-4)' }}>ENGINEERED PRECISION</div>
            <TextReveal as="h1" className="display-hero gradient-text" animation="spring-up" type="chars" stagger={0.02}>
              TECHNOLOGY
            </TextReveal>
          </ScrollReveal>
          <ScrollReveal delay={0.5}>
            <p className="body-lg" style={{ maxWidth: 600, margin: 'var(--space-6) auto 0' }}>
              Every Orbit timepiece is governed by our proprietary Orbital Movement™ — a revolutionary caliber that defies conventional watchmaking.
            </p>
          </ScrollReveal>
        </div>
      </section>

      {/* Movement Section */}
      <section className="section" style={{ background: 'var(--void-deep)' }}>
        <div className="container">
          <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: 'var(--space-16)', alignItems: 'center' }}>
            <div>
              <ScrollReveal>
                <div className="label" style={{ marginBottom: 'var(--space-4)', color: 'var(--nebula-violet)' }}>THE ORBITAL MOVEMENT™</div>
                <h2 className="display-lg" style={{ marginBottom: 'var(--space-6)' }}>Gravity as a feature, not a flaw</h2>
              </ScrollReveal>
              <ScrollReveal delay={0.2}>
                <p className="body-lg" style={{ marginBottom: 'var(--space-6)' }}>
                  Traditional tourbillons fight gravity. Ours embraces it. The Orbital Movement™ uses a magnetically-suspended escapement that floats in a vacuum chamber, eliminating friction and achieving chronometric precision previously thought impossible.
                </p>
                <p className="body-base" style={{ color: 'var(--star-muted)' }}>
                  The result: ±0.003 seconds daily variance — ten times more accurate than COSC certification standards.
                </p>
              </ScrollReveal>
            </div>

            <ScrollReveal direction="left">
              <div style={{
                width: '100%',
                aspectRatio: '1',
                borderRadius: 'var(--radius-2xl)',
                background: 'linear-gradient(135deg, var(--space-dark), var(--void-black))',
                border: '1px solid var(--glass-border)',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                position: 'relative',
                overflow: 'hidden',
              }}>
                {/* Exploded movement visualization */}
                {[0, 1, 2, 3, 4].map((layer, i) => (
                  <div key={i} style={{
                    position: 'absolute',
                    width: `${60 - i * 8}%`,
                    aspectRatio: '1',
                    borderRadius: '50%',
                    border: `1px solid rgba(124, 58, 237, ${0.15 + i * 0.05})`,
                    animation: `spin ${12 + i * 6}s ${i % 2 === 0 ? 'normal' : 'reverse'} linear infinite`,
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                  }}>
                    {i < 3 && (
                      <>
                        <div style={{
                          position: 'absolute',
                          top: 0,
                          left: '50%',
                          width: 3 + i,
                          height: 3 + i,
                          borderRadius: '50%',
                          background: 'var(--nebula-purple)',
                          transform: 'translate(-50%, -50%)',
                          boxShadow: '0 0 8px var(--nebula-glow)',
                        }} />
                        <div style={{
                          position: 'absolute',
                          bottom: 0,
                          left: '50%',
                          width: 2 + i,
                          height: 2 + i,
                          borderRadius: '50%',
                          background: 'var(--plasma-cyan)',
                          transform: 'translate(-50%, 50%)',
                          boxShadow: '0 0 6px var(--plasma-glow)',
                        }} />
                      </>
                    )}
                  </div>
                ))}

                {/* Center jewel */}
                <div style={{
                  width: 16,
                  height: 16,
                  borderRadius: '50%',
                  background: 'radial-gradient(circle, var(--plasma-cyan), var(--nebula-purple))',
                  boxShadow: '0 0 30px var(--plasma-glow), 0 0 60px var(--nebula-glow)',
                  zIndex: 5,
                }} />
              </div>
            </ScrollReveal>
          </div>
        </div>
      </section>

      {/* Key Technologies */}
      <section className="section">
        <div className="container">
          <ScrollReveal>
            <TextReveal as="h2" className="display-lg" animation="blur-in" type="words" style={{ textAlign: 'center', justifyContent: 'center', marginBottom: 'var(--space-12)' }}>
              Core Innovations
            </TextReveal>
          </ScrollReveal>

          <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: 'var(--space-6)' }}>
            {[
              {
                title: 'Magnetic Levitation',
                description: 'The balance wheel floats in a vacuum chamber via rare-earth magnets, eliminating mechanical friction entirely.',
                stat: '0%',
                statLabel: 'Friction',
                icon: '◎',
              },
              {
                title: 'Photon Harvesting',
                description: 'A solar panel integrated beneath the sapphire crystal continuously charges the micro-motor, providing infinite power reserve in daylight.',
                stat: '∞',
                statLabel: 'Solar Reserve',
                icon: '☀',
              },
              {
                title: 'Anti-magnetic Shield',
                description: 'A Faraday cage of mu-metal protects the movement from magnetic fields up to 40,000 A/m — 25 times the ISO 764 standard.',
                stat: '40K',
                statLabel: 'A/m Resistance',
                icon: '⊘',
              },
              {
                title: 'Vacuum Chamber',
                description: 'The entire escapement operates in a near-vacuum, eliminating air resistance and reducing positional error by 97%.',
                stat: '97%',
                statLabel: 'Error Reduction',
                icon: '◯',
              },
              {
                title: 'Neural Timecode',
                description: 'An embedded GPS + atomic clock receiver syncs to NIST timecodes every 24 hours, ensuring perpetual accuracy without manual setting.',
                stat: 'GPS',
                statLabel: 'Atomic Sync',
                icon: '⊛',
              },
              {
                title: 'Hyper-alloy Case',
                description: 'Grade 5 titanium alloyed with niobium creates a case that is 40% lighter than steel while maintaining superior hardness and corrosion resistance.',
                stat: '-40%',
                statLabel: 'Weight vs Steel',
                icon: '⬡',
              },
            ].map((tech, i) => (
              <ScrollReveal key={i} delay={i * 0.1}>
                <GlassCard className="glass" style={{ padding: 'var(--space-8)', height: '100%' }}>
                  <div style={{
                    fontSize: 'var(--text-3xl)',
                    marginBottom: 'var(--space-4)',
                    opacity: 0.6,
                  }}>
                    {tech.icon}
                  </div>
                  <h3 style={{
                    fontFamily: 'var(--font-display)',
                    fontSize: 'var(--text-xl)',
                    fontWeight: 600,
                    marginBottom: 'var(--space-3)',
                  }}>
                    {tech.title}
                  </h3>
                  <p className="body-sm" style={{ marginBottom: 'var(--space-6)' }}>
                    {tech.description}
                  </p>
                  <div style={{ marginTop: 'auto' }}>
                    <div className="display-md gradient-text" style={{ fontWeight: 700 }}>{tech.stat}</div>
                    <div className="body-sm">{tech.statLabel}</div>
                  </div>
                </GlassCard>
              </ScrollReveal>
            ))}
          </div>
        </div>
      </section>

      {/* CTA */}
      <section className="section" style={{ textAlign: 'center' }}>
        <div className="container">
          <ScrollReveal>
            <h2 className="display-lg" style={{ marginBottom: 'var(--space-6)' }}>Ready to experience the movement?</h2>
            <p className="body-lg" style={{ maxWidth: 500, margin: '0 auto var(--space-10)' }}>
              Visit an Orbit atelier to see the Orbital Movement™ in action, or explore our collection online.
            </p>
            <div style={{ display: 'flex', gap: 'var(--space-4)', justifyContent: 'center' }}>
              <MagneticButton variant="primary" size="lg" onClick={() => navigate('/collection')}>
                View Collection
              </MagneticButton>
              <MagneticButton variant="ghost" size="lg" onClick={() => navigate('/contact')}>
                Book an Atelier Visit
              </MagneticButton>
            </div>
          </ScrollReveal>
        </div>
      </section>

      <style>{`
        @keyframes spin {
          from { transform: rotate(0deg); }
          to { transform: rotate(360deg); }
        }
        @media (max-width: 768px) {
          .container > div[style*="grid-template-columns: 1fr 1fr"],
          .container > div[style*="grid-template-columns: repeat(3"] {
            grid-template-columns: 1fr !important;
          }
        }
      `}</style>
    </div>
  );
}
