import React, { useState } from 'react';
import TextReveal from '../components/ui/TextReveal';
import ScrollReveal from '../components/ui/ScrollReveal';
import GlassCard from '../components/ui/GlassCard';
import MagneticButton from '../components/ui/MagneticButton';
import { useCursor } from '../systems/CursorSystem';

const CASE_MATERIALS = [
  { id: 'titanium', name: 'Grade 5 Titanium', color: '#888899' },
  { id: 'ceramic', name: 'Black Ceramic', color: '#222233' },
  { id: 'rose-gold', name: 'Rose Gold', color: '#b76e79' },
];

const DIALPLANETS = [
  { id: 'earth', name: 'Earth', color: 'var(--earth-blue)', bg: 'linear-gradient(135deg, #0a2040, #1e5c8a)' },
  { id: 'uranus', name: 'Uranus', color: 'var(--uranus-ice)', bg: 'linear-gradient(135deg, #1a3a50, #7dd3fc)' },
  { id: 'neptune', name: 'Neptune', color: 'var(--neptune-deep)', bg: 'linear-gradient(135deg, #0a1840, #3b82f6)' },
];

const STRAPS = [
  { id: 'bracelet', name: 'Titanium Bracelet', color: '#777788' },
  { id: 'rubber', name: 'Deep-sea Rubber', color: '#333344' },
  { id: 'leather', name: 'Alcantara Leather', color: '#554433' },
  { id: 'nato', name: 'Orbital NATO', color: '#2a2a3a' },
];

