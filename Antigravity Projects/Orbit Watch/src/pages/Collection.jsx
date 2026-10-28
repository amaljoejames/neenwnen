import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import TextReveal from '../components/ui/TextReveal';
import ScrollReveal from '../components/ui/ScrollReveal';
import GlassCard from '../components/ui/GlassCard';
import { useCursor } from '../systems/CursorSystem';

const WATCHES = [
  {
    id: 'earth-origin',
    name: 'Earth Origin',
    edition: 'Blue Marble',
    price: '$48,500',
    color: 'var(--earth-blue)',
    gradient: 'linear-gradient(135deg, #0a2040, #1e5c8a)',
    planet: 'earth',
  },
  {
    id: 'earth-aurora',
    name: 'Earth Aurora',
    edition: 'Blue Marble',
    price: '$52,000',
    color: 'var(--earth-blue)',
    gradient: 'linear-gradient(135deg, #0a3050, #2dd4bf)',
    planet: 'earth',
  },
  {
    id: 'uranus-frost',
    name: 'Uranus Frost',
    edition: 'Ice Crown',
    price: '$56,800',
    color: 'var(--uranus-ice)',
    gradient: 'linear-gradient(135deg, #1a3a50, #7dd3fc)',
    planet: 'uranus',
  },
  {
    id: 'uranus-tilt',
    name: 'Uranus Tilt 97',
    edition: 'Ice Crown',
    price: '$61,200',
    color: 'var(--uranus-ice)',
    gradient: 'linear-gradient(135deg, #1a4a5a, #5eead4)',
    planet: 'uranus',
  },
  {
    id: 'neptune-storm',
    name: 'Neptune Storm',
    edition: 'Storm Chaser',
    price: '$64,500',
    color: 'var(--neptune-deep)',
    gradient: 'linear-gradient(135deg, #0a1840, #3b82f6)',
    planet: 'neptune',
  },
  {
    id: 'neptune-abyss',
    name: 'Neptune Abyss',
    edition: 'Storm Chaser',
    price: '$72,000',
    color: 'var(--neptune-deep)',
    gradient: 'linear-gradient(135deg, #050a30, #1d4ed8)',
    planet: 'neptune',
  },
];

const FILTERS = ['All', 'Earth', 'Uranus', 'Neptune'];

