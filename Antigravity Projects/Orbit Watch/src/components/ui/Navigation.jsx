import React, { useState, useRef, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { useCursor } from '../../systems/CursorSystem';
import gravityEngine from '../../systems/GravityEngine';

const NAV_ITEMS = [
  { path: '/', label: 'Home' },
  { path: '/collection', label: 'Collection' },
  { path: '/explore', label: 'Explore' },
  { path: '/technology', label: 'Technology' },
  { path: '/about', label: 'About' },
  { path: '/journal', label: 'Journal' },
  { path: '/contact', label: 'Contact' },
];

export default function Navigation() {
  const location = useLocation();
  const { setCursorState } = useCursor();
  const [isOpen, setIsOpen] = useState(false);
  const [scrolled, setScrolled] = useState(false);
  const navRef = useRef(null);
  const linkRefs = useRef([]);
  const indicatorRef = useRef(null);

  // Scroll detection for glass effect
  useEffect(() => {
    const handleScroll = () => {
      setScrolled(window.scrollY > 50);
    };
    window.addEventListener('scroll', handleScroll, { passive: true });
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  // Magnetic effect on nav links
  useEffect(() => {
    let animId;
    const animate = () => {
      linkRefs.current.forEach(link => {
        if (!link) return;
        const rect = link.getBoundingClientRect();
        const cx = rect.x + rect.width / 2;
        const cy = rect.y + rect.height / 2;
        const mx = gravityEngine.mouse.x;
        const my = gravityEngine.mouse.y;
        const dx = mx - cx;
        const dy = my - cy;
        const dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < 80) {
          const force = (1 - dist / 80) * 0.25;
          link.style.transform = `translate(${dx * force}px, ${dy * force}px)`;
        } else {
          link.style.transform = 'translate(0, 0)';
        }
      });

      animId = requestAnimationFrame(animate);
    };
    animId = requestAnimationFrame(animate);
    return () => cancelAnimationFrame(animId);
  }, []);

  return (
    <>
      <nav
        ref={navRef}
        className={`nav ${scrolled ? 'nav--scrolled' : ''}`}
        style={{
          position: 'fixed',
          top: 0,
          left: 0,
          right: 0,
          zIndex: 'var(--z-nav)',
          padding: '0 var(--space-8)',
          height: 72,
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'space-between',
          transition: 'background 0.5s, backdrop-filter 0.5s',
          background: scrolled ? 'rgba(3, 0, 20, 0.8)' : 'transparent',
          backdropFilter: scrolled ? 'blur(20px)' : 'none',
          WebkitBackdropFilter: scrolled ? 'blur(20px)' : 'none',
          borderBottom: scrolled ? '1px solid rgba(255,255,255,0.05)' : '1px solid transparent',
        }}
      >
        {/* Logo */}
        <Link
          to="/"
          className="nav__logo"
          onMouseEnter={() => setCursorState('hover')}
          onMouseLeave={() => setCursorState('default')}
          style={{
            fontFamily: 'var(--font-display)',
            fontSize: 'var(--text-xl)',
            fontWeight: 700,
            letterSpacing: '0.1em',
            textTransform: 'uppercase',
            background: 'linear-gradient(135deg, var(--star-white), var(--plasma-cyan))',
            WebkitBackgroundClip: 'text',
            WebkitTextFillColor: 'transparent',
            backgroundClip: 'text',
          }}
        >
          Orbit
        </Link>

        {/* Desktop links */}
        <div
          className="nav__links"
          style={{
            display: 'flex',
            alignItems: 'center',
            gap: 'var(--space-8)',
          }}
        >
          {NAV_ITEMS.slice(1).map((item, i) => (
            <Link
              key={item.path}
              ref={el => linkRefs.current[i] = el}
              to={item.path}
              onMouseEnter={() => setCursorState('hover')}
              onMouseLeave={() => setCursorState('default')}
              style={{
                fontFamily: 'var(--font-body)',
                fontSize: 'var(--text-sm)',
                fontWeight: 400,
                letterSpacing: '0.05em',
                textTransform: 'uppercase',
                color: location.pathname === item.path
                  ? 'var(--star-white)'
                  : 'var(--star-muted)',
                transition: 'color 0.3s',
                willChange: 'transform',
              }}
            >
              {item.label}
            </Link>
          ))}
        </div>

        {/* Mobile menu button */}
        <button
          className="nav__menu-btn"
          onClick={() => setIsOpen(!isOpen)}
          onMouseEnter={() => setCursorState('hover')}
          onMouseLeave={() => setCursorState('default')}
          style={{
            display: 'none',
            flexDirection: 'column',
            gap: 5,
            padding: 10,
          }}
          aria-label="Toggle menu"
        >
          <span style={{
            width: 24,
            height: 2,
            background: 'var(--star-white)',
            transition: 'transform 0.3s',
            transform: isOpen ? 'rotate(45deg) translate(3px, 3px)' : 'none',
          }} />
          <span style={{
            width: 24,
            height: 2,
            background: 'var(--star-white)',
            transition: 'opacity 0.3s',
            opacity: isOpen ? 0 : 1,
          }} />
          <span style={{
            width: 24,
            height: 2,
            background: 'var(--star-white)',
            transition: 'transform 0.3s',
            transform: isOpen ? 'rotate(-45deg) translate(3px, -3px)' : 'none',
          }} />
        </button>
      </nav>

      {/* Mobile menu overlay */}
      {isOpen && (
        <div
          className="nav__mobile-overlay"
          style={{
            position: 'fixed',
            inset: 0,
            zIndex: 99,
            background: 'rgba(3, 0, 20, 0.95)',
            backdropFilter: 'blur(30px)',
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            gap: 'var(--space-8)',
          }}
        >
          {NAV_ITEMS.map(item => (
            <Link
              key={item.path}
              to={item.path}
              onClick={() => setIsOpen(false)}
              onMouseEnter={() => setCursorState('hover')}
              onMouseLeave={() => setCursorState('default')}
              style={{
                fontFamily: 'var(--font-display)',
                fontSize: 'var(--text-4xl)',
                fontWeight: 600,
                color: location.pathname === item.path
                  ? 'var(--star-white)'
                  : 'var(--star-muted)',
              }}
            >
              {item.label}
            </Link>
          ))}
        </div>
      )}

      <style>{`
        @media (max-width: 768px) {
          .nav__links { display: none !important; }
          .nav__menu-btn { display: flex !important; }
        }
      `}</style>
    </>
  );
}
