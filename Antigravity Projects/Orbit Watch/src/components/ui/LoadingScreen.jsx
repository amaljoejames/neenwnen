import React, { useEffect, useState, useRef } from 'react';

export default function LoadingScreen({ onComplete }) {
  const [progress, setProgress] = useState(0);
  const [isExiting, setIsExiting] = useState(false);
  const [isComplete, setIsComplete] = useState(false);
  const counterRef = useRef(null);

  useEffect(() => {
    let current = 0;
    const target = 100;
    const duration = 2500;
    const start = performance.now();

    const tick = (now) => {
      const elapsed = now - start;
      const t = Math.min(elapsed / duration, 1);
      // Ease-out exponential
      const eased = 1 - Math.pow(1 - t, 3);
      current = Math.floor(eased * target);
      setProgress(current);

      if (t < 1) {
        requestAnimationFrame(tick);
      } else {
        setProgress(100);
        setTimeout(() => {
          setIsExiting(true);
          setTimeout(() => {
            setIsComplete(true);
            onComplete?.();
          }, 800);
        }, 300);
      }
    };

    requestAnimationFrame(tick);
  }, [onComplete]);

  if (isComplete) return null;

  return (
    <div
      className="loading-screen"
      style={{
        position: 'fixed',
        inset: 0,
        zIndex: 'var(--z-loading)',
        background: 'var(--void-black)',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        opacity: isExiting ? 0 : 1,
        transition: 'opacity 0.8s cubic-bezier(0.16, 1, 0.3, 1)',
      }}
    >
      {/* Orbit logo animation */}
      <div style={{
        marginBottom: 'var(--space-12)',
        position: 'relative',
      }}>
        <div style={{
          fontFamily: 'var(--font-display)',
          fontSize: 'var(--text-4xl)',
          fontWeight: 700,
          letterSpacing: '0.2em',
          textTransform: 'uppercase',
          background: 'linear-gradient(135deg, var(--star-white), var(--plasma-cyan))',
          WebkitBackgroundClip: 'text',
          WebkitTextFillColor: 'transparent',
          backgroundClip: 'text',
        }}>
          ORBIT
        </div>

        {/* Orbiting dot */}
        <div style={{
          position: 'absolute',
          top: '50%',
          left: '50%',
          width: 6,
          height: 6,
          borderRadius: '50%',
          background: 'var(--plasma-cyan)',
          boxShadow: '0 0 20px var(--plasma-cyan), 0 0 40px var(--plasma-glow)',
          animation: 'orbitDot 2s linear infinite',
          transformOrigin: '0 0',
        }} />
      </div>

      {/* Progress bar */}
      <div style={{
        width: 200,
        height: 1,
        background: 'rgba(255,255,255,0.1)',
        borderRadius: 1,
        overflow: 'hidden',
        position: 'relative',
      }}>
        <div style={{
          height: '100%',
          width: `${progress}%`,
          background: 'linear-gradient(90deg, var(--nebula-purple), var(--plasma-cyan))',
          transition: 'width 0.1s linear',
          borderRadius: 1,
          boxShadow: '0 0 10px var(--plasma-glow)',
        }} />
      </div>

      {/* Progress number */}
      <div
        ref={counterRef}
        style={{
          marginTop: 'var(--space-4)',
          fontFamily: 'var(--font-display)',
          fontSize: 'var(--text-sm)',
          color: 'var(--star-muted)',
          letterSpacing: '0.1em',
          fontVariantNumeric: 'tabular-nums',
        }}
      >
        {progress}%
      </div>

      <style>{`
        @keyframes orbitDot {
          from { transform: rotate(0deg) translateX(60px) rotate(0deg); }
          to { transform: rotate(360deg) translateX(60px) rotate(-360deg); }
        }
      `}</style>
    </div>
  );
}
