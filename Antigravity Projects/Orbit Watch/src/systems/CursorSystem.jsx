import React, { createContext, useContext, useRef, useEffect, useState, useCallback } from 'react';
import gravityEngine from './GravityEngine';

const CursorContext = createContext({
  cursorState: 'default',
  setCursorState: () => {},
  registerMagnetic: () => () => {},
});

export const useCursor = () => useContext(CursorContext);

/* 5-Layer Cursor Stack
   Layer 1: Inner dot      — lerp 0.35 — 6px  — snappy
   Layer 2: Ring            — lerp 0.15 — 32px — lagging ring
   Layer 3: Plasma glow     — lerp 0.08 — 64px — soft radial
   Layer 4: Trail           — lerp 0.03 — 12 points
   Layer 5: Field lines     — lerp 0.02 — 200px — radial SVG
*/

export function CursorProvider({ children }) {
  const [cursorState, setCursorState] = useState('default');
  const magneticElements = useRef(new Set());

  const registerMagnetic = useCallback((element, config = {}) => {
    const entry = { element, ...config };
    magneticElements.current.add(entry);
    return () => magneticElements.current.delete(entry);
  }, []);

  return (
    <CursorContext.Provider value={{ cursorState, setCursorState, registerMagnetic }}>
      {children}
      <CursorRenderer cursorState={cursorState} magneticElements={magneticElements} />
    </CursorContext.Provider>
  );
}

