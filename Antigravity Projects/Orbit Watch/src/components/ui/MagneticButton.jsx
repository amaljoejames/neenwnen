import React, { useRef, useEffect, useState } from 'react';
import { useCursor } from '../../systems/CursorSystem';
import gravityEngine from '../../systems/GravityEngine';

export default function MagneticButton({
  children,
  onClick,
  variant = 'primary', // 'primary' | 'secondary' | 'ghost'
  size = 'md',         // 'sm' | 'md' | 'lg'
  className = '',
  magneticRadius = 120,
  magneticStrength = 0.35,
  ...props
}) {
  const buttonRef = useRef(null);
  const innerRef = useRef(null);
  const glowRef = useRef(null);
  const { setCursorState, registerMagnetic } = useCursor();
  const pos = useRef({ x: 0, y: 0 });

  // Register for magnetic field
  useEffect(() => {
    if (!buttonRef.current) return;
    const unregister = registerMagnetic(buttonRef.current, {
      type: 'attract',
      radius: magneticRadius,
      strength: magneticStrength,
    });
    return unregister;
  }, [registerMagnetic, magneticRadius, magneticStrength]);

  // Spring-based hover animation
  useEffect(() => {
    let animId;
    const animate = () => {
      if (!buttonRef.current) return;
      const rect = buttonRef.current.getBoundingClientRect();
      const cx = rect.x + rect.width / 2;
      const cy = rect.y + rect.height / 2;
      const mx = gravityEngine.mouse.x;
      const my = gravityEngine.mouse.y;
      const dx = mx - cx;
      const dy = my - cy;
      const dist = Math.sqrt(dx * dx + dy * dy);

      if (dist < magneticRadius && innerRef.current) {
        const force = (1 - dist / magneticRadius);
        const tx = dx * force * 0.15;
        const ty = dy * force * 0.15;
        pos.current.x += (tx - pos.current.x) * 0.1;
        pos.current.y += (ty - pos.current.y) * 0.1;
        innerRef.current.style.transform = `translate(${pos.current.x}px, ${pos.current.y}px)`;

        if (glowRef.current) {
          glowRef.current.style.opacity = force * 0.6;
        }
      } else {
        pos.current.x += (0 - pos.current.x) * 0.1;
        pos.current.y += (0 - pos.current.y) * 0.1;
        if (innerRef.current) {
          innerRef.current.style.transform = `translate(${pos.current.x}px, ${pos.current.y}px)`;
        }
        if (glowRef.current) {
          glowRef.current.style.opacity = 0;
        }
      }

      animId = requestAnimationFrame(animate);
    };
    animId = requestAnimationFrame(animate);
    return () => cancelAnimationFrame(animId);
  }, [magneticRadius]);

  const sizeClasses = {
    sm: 'magnetic-btn--sm',
    md: 'magnetic-btn--md',
    lg: 'magnetic-btn--lg',
  };

  return (
    <button
      ref={buttonRef}
      className={`magnetic-btn magnetic-btn--${variant} ${sizeClasses[size]} ${className}`}
      onClick={onClick}
      onMouseEnter={() => setCursorState('hover')}
      onMouseLeave={() => setCursorState('default')}
      onMouseDown={() => setCursorState('click')}
      onMouseUp={() => setCursorState('hover')}
      {...props}
    >
      <span ref={innerRef} className="magnetic-btn__inner">
        {children}
      </span>
      <div ref={glowRef} className="magnetic-btn__glow" />
    </button>
  );
}
