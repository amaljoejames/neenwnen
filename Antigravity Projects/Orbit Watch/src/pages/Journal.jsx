import React from 'react';
import { useNavigate } from 'react-router-dom';
import TextReveal from '../components/ui/TextReveal';
import ScrollReveal from '../components/ui/ScrollReveal';
import GlassCard from '../components/ui/GlassCard';
import { useCursor } from '../systems/CursorSystem';

const ARTICLES = [
  {
    id: 1,
    category: 'Engineering',
    title: 'The Physics of Magnetic Levitation in Watchmaking',
    excerpt: 'How we suspend a balance wheel in mid-air using rare-earth magnets and a near-vacuum chamber — and why it matters for precision.',
    date: 'March 2026',
    readTime: '8 min read',
    gradient: 'linear-gradient(135deg, #1a0a30, #3a1a60)',
  },
  {
    id: 2,
    category: 'Design',
    title: 'Why Uranus Rotates Sideways: A Watchmaker\'s Perspective',
    excerpt: 'The 97° axial tilt that makes Uranus unique became the foundation for our most radical case design. Here\'s the engineering story.',
    date: 'February 2026',
    readTime: '6 min read',
    gradient: 'linear-gradient(135deg, #0a1840, #1a4a6a)',
  },
  {
    id: 3,
    category: 'Craftsmanship',
    title: 'Hand-Engraving Neptune\'s Rings at 100x Magnification',
    excerpt: 'Our master engraver spends 40 hours etching Neptune\'s ring system onto each Storm Chaser dial. A journey in micrometric precision.',
    date: 'January 2026',
    readTime: '10 min read',
    gradient: 'linear-gradient(135deg, #0a0a30, #1a2a60)',
  },
  {
    id: 4,
    category: 'Astronomy',
    title: 'City Lights from Orbit: The Data Behind the Dial',
    excerpt: 'We partnered with NASA to map Earth\'s night-time radiance data directly onto the Earth Origin\'s micro-LED dial. Here\'s how we did it.',
    date: 'December 2025',
    readTime: '12 min read',
    gradient: 'linear-gradient(135deg, #0a2030, #1a5060)',
  },
  {
    id: 5,
    category: 'Materials',
    title: 'Cryo-Titanium: Freezing Metal to Make it Stronger',
    excerpt: 'By treating Grade 5 titanium at -196°C, we create a case material that is both harder and more resistant to micro-scratches than any conventional alloy.',
    date: 'November 2025',
    readTime: '7 min read',
    gradient: 'linear-gradient(135deg, #1a1a30, #2a2a50)',
  },
  {
    id: 6,
    category: 'Events',
    title: 'Orbit at the Baselworld Planetary Pavilion',
    excerpt: 'Inside our immersive installation — a 360° planetarium where visitors experienced each watch in the environment of its namesake planet.',
    date: 'October 2025',
    readTime: '5 min read',
    gradient: 'linear-gradient(135deg, #200a30, #4a1a60)',
  },
];

