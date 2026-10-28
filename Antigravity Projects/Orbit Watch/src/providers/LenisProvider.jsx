import React from 'react';
import { ReactLenis } from 'lenis/react';

const lenisOptions = {
  lerp: 0.05,           // Heavy inertia — the key to "weight"
  wheelMultiplier: 0.8,  // Slightly slower wheel = more intentional
  smoothWheel: true,
  touchMultiplier: 1.5,
};

export default function LenisProvider({ children }) {
  // Respect reduced motion preference
  const isReducedMotion = 
    typeof window !== 'undefined' &&
    window.matchMedia('(prefers-reduced-motion: reduce)').matches;

  if (isReducedMotion) {
    return <>{children}</>;
  }

  return (
    <ReactLenis root options={lenisOptions}>
      {children}
    </ReactLenis>
  );
}
