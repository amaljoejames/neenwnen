import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import TextReveal from '../components/ui/TextReveal';
import ScrollReveal from '../components/ui/ScrollReveal';
import GlassCard from '../components/ui/GlassCard';
import MagneticButton from '../components/ui/MagneticButton';
import { useCursor } from '../systems/CursorSystem';

const WATCH_DATA = {
  'earth-origin': {
    name: 'Earth Origin',
    edition: 'Blue Marble Edition',
    price: '$48,500',
    color: 'var(--earth-blue)',
    gradient: 'linear-gradient(135deg, #0a2040, #1e5c8a)',
    tagline: 'Where civilization meets the cosmos',
    description: 'The Earth Origin captures the essence of our home planet — a delicate blue sphere suspended in the void. The dial features a miniature representation of Earth\'s city lights, illuminated by micro-LEDs that respond to ambient light conditions.',
    specs: [
      { label: 'Case', value: '42mm Grade 5 Titanium' },
      { label: 'Movement', value: 'Orbital Cal. OW-01' },
      { label: 'Power Reserve', value: '72 hours' },
      { label: 'Water Resistance', value: '300 meters' },
      { label: 'Crystal', value: 'Sapphire, AR-coated' },
      { label: 'Strap', value: 'Integrated titanium bracelet' },
      { label: 'Dial', value: 'Multi-layer city lights' },
      { label: 'Weight', value: '89 grams' },
    ],
  },
  'earth-aurora': {
    name: 'Earth Aurora',
    edition: 'Blue Marble Edition',
    price: '$52,000',
    color: 'var(--earth-blue)',
    gradient: 'linear-gradient(135deg, #0a3050, #2dd4bf)',
    tagline: 'Northern lights captured in sapphire',
    description: 'The Aurora variant adds a stunning polar complication — a rotating subdial at 6 o\'clock that replicates the aurora borealis through electroluminescent pigments embedded in the sapphire crystal.',
    specs: [
      { label: 'Case', value: '44mm Grade 5 Titanium + Ceramic' },
      { label: 'Movement', value: 'Orbital Cal. OW-01A' },
      { label: 'Power Reserve', value: '72 hours' },
      { label: 'Water Resistance', value: '200 meters' },
      { label: 'Crystal', value: 'Sapphire with EL layer' },
      { label: 'Strap', value: 'Arctic polymer + titanium' },
      { label: 'Dial', value: 'Aurora electroluminescent' },
      { label: 'Weight', value: '94 grams' },
    ],
  },
  'uranus-frost': {
    name: 'Uranus Frost',
    edition: 'Ice Crown Edition',
    price: '$56,800',
    color: 'var(--uranus-ice)',
    gradient: 'linear-gradient(135deg, #1a3a50, #7dd3fc)',
    tagline: 'Frozen beauty at the edge of reason',
    description: 'The Frost variant embraces the extreme cold of Uranus with a cryo-treated titanium case and an ice-blue sapphire dial. The 97° tilted bezel creates an asymmetric profile that makes this watch impossible to ignore.',
    specs: [
      { label: 'Case', value: '43mm Cryo-Titanium' },
      { label: 'Movement', value: 'Orbital Cal. OW-07' },
      { label: 'Power Reserve', value: '80 hours' },
      { label: 'Water Resistance', value: '300 meters' },
      { label: 'Crystal', value: 'Ice-tint sapphire' },
      { label: 'Strap', value: 'Glacier-blue ceramic' },
      { label: 'Dial', value: '13-ring micro-engraved' },
      { label: 'Weight', value: '86 grams' },
    ],
  },
  'uranus-tilt': {
    name: 'Uranus Tilt 97',
    edition: 'Ice Crown Edition',
    price: '$61,200',
    color: 'var(--uranus-ice)',
    gradient: 'linear-gradient(135deg, #1a4a5a, #5eead4)',
    tagline: 'The only watch that stands sideways',
    description: 'The Tilt 97 is our most radical design — a case physically tilted at 97° with a gyroscopic dial that maintains horizontal even as the case angles. It\'s engineering defiance made wearable.',
    specs: [
      { label: 'Case', value: '45mm Titanium, 97° offset' },
      { label: 'Movement', value: 'Orbital Cal. OW-97G' },
      { label: 'Power Reserve', value: '96 hours' },
      { label: 'Water Resistance', value: '100 meters' },
      { label: 'Crystal', value: 'Domed sapphire, 3.5mm' },
      { label: 'Strap', value: 'Articulated titanium' },
      { label: 'Dial', value: 'Gyroscopic self-leveling' },
      { label: 'Weight', value: '112 grams' },
    ],
  },
  'neptune-storm': {
    name: 'Neptune Storm',
    edition: 'Storm Chaser Edition',
    price: '$64,500',
    color: 'var(--neptune-deep)',
    gradient: 'linear-gradient(135deg, #0a1840, #3b82f6)',
    tagline: 'Supersonic precision in deep blue',
    description: 'The Storm variant channels Neptune\'s violent atmosphere into a timepiece of extraordinary depth. The Great Dark Spot complication rotates across the dial every 16.1 hours, driven by a dedicated micro-motor.',
    specs: [
      { label: 'Case', value: '44mm Blue PVD Titanium' },
      { label: 'Movement', value: 'Orbital Cal. OW-14' },
      { label: 'Power Reserve', value: '84 hours' },
      { label: 'Water Resistance', value: '500 meters' },
      { label: 'Crystal', value: 'Deep-blue sapphire' },
      { label: 'Strap', value: 'Cobalt-blue ceramic' },
      { label: 'Dial', value: 'Great Dark Spot rotating' },
      { label: 'Weight', value: '98 grams' },
    ],
  },
  'neptune-abyss': {
    name: 'Neptune Abyss',
    edition: 'Storm Chaser Edition',
    price: '$72,000',
    color: 'var(--neptune-deep)',
    gradient: 'linear-gradient(135deg, #050a30, #1d4ed8)',
    tagline: 'Into the deepest blue',
    description: 'Our flagship Neptune — the Abyss pushes every limit. A 1000m depth rating, a retrograde Triton moonphase, and a dial so dark it seems to absorb light itself. This is the watch for those who explore where others won\'t go.',
    specs: [
      { label: 'Case', value: '46mm Ceramic + Titanium' },
      { label: 'Movement', value: 'Orbital Cal. OW-14X' },
      { label: 'Power Reserve', value: '120 hours' },
      { label: 'Water Resistance', value: '1000 meters' },
      { label: 'Crystal', value: 'Anti-reflective sapphire' },
      { label: 'Strap', value: 'Deep-sea rubber + Ti clasp' },
      { label: 'Dial', value: 'Vantablack + storm comp.' },
      { label: 'Weight', value: '118 grams' },
    ],
  },
};

