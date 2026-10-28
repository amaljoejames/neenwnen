import React, { useState, useRef, useEffect } from 'react';
import TextReveal from '../components/ui/TextReveal';
import ScrollReveal from '../components/ui/ScrollReveal';
import MagneticButton from '../components/ui/MagneticButton';
import { useCursor } from '../systems/CursorSystem';
import gravityEngine from '../systems/GravityEngine';

export default function Contact() {
  const { setCursorState } = useCursor();
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    subject: 'general',
    message: '',
  });
  const [submitted, setSubmitted] = useState(false);

  const handleChange = (e) => {
    setFormData(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setSubmitted(true);
  };

  return (
    <div className="page-content" style={{ paddingTop: 120 }}>
      <section className="section" style={{ minHeight: '80vh' }}>
        <div className="container" style={{ maxWidth: 900 }}>
          <div style={{
            display: 'grid',
            gridTemplateColumns: '1fr 1.2fr',
            gap: 'var(--space-16)',
            alignItems: 'start',
          }}>
            {/* Info */}
            <div>
              <ScrollReveal>
                <div className="label" style={{ marginBottom: 'var(--space-4)' }}>GET IN TOUCH</div>
                <TextReveal as="h1" className="display-xl" animation="spring-up" type="words">
                  Contact
                </TextReveal>
              </ScrollReveal>

              <ScrollReveal delay={0.2}>
                <p className="body-lg" style={{ marginTop: 'var(--space-6)' }}>
                  Whether you're interested in a private viewing, a bespoke commission, or simply want to learn more — we'd love to hear from you.
                </p>
              </ScrollReveal>

              <ScrollReveal delay={0.3}>
                <div style={{ marginTop: 'var(--space-10)', display: 'flex', flexDirection: 'column', gap: 'var(--space-6)' }}>
                  {[
                    { label: 'General Inquiries', value: 'hello@orbit.watch' },
                    { label: 'Press & Media', value: 'press@orbit.watch' },
                    { label: 'Atelier Bookings', value: 'atelier@orbit.watch' },
                  ].map((contact, i) => (
                    <div key={i}>
                      <div className="label" style={{ marginBottom: 'var(--space-1)', fontSize: '0.65rem' }}>{contact.label}</div>
                      <div style={{ fontFamily: 'var(--font-display)', fontSize: 'var(--text-base)', color: 'var(--star-white)' }}>
                        {contact.value}
                      </div>
                    </div>
                  ))}
                </div>
              </ScrollReveal>

              <ScrollReveal delay={0.4}>
                <div style={{ marginTop: 'var(--space-10)' }}>
                  <div className="label" style={{ marginBottom: 'var(--space-3)', fontSize: '0.65rem' }}>ATELIERS</div>
                  <div className="body-sm" style={{ lineHeight: 2 }}>
                    Geneva · Rue du Rhône 48<br />
                    Tokyo · Ginza 7-Chome<br />
                    New York · Madison Avenue 820<br />
                    Dubai · DIFC Gate Avenue
                  </div>
                </div>
              </ScrollReveal>
            </div>

            {/* Form */}
            <ScrollReveal direction="left" delay={0.2}>
              {submitted ? (
                <div style={{
                  padding: 'var(--space-12)',
                  textAlign: 'center',
                  background: 'var(--glass-bg)',
                  border: '1px solid var(--glass-border)',
                  borderRadius: 'var(--radius-xl)',
                  backdropFilter: 'blur(20px)',
                }}>
                  <div style={{ fontSize: 'var(--text-4xl)', marginBottom: 'var(--space-4)' }}>✓</div>
                  <h3 className="display-md" style={{ marginBottom: 'var(--space-3)' }}>Message sent</h3>
                  <p className="body-base">
                    Thank you for reaching out. We'll respond within 24 hours.
                  </p>
                </div>
              ) : (
                <form
                  onSubmit={handleSubmit}
                  style={{
                    display: 'flex',
                    flexDirection: 'column',
                    gap: 'var(--space-5)',
                    padding: 'var(--space-8)',
                    background: 'var(--glass-bg)',
                    border: '1px solid var(--glass-border)',
                    borderRadius: 'var(--radius-xl)',
                    backdropFilter: 'blur(20px)',
                  }}
                >
                  <GravityInput
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    placeholder="Your name"
                    setCursorState={setCursorState}
                  />
                  <GravityInput
                    label="Email"
                    name="email"
                    type="email"
                    value={formData.email}
                    onChange={handleChange}
                    placeholder="you@example.com"
                    setCursorState={setCursorState}
                  />

                  <div>
                    <label className="label" style={{ display: 'block', marginBottom: 'var(--space-2)', fontSize: '0.65rem' }}>Subject</label>
                    <select
                      name="subject"
                      value={formData.subject}
                      onChange={handleChange}
                      onFocus={() => setCursorState('hover')}
                      onBlur={() => setCursorState('default')}
                      style={{
                        width: '100%',
                        padding: 'var(--space-3) var(--space-4)',
                        background: 'rgba(255,255,255,0.03)',
                        border: '1px solid var(--glass-border)',
                        borderRadius: 'var(--radius-md)',
                        color: 'var(--star-white)',
                        fontFamily: 'var(--font-body)',
                        fontSize: 'var(--text-base)',
                        outline: 'none',
                        cursor: 'none',
                        transition: 'border-color 0.3s',
                        appearance: 'none',
                      }}
                    >
                      <option value="general" style={{ background: 'var(--void-black)' }}>General Inquiry</option>
                      <option value="purchase" style={{ background: 'var(--void-black)' }}>Purchase Interest</option>
                      <option value="atelier" style={{ background: 'var(--void-black)' }}>Atelier Visit</option>
                      <option value="bespoke" style={{ background: 'var(--void-black)' }}>Bespoke Commission</option>
                      <option value="press" style={{ background: 'var(--void-black)' }}>Press & Media</option>
                    </select>
                  </div>

                  <div>
                    <label className="label" style={{ display: 'block', marginBottom: 'var(--space-2)', fontSize: '0.65rem' }}>Message</label>
                    <textarea
                      name="message"
                      value={formData.message}
                      onChange={handleChange}
                      onFocus={() => setCursorState('text')}
                      onBlur={() => setCursorState('default')}
                      placeholder="Tell us about your interest..."
                      rows={5}
                      style={{
                        width: '100%',
                        padding: 'var(--space-3) var(--space-4)',
                        background: 'rgba(255,255,255,0.03)',
                        border: '1px solid var(--glass-border)',
                        borderRadius: 'var(--radius-md)',
                        color: 'var(--star-white)',
                        fontFamily: 'var(--font-body)',
                        fontSize: 'var(--text-base)',
                        outline: 'none',
                        cursor: 'none',
                        resize: 'vertical',
                        transition: 'border-color 0.3s',
                      }}
                    />
                  </div>

                  <MagneticButton variant="primary" size="lg" type="submit">
                    Send Message
                  </MagneticButton>
                </form>
              )}
            </ScrollReveal>
          </div>
        </div>
      </section>
    </div>
  );
}