function CursorRenderer({ cursorState, magneticElements }) {
  const dotRef = useRef(null);
  const ringRef = useRef(null);
  const plasmaRef = useRef(null);
  const trailRefs = useRef([]);
  const fieldRef = useRef(null);

  // Smoothed positions for each layer
  const layerPos = useRef({
    dot: { x: 0, y: 0 },
    ring: { x: 0, y: 0 },
    plasma: { x: 0, y: 0 },
    trail: Array.from({ length: 12 }, () => ({ x: 0, y: 0 })),
    field: { x: 0, y: 0 },
  });

  const ringScale = useRef(1);
  const dotScale = useRef(1);
  const fieldRotation = useRef(0);

  useEffect(() => {
    gravityEngine.init();

    let animId;
    const animate = () => {
      const mx = gravityEngine.mouse.x;
      const my = gravityEngine.mouse.y;
      const speed = gravityEngine.speed;
      const lp = layerPos.current;

      // Layer 1: Dot — lerp 0.35 (snappy)
      lp.dot.x += (mx - lp.dot.x) * 0.35;
      lp.dot.y += (my - lp.dot.y) * 0.35;

      // Layer 2: Ring — lerp 0.15
      lp.ring.x += (mx - lp.ring.x) * 0.15;
      lp.ring.y += (my - lp.ring.y) * 0.15;

      // Layer 3: Plasma — lerp 0.08
      lp.plasma.x += (mx - lp.plasma.x) * 0.08;
      lp.plasma.y += (my - lp.plasma.y) * 0.08;

      // Layer 4: Trail — cascade lerp 0.03-0.06
      for (let i = 0; i < lp.trail.length; i++) {
        const target = i === 0 ? { x: mx, y: my } : lp.trail[i - 1];
        const factor = 0.06 - i * 0.003;
        lp.trail[i].x += (target.x - lp.trail[i].x) * Math.max(factor, 0.01);
        lp.trail[i].y += (target.y - lp.trail[i].y) * Math.max(factor, 0.01);
      }

      // Layer 5: Field — lerp 0.02 (slowest)
      lp.field.x += (mx - lp.field.x) * 0.02;
      lp.field.y += (my - lp.field.y) * 0.02;

      // Ring scale based on state
      const targetRingScale = cursorState === 'hover' ? 2.0 :
                              cursorState === 'click' ? 0.6 :
                              cursorState === 'text' ? 0.3 : 1.0;
      ringScale.current += (targetRingScale - ringScale.current) * 0.12;

      // Dot scale
      const targetDotScale = cursorState === 'click' ? 1.5 : 1.0;
      dotScale.current += (targetDotScale - dotScale.current) * 0.15;

      // Field rotation based on velocity
      fieldRotation.current += speed * 0.3;

      // Apply transforms (GPU composited with translate3d)
      if (dotRef.current) {
        dotRef.current.style.transform =
          `translate3d(${lp.dot.x - 3}px, ${lp.dot.y - 3}px, 0) scale(${dotScale.current})`;
      }
      if (ringRef.current) {
        ringRef.current.style.transform =
          `translate3d(${lp.ring.x - 16}px, ${lp.ring.y - 16}px, 0) scale(${ringScale.current})`;
        // Text state: ring becomes vertical line
        ringRef.current.style.borderRadius = cursorState === 'text' ? '2px' : '50%';
        ringRef.current.style.width = cursorState === 'text' ? '2px' : '32px';
        ringRef.current.style.height = cursorState === 'text' ? '24px' : '32px';
      }
      if (plasmaRef.current) {
        const plasmaOpacity = cursorState === 'hover' ? 0.5 : 
                              cursorState === 'text' ? 0.1 : 0.25;
        plasmaRef.current.style.transform =
          `translate3d(${lp.plasma.x - 32}px, ${lp.plasma.y - 32}px, 0)`;
        plasmaRef.current.style.opacity = plasmaOpacity;
      }

      // Trail points
      trailRefs.current.forEach((ref, i) => {
        if (ref) {
          const alpha = 1 - i / lp.trail.length;
          ref.style.transform =
            `translate3d(${lp.trail[i].x - 2}px, ${lp.trail[i].y - 2}px, 0)`;
          ref.style.opacity = alpha * 0.3 * (speed > 2 ? 1 : speed / 2);
        }
      });

      if (fieldRef.current) {
        const fieldOpacity = speed > 3 ? 0.15 : 0;
        fieldRef.current.style.transform =
          `translate3d(${lp.field.x - 100}px, ${lp.field.y - 100}px, 0) rotate(${fieldRotation.current}deg)`;
        fieldRef.current.style.opacity = fieldOpacity;
      }

      // Magnetic field processing
      magneticElements.current.forEach(({ element, type = 'attract', radius = 100, strength = 0.3 }) => {
        if (!element || !element.getBoundingClientRect) return;
        const rect = element.getBoundingClientRect();
        const cx = rect.x + rect.width / 2;
        const cy = rect.y + rect.height / 2;
        const dx = mx - cx;
        const dy = my - cy;
        const dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < radius) {
          const force = (1 - dist / radius) * strength;
          const tx = type === 'attract' ? dx * force : -dx * force;
          const ty = type === 'attract' ? dy * force : -dy * force;
          element.style.transform = `translate(${tx}px, ${ty}px)`;
        } else {
          element.style.transform = 'translate(0px, 0px)';
        }
      });

      animId = requestAnimationFrame(animate);
    };

    animId = requestAnimationFrame(animate);
    return () => cancelAnimationFrame(animId);
  }, [cursorState]);

  // Hide on touch devices
  const [isTouchDevice, setIsTouchDevice] = useState(false);
  useEffect(() => {
    setIsTouchDevice('ontouchstart' in window && window.innerWidth < 1024);
  }, []);

  if (isTouchDevice) return null;

  return (
    <div className="cursor-system" style={{ position: 'fixed', inset: 0, zIndex: 'var(--z-cursor)', pointerEvents: 'none' }}>
      {/* Layer 5: Field lines */}
      <div
        ref={fieldRef}
        className="cursor-field"
        style={{
          position: 'absolute',
          width: 200,
          height: 200,
          opacity: 0,
          transition: 'opacity 0.3s',
        }}
      >
        <svg width="200" height="200" viewBox="0 0 200 200">
          {Array.from({ length: 8 }, (_, i) => {
            const angle = (i / 8) * Math.PI * 2;
            const x1 = 100 + Math.cos(angle) * 30;
            const y1 = 100 + Math.sin(angle) * 30;
            const x2 = 100 + Math.cos(angle) * 90;
            const y2 = 100 + Math.sin(angle) * 90;
            return (
              <line
                key={i}
                x1={x1} y1={y1} x2={x2} y2={y2}
                stroke="rgba(255,255,255,0.08)"
                strokeWidth="1"
              />
            );
          })}
        </svg>
      </div>

      {/* Layer 4: Trail */}
      {Array.from({ length: 12 }, (_, i) => (
        <div
          key={i}
          ref={el => trailRefs.current[i] = el}
          style={{
            position: 'absolute',
            width: 4,
            height: 4,
            borderRadius: '50%',
            background: 'var(--plasma-cyan)',
            opacity: 0,
          }}
        />
      ))}

      {/* Layer 3: Plasma glow */}
      <div
        ref={plasmaRef}
        style={{
          position: 'absolute',
          width: 64,
          height: 64,
          borderRadius: '50%',
          background: 'radial-gradient(circle, var(--cursor-plasma) 0%, transparent 70%)',
          filter: 'blur(8px)',
          opacity: 0.25,
        }}
      />

      {/* Layer 2: Ring */}
      <div
        ref={ringRef}
        style={{
          position: 'absolute',
          width: 32,
          height: 32,
          borderRadius: '50%',
          border: '1px solid var(--cursor-ring)',
          transition: 'width 0.3s, height 0.3s, border-radius 0.3s',
        }}
      />

      {/* Layer 1: Inner dot */}
      <div
        ref={dotRef}
        style={{
          position: 'absolute',
          width: 6,
          height: 6,
          borderRadius: '50%',
          background: 'var(--cursor-dot)',
          boxShadow: '0 0 10px var(--cursor-plasma)',
        }}
      />
    </div>
  );
}

export default CursorProvider;
