import React, { useRef, useEffect, useState } from 'react';
import { useCursor } from '../../systems/CursorSystem';
import gravityEngine from '../../systems/GravityEngine';

export default function GlassCard({
  children,
  className = '',
  tilt = true,
  glow = true,
  onClick,
  style = {},
}) {
  const cardRef = useRef(null);
  const glowRef = useRef(null);
  const { setCursorState } = useCursor();
  const transform = useRef({ rotateX: 0, rotateY: 0 });

  useEffect(() => {
    if (!tilt) return;

    let animId;
    const animate = () => {
      if (!cardRef.current) return;

      const rect = cardRef.current.getBoundingClientRect();
      const cx = rect.x + rect.width / 2;
      const cy = rect.y + rect.height / 2;
      const mx = gravityEngine.mouse.x;
      const my = gravityEngine.mouse.y;

      // Check if mouse is near the card
      const isNear = mx > rect.x - 50 && mx < rect.right + 50 &&
                     my > rect.y - 50 && my < rect.bottom + 50;

      if (isNear) {
        const dx = (mx - cx) / (rect.width / 2);
        const dy = (my - cy) / (rect.height / 2);

        // Spring toward target rotation
        const targetRotateY = dx * 8;
        const targetRotateX = -dy * 8;
        transform.current.rotateX += (targetRotateX - transform.current.rotateX) * 0.08;
        transform.current.rotateY += (targetRotateY - transform.current.rotateY) * 0.08;

        // Glow follows cursor
        if (glowRef.current) {
          const px = ((mx - rect.x) / rect.width) * 100;
          const py = ((my - rect.y) / rect.height) * 100;
          glowRef.current.style.background = `radial-gradient(circle at ${px}% ${py}%, rgba(124, 58, 237, 0.15) 0%, transparent 60%)`;
          glowRef.current.style.opacity = '1';
        }
      } else {
        // Spring back to neutral
        transform.current.rotateX += (0 - transform.current.rotateX) * 0.06;
        transform.current.rotateY += (0 - transform.current.rotateY) * 0.06;
        if (glowRef.current) {
          glowRef.current.style.opacity = '0';
        }
      }

      cardRef.current.style.transform = `perspective(800px) rotateX(${transform.current.rotateX}deg) rotateY(${transform.current.rotateY}deg)`;

      animId = requestAnimationFrame(animate);
    };

    animId = requestAnimationFrame(animate);
    return () => cancelAnimationFrame(animId);
  }, [tilt]);

  return (
    <div
      ref={cardRef}
      className={`glass-card ${className}`}
      onClick={onClick}
      onMouseEnter={() => setCursorState('hover')}
      onMouseLeave={() => setCursorState('default')}
      style={{
        position: 'relative',
        overflow: 'hidden',
        willChange: 'transform',
        ...style,
      }}
    >
      {glow && (
        <div
          ref={glowRef}
          className="glass-card__glow"
          style={{
            position: 'absolute',
            inset: 0,
            opacity: 0,
            transition: 'opacity 0.4s',
            pointerEvents: 'none',
            zIndex: 1,
          }}
        />
      )}
      <div style={{ position: 'relative', zIndex: 2 }}>
        {children}
      </div>
    </div>
  );
}
