import React from 'react';
import TextReveal from '../components/ui/TextReveal';
import ScrollReveal from '../components/ui/ScrollReveal';
import GlassCard from '../components/ui/GlassCard';
import MagneticButton from '../components/ui/MagneticButton';

export default function About() {
  const timeline = [
    { year: '2019', title: 'The Spark', desc: 'A team of watchmakers and astrophysicists meet at CERN. A conversation about magnetic levitation begins.' },
    { year: '2020', title: 'First Prototype', desc: 'The first magnetically-suspended balance wheel is tested. It runs for 47 seconds before losing amplitude.' },
    { year: '2021', title: 'Vacuum Breakthrough', desc: 'By placing the escapement in a near-vacuum chamber, amplitude stability is achieved. The Orbital Movement™ is born.' },
    { year: '2022', title: 'Earth Edition', desc: 'The first production Orbit watch — the Earth Origin — is unveiled at Baselworld to universal acclaim.' },
    { year: '2023', title: 'Planetary Series', desc: 'Uranus and Neptune editions join the collection, each incorporating planet-specific design elements and complications.' },
    { year: '2024', title: 'Atelier Network', desc: 'Orbit opens dedicated ateliers in Geneva, Tokyo, New York, and Dubai — immersive spaces where clients experience the cosmos.' },
    { year: '2026', title: 'Today', desc: 'Six timepieces, three planetary editions, one mission: to capture the mechanics of the cosmos on your wrist.' },
  ];

  const team = [
    { name: 'Dr. Elara Voss', role: 'Founder & Chief Horologist', bio: 'Former CERN physicist. 15 years in haute horlogerie.' },
    { name: 'Kai Tanaka', role: 'Head of Design', bio: 'Ex-Apple Industrial Design. Red Dot Grand Prix winner.' },
    { name: 'Sofia Andromeda', role: 'Master Watchmaker', bio: '20 years at Patek Philippe. WOSTEP certified.' },
    { name: 'Marcus Stellar', role: 'CTO', bio: 'Pioneer in MEMS and magnetic levitation technology.' },
  ];

  return (
    <div className="page-content" style={{ paddingTop: 120 }}>
      {/* Hero */}
      <section className="section" style={{ minHeight: '50vh', display: 'flex', alignItems: 'center' }}>
        <div className="container" style={{ textAlign: 'center' }}>
          <ScrollReveal>
            <div className="label" style={{ marginBottom: 'var(--space-4)' }}>OUR STORY</div>
            <TextReveal as="h1" className="display-hero gradient-text" animation="spring-up" type="chars" stagger={0.02}>
              ABOUT
            </TextReveal>
            <p className="body-lg" style={{ maxWidth: 600, margin: 'var(--space-6) auto 0' }}>
              Born from the collision of astrophysics and haute horlogerie, Orbit exists to prove that the most precise instruments are inspired by the universe itself.
            </p>
          </ScrollReveal>
        </div>
      </section>

      {/* Timeline */}
      <section className="section" style={{ background: 'var(--void-deep)' }}>
        <div className="container" style={{ maxWidth: 800 }}>
          <ScrollReveal>
            <TextReveal as="h2" className="display-lg" animation="blur-in" type="words" style={{ textAlign: 'center', justifyContent: 'center', marginBottom: 'var(--space-16)' }}>
              Our journey through time
            </TextReveal>
          </ScrollReveal>

          <div style={{ position: 'relative' }}>
            {/* Timeline line */}
            <div style={{
              position: 'absolute',
              left: 80,
              top: 0,
              bottom: 0,
              width: 1,
              background: 'linear-gradient(180deg, var(--nebula-purple), var(--plasma-cyan), transparent)',
            }} />

            {timeline.map((event, i) => (
              <ScrollReveal key={i} delay={i * 0.08}>
                <div style={{
                  display: 'flex',
                  gap: 'var(--space-8)',
                  marginBottom: 'var(--space-10)',
                  alignItems: 'flex-start',
                }}>
                  <div style={{
                    minWidth: 60,
                    fontFamily: 'var(--font-display)',
                    fontSize: 'var(--text-sm)',
                    fontWeight: 600,
                    color: 'var(--plasma-cyan)',
                    textAlign: 'right',
                    paddingTop: 'var(--space-1)',
                  }}>
                    {event.year}
                  </div>

                  {/* Dot */}
                  <div style={{
                    minWidth: 12,
                    height: 12,
                    borderRadius: '50%',
                    background: i === timeline.length - 1 ? 'var(--plasma-cyan)' : 'var(--space-mid)',
                    border: '2px solid var(--nebula-purple)',
                    marginTop: 6,
                    boxShadow: i === timeline.length - 1 ? '0 0 15px var(--plasma-glow)' : 'none',
                    position: 'relative',
                    zIndex: 2,
                  }} />

                  <div style={{ paddingTop: 0 }}>
                    <h3 style={{
                      fontFamily: 'var(--font-display)',
                      fontSize: 'var(--text-xl)',
                      fontWeight: 600,
                      marginBottom: 'var(--space-2)',
                    }}>
                      {event.title}
                    </h3>
                    <p className="body-base">{event.desc}</p>
                  </div>
                </div>
              </ScrollReveal>
            ))}
          </div>
        </div>
      </section>

      {/* Team */}
      <section className="section">
        <div className="container">
          <ScrollReveal>
            <TextReveal as="h2" className="display-lg" animation="blur-in" type="words" style={{ textAlign: 'center', justifyContent: 'center', marginBottom: 'var(--space-12)' }}>
              The minds behind the movement
            </TextReveal>
          </ScrollReveal>

          <div style={{ display: 'grid', gridTemplateColumns: 'repeat(4, 1fr)', gap: 'var(--space-6)' }}>
            {team.map((person, i) => (
              <ScrollReveal key={i} delay={i * 0.1}>
                <GlassCard className="glass" style={{ padding: 'var(--space-6)', textAlign: 'center' }}>
                  <div style={{
                    width: 80,
                    height: 80,
                    borderRadius: '50%',
                    background: 'linear-gradient(135deg, var(--space-mid), var(--space-light))',
                    margin: '0 auto var(--space-4)',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    fontFamily: 'var(--font-display)',
                    fontSize: 'var(--text-2xl)',
                    fontWeight: 700,
                    color: 'var(--star-muted)',
                  }}>
                    {person.name.split(' ').map(n => n[0]).join('')}
                  </div>
                  <h3 style={{
                    fontFamily: 'var(--font-display)',
                    fontSize: 'var(--text-lg)',
                    fontWeight: 600,
                    marginBottom: 'var(--space-1)',
                  }}>
                    {person.name}
                  </h3>
                  <div className="label" style={{ marginBottom: 'var(--space-3)', fontSize: '0.65rem' }}>
                    {person.role}
                  </div>
                  <p className="body-sm">{person.bio}</p>
                </GlassCard>
              </ScrollReveal>
            ))}
          </div>
        </div>
      </section>

      {/* Mission */}
      <section className="section" style={{ background: 'var(--void-deep)' }}>
        <div className="container" style={{ textAlign: 'center', maxWidth: 700 }}>
          <ScrollReveal>
            <div style={{
              fontSize: 'var(--text-5xl)',
              fontFamily: 'var(--font-display)',
              fontWeight: 300,
              lineHeight: 1.4,
              fontStyle: 'italic',
              color: 'var(--star-dim)',
            }}>
              "We don't build watches. We build instruments that let you wear the mechanics of the universe."
            </div>
            <div style={{ marginTop: 'var(--space-6)' }}>
              <span className="label">Dr. Elara Voss</span>
              <span className="body-sm" style={{ marginLeft: 'var(--space-2)' }}>— Founder</span>
            </div>
          </ScrollReveal>
        </div>
      </section>
    </div>
  );
}