export default function Journal() {
  const navigate = useNavigate();
  const { setCursorState } = useCursor();

  return (
    <div className="page-content" style={{ paddingTop: 120 }}>
      <section className="section">
        <div className="container">
          <ScrollReveal>
            <div className="label" style={{ marginBottom: 'var(--space-4)' }}>THE JOURNAL</div>
            <TextReveal as="h1" className="display-xl gradient-text" animation="spring-up" type="words">
              Stories from the void
            </TextReveal>
          </ScrollReveal>

          <ScrollReveal delay={0.3}>
            <p className="body-lg" style={{ maxWidth: 600, marginTop: 'var(--space-6)' }}>
              Deep dives into the engineering, design, and astronomy that shapes every Orbit timepiece.
            </p>
          </ScrollReveal>

          {/* Featured Article */}
          <ScrollReveal delay={0.4}>
            <GlassCard
              className="glass"
              onClick={() => {}}
              style={{
                marginTop: 'var(--space-12)',
                padding: 0,
                overflow: 'hidden',
              }}
            >
              <div style={{
                display: 'grid',
                gridTemplateColumns: '1.5fr 1fr',
              }}>
                <div style={{
                  padding: 'var(--space-10)',
                  display: 'flex',
                  flexDirection: 'column',
                  justifyContent: 'center',
                }}>
                  <div className="label" style={{ color: 'var(--nebula-violet)', marginBottom: 'var(--space-3)' }}>
                    FEATURED • {ARTICLES[0].category}
                  </div>
                  <h2 style={{
                    fontFamily: 'var(--font-display)',
                    fontSize: 'var(--text-3xl)',
                    fontWeight: 600,
                    lineHeight: 1.2,
                    marginBottom: 'var(--space-4)',
                  }}>
                    {ARTICLES[0].title}
                  </h2>
                  <p className="body-lg" style={{ marginBottom: 'var(--space-6)' }}>
                    {ARTICLES[0].excerpt}
                  </p>
                  <div className="body-sm">
                    {ARTICLES[0].date} • {ARTICLES[0].readTime}
                  </div>
                </div>
                <div style={{
                  background: ARTICLES[0].gradient,
                  minHeight: 300,
                  display: 'flex',
                  alignItems: 'center',
                  justifyContent: 'center',
                }}>
                  <div style={{
                    width: 120,
                    height: 120,
                    borderRadius: '50%',
                    border: '1px solid rgba(255,255,255,0.1)',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    animation: 'float 4s ease-in-out infinite',
                  }}>
                    <div style={{
                      width: 60,
                      height: 60,
                      borderRadius: '50%',
                      background: 'radial-gradient(circle, var(--nebula-purple), transparent)',
                      boxShadow: '0 0 30px var(--nebula-glow)',
                    }} />
                  </div>
                </div>
              </div>
            </GlassCard>
          </ScrollReveal>

          {/* Article Grid */}
          <div style={{
            display: 'grid',
            gridTemplateColumns: 'repeat(3, 1fr)',
            gap: 'var(--space-6)',
            marginTop: 'var(--space-10)',
          }}>
            {ARTICLES.slice(1).map((article, i) => (
              <ScrollReveal key={article.id} delay={i * 0.1}>
                <GlassCard
                  className="glass"
                  onClick={() => {}}
                  style={{
                    padding: 0,
                    overflow: 'hidden',
                    height: '100%',
                    display: 'flex',
                    flexDirection: 'column',
                  }}
                >
                  <div style={{
                    height: 160,
                    background: article.gradient,
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                  }}>
                    <div style={{
                      width: 40,
                      height: 40,
                      borderRadius: '50%',
                      border: '1px solid rgba(255,255,255,0.1)',
                      animation: `float ${3 + i * 0.5}s ease-in-out infinite`,
                    }} />
                  </div>
                  <div style={{ padding: 'var(--space-6)', flex: 1, display: 'flex', flexDirection: 'column' }}>
                    <div className="label" style={{ marginBottom: 'var(--space-2)', fontSize: '0.65rem' }}>
                      {article.category}
                    </div>
                    <h3 style={{
                      fontFamily: 'var(--font-display)',
                      fontSize: 'var(--text-lg)',
                      fontWeight: 600,
                      lineHeight: 1.3,
                      marginBottom: 'var(--space-3)',
                    }}>
                      {article.title}
                    </h3>
                    <p className="body-sm" style={{ marginBottom: 'var(--space-4)', flex: 1 }}>
                      {article.excerpt.slice(0, 100)}...
                    </p>
                    <div className="body-sm" style={{ fontSize: '0.7rem' }}>
                      {article.date} • {article.readTime}
                    </div>
                  </div>
                </GlassCard>
              </ScrollReveal>
            ))}
          </div>
        </div>
      </section>

      <style>{`
        @keyframes float {
          0%, 100% { transform: translateY(0); }
          50% { transform: translateY(-10px); }
        }
        @media (max-width: 768px) {
          .section div[style*="grid-template-columns: repeat(3"] {
            grid-template-columns: 1fr !important;
          }
          .section div[style*="grid-template-columns: 1.5fr 1fr"] {
            grid-template-columns: 1fr !important;
          }
        }
      `}</style>
    </div>
  );
}
