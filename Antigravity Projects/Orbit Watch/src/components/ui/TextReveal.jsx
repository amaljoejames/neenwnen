import React, { useRef, useEffect, useState } from 'react';

export default function TextReveal({
  children,
  as: Tag = 'h2',
  className = '',
  delay = 0,
  stagger = 0.03,
  type = 'chars', // 'chars' | 'words' | 'lines'
  animation = 'spring-up', // 'spring-up' | 'blur-in' | 'letter-wave'
}) {
  const containerRef = useRef(null);
  const [isVisible, setIsVisible] = useState(false);
  const text = typeof children === 'string' ? children : '';

  useEffect(() => {
    const el = containerRef.current;
    if (!el) return;

    const observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting) {
          setIsVisible(true);
          observer.disconnect();
        }
      },
      { threshold: 0.2 }
    );

    observer.observe(el);
    return () => observer.disconnect();
  }, []);

  const getItems = () => {
    if (type === 'chars') {
      return text.split('').map((char, i) => ({
        content: char === ' ' ? '\u00A0' : char,
        key: i,
      }));
    }
    if (type === 'words') {
      return text.split(' ').map((word, i) => ({
        content: word,
        key: i,
      }));
    }
    return [{ content: text, key: 0 }];
  };

  const items = getItems();

  const getAnimationStyle = (index) => {
    const itemDelay = delay + index * stagger;

    if (!isVisible) {
      switch (animation) {
        case 'spring-up':
          return {
            opacity: 0,
            transform: 'translateY(100%) rotateX(-80deg)',
            transition: 'none',
          };
        case 'blur-in':
          return {
            opacity: 0,
            filter: 'blur(10px)',
            transform: 'translateY(20px)',
            transition: 'none',
          };
        case 'letter-wave':
          return {
            opacity: 0,
            transform: 'translateY(40px) scale(0.8)',
            transition: 'none',
          };
        default:
          return { opacity: 0 };
      }
    }

    switch (animation) {
      case 'spring-up':
        return {
          opacity: 1,
          transform: 'translateY(0) rotateX(0)',
          transition: `opacity 0.6s cubic-bezier(0.16, 1, 0.3, 1) ${itemDelay}s, transform 0.8s cubic-bezier(0.16, 1, 0.3, 1) ${itemDelay}s`,
        };
      case 'blur-in':
        return {
          opacity: 1,
          filter: 'blur(0px)',
          transform: 'translateY(0)',
          transition: `all 0.8s cubic-bezier(0.16, 1, 0.3, 1) ${itemDelay}s`,
        };
      case 'letter-wave':
        return {
          opacity: 1,
          transform: 'translateY(0) scale(1)',
          transition: `all 0.7s cubic-bezier(0.34, 1.56, 0.64, 1) ${itemDelay}s`,
        };
      default:
        return { opacity: 1 };
    }
  };

  return (
    <Tag
      ref={containerRef}
      className={`text-reveal ${className}`}
      style={{
        display: 'flex',
        flexWrap: 'wrap',
        overflow: 'hidden',
        perspective: '800px',
        gap: type === 'words' ? '0.3em' : 0,
      }}
    >
      {items.map(({ content, key }) => (
        <span
          key={key}
          style={{
            display: 'inline-block',
            willChange: 'transform, opacity',
            ...getAnimationStyle(key),
          }}
        >
          {content}
        </span>
      ))}
    </Tag>
  );
}