export default function Collection() {
  const navigate = useNavigate();
  const { setCursorState } = useCursor();
  const [activeFilter, setActiveFilter] = useState('All');

  const filtered = activeFilter === 'All'
    ? WATCHES
    : WATCHES.filter(w => w.planet === activeFilter.toLowerCase());

  return (
    <div className="page-content" style={{ paddingTop: 120 }}>
      <section className="section">
        <div className="container">
          <ScrollReveal>
            <div className="label" style={{ marginBottom: 'var(--space-4)' }}>THE COLLECTION</div>
            <TextReveal as="h1" className="display-xl gradient-text" animation="spring-up" type="words">
              Every second, cosmic.
            </TextReveal>
          </ScrollReveal>

          <ScrollReveal delay={0.3}>
            <p className="body-lg" style={{ maxWidth: 600, marginTop: 'var(--space-6)' }}>
              Six timepieces. Three worlds. Each one a gravitational statement engineered to capture the essence of our solar system's most extraordinary planets.
            </p>
          </ScrollReveal>

          {/* Filter Bar */}
          <ScrollReveal delay={0.4}>
            <div style={{
              display: 'flex',
              gap: 'var(--space-2)',
              marginTop: 'var(--space-12)',
              padding: 'var(--space-1)',
              background: 'var(--glass-bg)',
              borderRadius: 'var(--radius-full)',
              border: '1px solid var(--glass-border)',
              width: 'fit-content',
            }}>
              {FILTERS.map(filter => (
                <button
                  key={filter}
                  onClick={() => setActiveFilter(filter)}
                  onMouseEnter={() => setCursorState('hover')}
                  onMouseLeave={() => setCursorState('default')}
                  style={{
                    padding: 'var(--space-2) var(--space-6)',
                    borderRadius: 'var(--radius-full)',
                    fontSize: 'var(--text-sm)',
                    fontWeight: 500,
                    letterSpacing: '0.05em',
                    textTransform: 'uppercase',
                    background: activeFilter === filter
                      ? 'rgba(255,255,255,0.1)'
                      : 'transparent',
                    color: activeFilter === filter
                      ? 'var(--star-white)'
                      : 'var(--star-muted)',
                    transition: 'all 0.3s var(--ease-out)',
                  }}
                >
                  {filter}
                </button>
              ))}
            </div>
          </ScrollReveal>

          {/* Watch Grid */}
          <div style={{
            display: 'grid',
            gridTemplateColumns: 'repeat(3, 1fr)',
            gap: 'var(--space-8)',
            marginTop: 'var(--space-10)',
          }}>
            {filtered.map((watch, i) => (
              <ScrollReveal key={watch.id} delay={i * 0.1}>
                <GlassCard
                  className="glass"
                  onClick={() => navigate(`/watch/${watch.id}`)}
                  style={{
                    padding: 0,
                    overflow: 'hidden',
                    cursor: 'none',
                  }}
                >
                  {/* Watch Image Placeholder */}
                  <div style={{
                    width: '100%',
                    height: 320,
                    background: watch.gradient,
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    position: 'relative',
                    overflow: 'hidden',
                  }}>
                    {/* Watch face representation */}
                    <div style={{
                      width: 140,
                      height: 140,
                      borderRadius: '50%',
                      background: 'rgba(0,0,0,0.3)',
                      border: '2px solid rgba(255,255,255,0.15)',
                      display: 'flex',
                      alignItems: 'center',
                      justifyContent: 'center',
                      backdropFilter: 'blur(10px)',
                    }}>
                      <div style={{
                        width: 120,
                        height: 120,
                        borderRadius: '50%',
                        border: '1px solid rgba(255,255,255,0.08)',
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'center',
                      }}>
                        {/* Watch hands */}
                        <div style={{
                          width: 1,
                          height: 40,
                          background: 'var(--star-white)',
                          position: 'absolute',
                          transformOrigin: 'bottom',
                          transform: 'rotate(-30deg) translateY(-20px)',
                        }} />
                        <div style={{
                          width: 1,
                          height: 28,
                          background: watch.color,
                          position: 'absolute',
                          transformOrigin: 'bottom',
                          transform: 'rotate(60deg) translateY(-14px)',
                        }} />
                        <div style={{
                          width: 4,
                          height: 4,
                          borderRadius: '50%',
                          background: 'var(--star-white)',
                        }} />
                      </div>
                    </div>

                    {/* Glow effect */}
                    <div style={{
                      position: 'absolute',
                      bottom: -40,
                      left: '50%',
                      transform: 'translateX(-50%)',
                      width: 200,
                      height: 80,
                      background: `radial-gradient(ellipse, ${watch.color}33, transparent)`,
                      filter: 'blur(20px)',
                    }} />
                  </div>

                  {/* Watch Info */}
                  <div style={{ padding: 'var(--space-6)' }}>
                    <div className="label" style={{ color: watch.color, marginBottom: 'var(--space-2)' }}>
                      {watch.edition}
                    </div>
                    <h3 style={{
                      fontFamily: 'var(--font-display)',
                      fontSize: 'var(--text-xl)',
                      fontWeight: 600,
                      marginBottom: 'var(--space-2)',
                    }}>
                      {watch.name}
                    </h3>
                    <div className="body-sm">{watch.price}</div>
                  </div>
                </GlassCard>
              </ScrollReveal>
            ))}
          </div>
        </div>
      </section>

      <style>{`
        @media (max-width: 900px) {
          .section div[style*="grid-template-columns: repeat(3"] {
            grid-template-columns: repeat(2, 1fr) !important;
          }
        }
        @media (max-width: 600px) {
          .section div[style*="grid-template-columns: repeat(3"],
          .section div[style*="grid-template-columns: repeat(2"] {
            grid-template-columns: 1fr !important;
          }
        }
      `}</style>
    </div>
  );
}