export default function Configurator() {
  const { setCursorState } = useCursor();
  const [caseMat, setCaseMat] = useState('titanium');
  const [dial, setDial] = useState('earth');
  const [strap, setStrap] = useState('bracelet');

  const selectedDial = DIALPLANETS.find(d => d.id === dial);
  const selectedCase = CASE_MATERIALS.find(c => c.id === caseMat);

  return (
    <div className="page-content" style={{ paddingTop: 120 }}>
      <section className="section">
        <div className="container">
          <ScrollReveal>
            <div className="label" style={{ marginBottom: 'var(--space-4)' }}>CONFIGURE</div>
            <TextReveal as="h1" className="display-xl gradient-text" animation="spring-up" type="words">
              Build your timepiece
            </TextReveal>
          </ScrollReveal>

          <div style={{
            display: 'grid',
            gridTemplateColumns: '1.2fr 1fr',
            gap: 'var(--space-16)',
            marginTop: 'var(--space-12)',
            alignItems: 'start',
          }}>
            {/* Watch Preview */}
            <ScrollReveal direction="left">
              <div style={{
                position: 'sticky',
                top: 120,
                width: '100%',
                aspectRatio: '1',
                borderRadius: 'var(--radius-2xl)',
                background: selectedDial.bg,
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                border: '1px solid var(--glass-border)',
                overflow: 'hidden',
              }}>
                {/* Watch case */}
                <div style={{
                  width: '55%',
                  aspectRatio: '1',
                  borderRadius: '50%',
                  background: `radial-gradient(circle at 40% 35%, ${selectedCase.color}cc, ${selectedCase.color}44)`,
                  border: `3px solid ${selectedCase.color}88`,
                  display: 'flex',
                  alignItems: 'center',
                  justifyContent: 'center',
                  boxShadow: `0 20px 60px rgba(0,0,0,0.5), 0 0 40px ${selectedDial.color}22`,
                  position: 'relative',
                }}>
                  {/* Dial */}
                  <div style={{
                    width: '85%',
                    aspectRatio: '1',
                    borderRadius: '50%',
                    background: `linear-gradient(135deg, rgba(0,0,0,0.6), rgba(0,0,0,0.2))`,
                    border: `1px solid rgba(255,255,255,0.08)`,
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    position: 'relative',
                  }}>
                    {/* Planet mini */}
                    <div style={{
                      width: '35%',
                      aspectRatio: '1',
                      borderRadius: '50%',
                      background: `radial-gradient(circle, ${selectedDial.color}, ${selectedDial.color}44)`,
                      boxShadow: `0 0 30px ${selectedDial.color}44`,
                      position: 'absolute',
                      top: '25%',
                    }} />

                    {/* Hour markers */}
                    {Array.from({ length: 12 }, (_, i) => {
                      const angle = (i / 12) * Math.PI * 2 - Math.PI / 2;
                      return (
                        <div key={i} style={{
                          position: 'absolute',
                          width: i % 3 === 0 ? 2 : 1,
                          height: i % 3 === 0 ? 12 : 6,
                          background: i % 3 === 0 ? 'rgba(255,255,255,0.7)' : 'rgba(255,255,255,0.2)',
                          left: `${50 + Math.cos(angle) * 43}%`,
                          top: `${50 + Math.sin(angle) * 43}%`,
                          transform: `translate(-50%, -50%) rotate(${i * 30}deg)`,
                          borderRadius: 1,
                        }} />
                      );
                    })}

                    {/* Hands */}
                    <div style={{ position: 'absolute', width: 2, height: '28%', background: 'white', bottom: '50%', left: '50%', transform: 'translateX(-50%) rotate(-25deg)', transformOrigin: 'bottom', borderRadius: 2 }} />
                    <div style={{ position: 'absolute', width: 1.5, height: '20%', background: selectedDial.color, bottom: '50%', left: '50%', transform: 'translateX(-50%) rotate(70deg)', transformOrigin: 'bottom', borderRadius: 2 }} />
                    <div style={{ width: 6, height: 6, borderRadius: '50%', background: 'white', zIndex: 5, position: 'relative' }} />

                    <div style={{ position: 'absolute', top: '60%', fontFamily: 'var(--font-display)', fontSize: '5px', fontWeight: 700, letterSpacing: '0.2em', color: 'rgba(255,255,255,0.3)' }}>ORBIT</div>
                  </div>
                </div>

                {/* Crown */}
                <div style={{
                  position: 'absolute',
                  right: -4,
                  width: 8,
                  height: 24,
                  background: `${selectedCase.color}88`,
                  borderRadius: '0 3px 3px 0',
                  border: `1px solid ${selectedCase.color}44`,
                }} />

                {/* Lugs (top and bottom strap connections) */}
                <div style={{ position: 'absolute', top: -8, left: '30%', right: '30%', height: 8, background: `${selectedCase.color}66`, borderRadius: '4px 4px 0 0' }} />
                <div style={{ position: 'absolute', bottom: -8, left: '30%', right: '30%', height: 8, background: `${selectedCase.color}66`, borderRadius: '0 0 4px 4px' }} />
              </div>
            </ScrollReveal>

            {/* Configuration Options */}
            <div style={{ display: 'flex', flexDirection: 'column', gap: 'var(--space-10)' }}>
              {/* Case Material */}
              <ScrollReveal delay={0.1}>
                <h3 className="display-md" style={{ marginBottom: 'var(--space-4)' }}>Case Material</h3>
                <div style={{ display: 'flex', flexDirection: 'column', gap: 'var(--space-3)' }}>
                  {CASE_MATERIALS.map(mat => (
                    <button
                      key={mat.id}
                      onClick={() => setCaseMat(mat.id)}
                      onMouseEnter={() => setCursorState('hover')}
                      onMouseLeave={() => setCursorState('default')}
                      style={{
                        display: 'flex',
                        alignItems: 'center',
                        gap: 'var(--space-4)',
                        padding: 'var(--space-4)',
                        borderRadius: 'var(--radius-md)',
                        background: caseMat === mat.id ? 'rgba(255,255,255,0.05)' : 'transparent',
                        border: `1px solid ${caseMat === mat.id ? 'rgba(255,255,255,0.15)' : 'rgba(255,255,255,0.05)'}`,
                        transition: 'all 0.3s',
                        textAlign: 'left',
                      }}
                    >
                      <div style={{
                        width: 32,
                        height: 32,
                        borderRadius: '50%',
                        background: `radial-gradient(circle, ${mat.color}, ${mat.color}88)`,
                        border: caseMat === mat.id ? '2px solid var(--star-white)' : '2px solid transparent',
                      }} />
                      <span style={{
                        fontWeight: caseMat === mat.id ? 600 : 400,
                        color: caseMat === mat.id ? 'var(--star-white)' : 'var(--star-muted)',
                      }}>
                        {mat.name}
                      </span>
                    </button>
                  ))}
                </div>
              </ScrollReveal>

              {/* Dial / Planet */}
              <ScrollReveal delay={0.2}>
                <h3 className="display-md" style={{ marginBottom: 'var(--space-4)' }}>Planetary Dial</h3>
                <div style={{ display: 'flex', gap: 'var(--space-3)' }}>
                  {DIALPLANETS.map(d => (
                    <button
                      key={d.id}
                      onClick={() => setDial(d.id)}
                      onMouseEnter={() => setCursorState('hover')}
                      onMouseLeave={() => setCursorState('default')}
                      style={{
                        flex: 1,
                        padding: 'var(--space-4)',
                        borderRadius: 'var(--radius-md)',
                        background: dial === d.id ? d.bg : 'transparent',
                        border: `1px solid ${dial === d.id ? d.color + '44' : 'rgba(255,255,255,0.05)'}`,
                        textAlign: 'center',
                        transition: 'all 0.3s',
                      }}
                    >
                      <div style={{
                        width: 40,
                        height: 40,
                        borderRadius: '50%',
                        background: `radial-gradient(circle, ${d.color}, ${d.color}44)`,
                        margin: '0 auto var(--space-2)',
                        boxShadow: dial === d.id ? `0 0 20px ${d.color}44` : 'none',
                      }} />
                      <span style={{
                        fontSize: 'var(--text-sm)',
                        fontWeight: dial === d.id ? 600 : 400,
                        color: dial === d.id ? 'var(--star-white)' : 'var(--star-muted)',
                      }}>
                        {d.name}
                      </span>
                    </button>
                  ))}
                </div>
              </ScrollReveal>

              {/* Strap */}
              <ScrollReveal delay={0.3}>
                <h3 className="display-md" style={{ marginBottom: 'var(--space-4)' }}>Strap</h3>
                <div style={{ display: 'flex', flexDirection: 'column', gap: 'var(--space-3)' }}>
                  {STRAPS.map(s => (
                    <button
                      key={s.id}
                      onClick={() => setStrap(s.id)}
                      onMouseEnter={() => setCursorState('hover')}
                      onMouseLeave={() => setCursorState('default')}
                      style={{
                        display: 'flex',
                        alignItems: 'center',
                        gap: 'var(--space-4)',
                        padding: 'var(--space-4)',
                        borderRadius: 'var(--radius-md)',
                        background: strap === s.id ? 'rgba(255,255,255,0.05)' : 'transparent',
                        border: `1px solid ${strap === s.id ? 'rgba(255,255,255,0.15)' : 'rgba(255,255,255,0.05)'}`,
                        transition: 'all 0.3s',
                        textAlign: 'left',
                      }}
                    >
                      <div style={{
                        width: 32,
                        height: 8,
                        borderRadius: 4,
                        background: s.color,
                        border: strap === s.id ? '1px solid var(--star-muted)' : '1px solid transparent',
                      }} />
                      <span style={{
                        fontWeight: strap === s.id ? 600 : 400,
                        color: strap === s.id ? 'var(--star-white)' : 'var(--star-muted)',
                      }}>
                        {s.name}
                      </span>
                    </button>
                  ))}
                </div>
              </ScrollReveal>

              {/* Summary */}
              <ScrollReveal delay={0.4}>
                <GlassCard className="glass" style={{ padding: 'var(--space-6)' }}>
                  <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: 'var(--space-4)' }}>
                    <span className="body-sm">Your Configuration</span>
                    <span className="display-md gradient-text" style={{ fontWeight: 700 }}>$52,500</span>
                  </div>
                  <div className="body-sm" style={{ marginBottom: 'var(--space-4)' }}>
                    {CASE_MATERIALS.find(c => c.id === caseMat)?.name} • {DIALPLANETS.find(d => d.id === dial)?.name} Dial • {STRAPS.find(s => s.id === strap)?.name}
                  </div>
                  <MagneticButton variant="primary" size="lg" style={{ width: '100%' }}>
                    Reserve This Configuration
                  </MagneticButton>
                </GlassCard>
              </ScrollReveal>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}
