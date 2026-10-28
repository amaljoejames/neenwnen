import React, { useState, useEffect, lazy, Suspense } from 'react';
import { BrowserRouter, Routes, Route, useLocation } from 'react-router-dom';
import LenisProvider from './providers/LenisProvider';
import { CursorProvider } from './systems/CursorSystem';
import Navigation from './components/ui/Navigation';
import LoadingScreen from './components/ui/LoadingScreen';
import './App.css';

// Lazy-load pages for performance
const Home = lazy(() => import('./pages/Home'));
const Collection = lazy(() => import('./pages/Collection'));
const WatchDetail = lazy(() => import('./pages/WatchDetail'));
const Explore = lazy(() => import('./pages/Explore'));
const EarthPage = lazy(() => import('./pages/EarthPage'));
const UranusPage = lazy(() => import('./pages/UranusPage'));
const NeptunePage = lazy(() => import('./pages/NeptunePage'));
const Technology = lazy(() => import('./pages/Technology'));
const About = lazy(() => import('./pages/About'));
const Configurator = lazy(() => import('./pages/Configurator'));
const Journal = lazy(() => import('./pages/Journal'));
const Contact = lazy(() => import('./pages/Contact'));

// Scroll to top on route change
function ScrollToTop() {
  const { pathname } = useLocation();
  useEffect(() => {
    window.scrollTo(0, 0);
  }, [pathname]);
  return null;
}

// Page loading fallback
function PageLoader() {
  return (
    <div style={{
      width: '100vw',
      height: '100vh',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      background: 'var(--void-black)',
    }}>
      <div style={{
        width: 40,
        height: 40,
        borderRadius: '50%',
        border: '2px solid transparent',
        borderTopColor: 'var(--plasma-cyan)',
        animation: 'spin 1s linear infinite',
      }} />
    </div>
  );
}

function App() {
  const [isLoaded, setIsLoaded] = useState(false);

  return (
    <BrowserRouter>
      <LenisProvider>
        <CursorProvider>
          {!isLoaded && (
            <LoadingScreen onComplete={() => setIsLoaded(true)} />
          )}

          <Navigation />

          <ScrollToTop />

          <Suspense fallback={<PageLoader />}>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/collection" element={<Collection />} />
              <Route path="/watch/:id" element={<WatchDetail />} />
              <Route path="/explore" element={<Explore />} />
              <Route path="/earth" element={<EarthPage />} />
              <Route path="/uranus" element={<UranusPage />} />
              <Route path="/neptune" element={<NeptunePage />} />
              <Route path="/technology" element={<Technology />} />
              <Route path="/about" element={<About />} />
              <Route path="/configurator" element={<Configurator />} />
              <Route path="/journal" element={<Journal />} />
              <Route path="/contact" element={<Contact />} />
            </Routes>
          </Suspense>
        </CursorProvider>
      </LenisProvider>
    </BrowserRouter>
  );
}

export default App;