// Gravity-affected input field — slight magnetic pull toward cursor
function GravityInput({ label, name, type = 'text', value, onChange, placeholder, setCursorState }) {
  const wrapperRef = useRef(null);
  const transform = useRef({ x: 0, y: 0 });

  useEffect(() => {
    let animId;
    const animate = () => {
      if (!wrapperRef.current) return;

      const rect = wrapperRef.current.getBoundingClientRect();
      const cx = rect.x + rect.width / 2;
      const cy = rect.y + rect.height / 2;
      const mx = gravityEngine.mouse.x;
      const my = gravityEngine.mouse.y;
      const dx = mx - cx;
      const dy = my - cy;
      const dist = Math.sqrt(dx * dx + dy * dy);

      if (dist < 150) {
        const force = (1 - dist / 150) * 0.08;
        transform.current.x += (dx * force - transform.current.x) * 0.1;
        transform.current.y += (dy * force - transform.current.y) * 0.1;
      } else {
        transform.current.x += (0 - transform.current.x) * 0.1;
        transform.current.y += (0 - transform.current.y) * 0.1;
      }

      wrapperRef.current.style.transform = `translate(${transform.current.x}px, ${transform.current.y}px)`;
      animId = requestAnimationFrame(animate);
    };
    animId = requestAnimationFrame(animate);
    return () => cancelAnimationFrame(animId);
  }, []);

  return (
    <div ref={wrapperRef} style={{ willChange: 'transform' }}>
      <label className="label" style={{ display: 'block', marginBottom: 'var(--space-2)', fontSize: '0.65rem' }}>
        {label}
      </label>
      <input
        type={type}
        name={name}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        onFocus={() => setCursorState('text')}
        onBlur={() => setCursorState('default')}
        style={{
          width: '100%',
          padding: 'var(--space-3) var(--space-4)',
          background: 'rgba(255,255,255,0.03)',
          border: '1px solid var(--glass-border)',
          borderRadius: 'var(--radius-md)',
          color: 'var(--star-white)',
          fontFamily: 'var(--font-body)',
          fontSize: 'var(--text-base)',
          outline: 'none',
          cursor: 'none',
          transition: 'border-color 0.3s',
        }}
      />
    </div>
  );
}