export default function WatchDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { setCursorState } = useCursor();
  const watch = WATCH_DATA[id];

  if (!watch) {
    return (
      <div className="page-content" style={{ paddingTop: 120, textAlign: 'center', minHeight: '80vh', display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
        <h1 className="display-lg">Watch not found</h1>
        <MagneticButton variant="primary" onClick={() => navigate('/collection')} style={{ marginTop: 'var(--space-8)' }}>
          Back to Collection
        </MagneticButton>
      </div>
    );
  }

  return (
    <div className="page-content" style={{ paddingTop: 100 }}>
      {/* Hero */}
      <section className="section">
        <div className="container" style={{
          display: 'grid',
          gridTemplateColumns: '1fr 1fr',
          gap: 'var(--space-16)',
          alignItems: 'center',
          minHeight: '70vh',
        }}>
          {/* Watch Visual */}
          <ScrollReveal direction="left">
            <div style={{
              width: '100%',
              aspectRatio: '1',
              maxWidth: 500,
              borderRadius: 'var(--radius-2xl)',
              background: watch.gradient,
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              position: 'relative',
              overflow: 'hidden',
            }}>
              {/* Large watch face */}
              <div style={{
                width: '60%',
                aspectRatio: '1',
                borderRadius: '50%',
                background: 'rgba(0,0,0,0.4)',
                border: '3px solid rgba(255,255,255,0.12)',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                backdropFilter: 'blur(20px)',
                boxShadow: `0 0 60px ${watch.color}33, inset 0 0 30px rgba(0,0,0,0.3)`,
                position: 'relative',
              }}>
                {/* Hour markers */}
                {Array.from({ length: 12 }, (_, i) => {
                  const angle = (i / 12) * Math.PI * 2 - Math.PI / 2;
                  return (
                    <div key={i} style={{
                      position: 'absolute',
                      width: i % 3 === 0 ? 3 : 1,
                      height: i % 3 === 0 ? 14 : 8,
                      background: i % 3 === 0 ? 'var(--star-white)' : 'rgba(255,255,255,0.3)',
                      left: `${50 + Math.cos(angle) * 42}%`,
                      top: `${50 + Math.sin(angle) * 42}%`,
                      transform: `translate(-50%, -50%) rotate(${i * 30}deg)`,
                      borderRadius: 1,
                    }} />
                  );
                })}

                {/* Watch hands */}
                <div style={{
                  position: 'absolute',
                  width: 2,
                  height: '30%',
                  background: 'var(--star-white)',
                  bottom: '50%',
                  left: '50%',
                  transform: 'translateX(-50%) rotate(-30deg)',
                  transformOrigin: 'bottom center',
                  borderRadius: 2,
                }} />
                <div style={{
                  position: 'absolute',
                  width: 1.5,
                  height: '22%',
                  background: watch.color,
                  bottom: '50%',
                  left: '50%',
                  transform: 'translateX(-50%) rotate(75deg)',
                  transformOrigin: 'bottom center',
                  borderRadius: 2,
                }} />
                <div style={{
                  position: 'absolute',
                  width: 0.5,
                  height: '35%',
                  background: 'var(--plasma-cyan)',
                  bottom: '50%',
                  left: '50%',
                  transform: 'translateX(-50%) rotate(180deg)',
                  transformOrigin: 'bottom center',
                }} />

                {/* Center dot */}
                <div style={{
                  width: 8,
                  height: 8,
                  borderRadius: '50%',
                  background: 'var(--star-white)',
                  border: '2px solid rgba(0,0,0,0.5)',
                  zIndex: 5,
                }} />

                {/* Brand name */}
                <div style={{
                  position: 'absolute',
                  top: '28%',
                  fontFamily: 'var(--font-display)',
                  fontSize: 8,
                  fontWeight: 700,
                  letterSpacing: '0.2em',
                  color: 'rgba(255,255,255,0.4)',
                }}>
                  ORBIT
                </div>
              </div>

              {/* Crown */}
              <div style={{
                position: 'absolute',
                right: -6,
                top: '50%',
                transform: 'translateY(-50%)',
                width: 12,
                height: 30,
                background: 'rgba(255,255,255,0.15)',
                borderRadius: '0 4px 4px 0',
                border: '1px solid rgba(255,255,255,0.1)',
              }} />

              {/* Orbital ring decoration */}
              <div style={{
                position: 'absolute',
                width: '110%',
                height: '110%',
                borderRadius: '50%',
                border: '1px solid rgba(255,255,255,0.04)',
                animation: 'spin 20s linear infinite',
              }}>
                <div style={{
                  position: 'absolute',
                  top: 0,
                  left: '50%',
                  width: 6,
                  height: 6,
                  borderRadius: '50%',
                  background: watch.color,
                  transform: 'translate(-50%, -50%)',
                  boxShadow: `0 0 10px ${watch.color}`,
                }} />
              </div>
            </div>
          </ScrollReveal>

          {/* Watch Info */}
          <div>
            <ScrollReveal>
              <div className="label" style={{ color: watch.color, marginBottom: 'var(--space-3)' }}>
                {watch.edition}
              </div>
              <TextReveal as="h1" className="display-xl" animation="spring-up" type="words">
                {watch.name}
              </TextReveal>
            </ScrollReveal>

            <ScrollReveal delay={0.2}>
              <p className="body-lg" style={{
                marginTop: 'var(--space-4)',
                fontStyle: 'italic',
                color: 'var(--star-dim)',
              }}>
                "{watch.tagline}"
              </p>
            </ScrollReveal>

            <ScrollReveal delay={0.3}>
              <p className="body-base" style={{ marginTop: 'var(--space-6)', maxWidth: 500 }}>
                {watch.description}
              </p>
            </ScrollReveal>

            <ScrollReveal delay={0.4}>
              <div style={{
                display: 'flex',
                alignItems: 'baseline',
                gap: 'var(--space-4)',
                marginTop: 'var(--space-8)',
              }}>
                <span className="display-md gradient-text" style={{ fontWeight: 700 }}>
                  {watch.price}
                </span>
                <span className="body-sm">Starting price</span>
              </div>
            </ScrollReveal>

            <ScrollReveal delay={0.5}>
              <div style={{
                display: 'flex',
                gap: 'var(--space-4)',
                marginTop: 'var(--space-8)',
              }}>
                <MagneticButton variant="primary" size="lg">
                  Configure & Order
                </MagneticButton>
                <MagneticButton variant="ghost" size="lg">
                  Book Viewing
                </MagneticButton>
              </div>
            </ScrollReveal>
          </div>
        </div>
      </section>

      {/* Specifications */}
      <section className="section" style={{ background: 'var(--void-deep)' }}>
        <div className="container">
          <ScrollReveal>
            <TextReveal as="h2" className="display-lg" animation="blur-in" type="words" style={{ textAlign: 'center', justifyContent: 'center', marginBottom: 'var(--space-12)' }}>
              Technical Specifications
            </TextReveal>
          </ScrollReveal>

          <div style={{
            display: 'grid',
            gridTemplateColumns: 'repeat(2, 1fr)',
            gap: 'var(--space-4)',
            maxWidth: 700,
            margin: '0 auto',
          }}>
            {watch.specs.map((spec, i) => (
              <ScrollReveal key={i} delay={i * 0.05}>
                <div style={{
                  display: 'flex',
                  justifyContent: 'space-between',
                  padding: 'var(--space-4)',
                  borderBottom: '1px solid var(--glass-border)',
                }}>
                  <span className="body-sm">{spec.label}</span>
                  <span className="body-base" style={{ fontWeight: 500, color: 'var(--star-white)' }}>{spec.value}</span>
                </div>
              </ScrollReveal>
            ))}
          </div>
        </div>
      </section>

      <style>{`
        @keyframes spin {
          from { transform: rotate(0deg); }
          to { transform: rotate(360deg); }
        }
        @media (max-width: 768px) {
          .container[style*="grid-template-columns: 1fr 1fr"] {
            grid-template-columns: 1fr !important;
          }
        }
      `}</style>
    </div>
  );
}
