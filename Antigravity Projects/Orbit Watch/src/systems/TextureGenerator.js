import * as THREE from 'three';

// High-quality procedural planet texture generator
// Eliminates all external CDN/CORS dependencies
// Uses Canvas 2D + noise algorithms for realistic results

function createNoise2D(seed = 0) {
  // Simple hash-based value noise
  const hash = (x, y) => {
    let h = seed + x * 374761393 + y * 668265263;
    h = (h ^ (h >> 13)) * 1274126177;
    h = h ^ (h >> 16);
    return (h & 0x7fffffff) / 0x7fffffff;
  };

  const smoothNoise = (x, y) => {
    const ix = Math.floor(x), iy = Math.floor(y);
    const fx = x - ix, fy = y - iy;
    const sx = fx * fx * (3 - 2 * fx), sy = fy * fy * (3 - 2 * fy);
    const n00 = hash(ix, iy), n10 = hash(ix + 1, iy);
    const n01 = hash(ix, iy + 1), n11 = hash(ix + 1, iy + 1);
    return n00 * (1 - sx) * (1 - sy) + n10 * sx * (1 - sy) + n01 * (1 - sx) * sy + n11 * sx * sy;
  };

  return (x, y, octaves = 6) => {
    let value = 0, amplitude = 1, frequency = 1, maxValue = 0;
    for (let i = 0; i < octaves; i++) {
      value += smoothNoise(x * frequency, y * frequency) * amplitude;
      maxValue += amplitude;
      amplitude *= 0.5;
      frequency *= 2.0;
    }
    return value / maxValue;
  };
}

function createCanvas(width, height) {
  const canvas = document.createElement('canvas');
  canvas.width = width;
  canvas.height = height;
  return { canvas, ctx: canvas.getContext('2d') };
}

function canvasToTexture(canvas) {
  const texture = new THREE.CanvasTexture(canvas);
  texture.colorSpace = THREE.SRGBColorSpace;
  texture.needsUpdate = true;
  return texture;
}

// ─── EARTH ───
export function generateEarthTexture(size = 2048) {
  const { canvas, ctx } = createCanvas(size, size / 2);
  const noise = createNoise2D(42);
  const noise2 = createNoise2D(137);
  const imageData = ctx.createImageData(size, size / 2);
  const data = imageData.data;

  for (let y = 0; y < size / 2; y++) {
    for (let x = 0; x < size; x++) {
      const u = x / size;
      const v = y / (size / 2);
      const lat = (v - 0.5) * Math.PI;
      const lon = u * Math.PI * 2;

      // Spherical coordinates for seamless noise
      const nx = Math.cos(lat) * Math.cos(lon);
      const ny = Math.sin(lat);
      const nz = Math.cos(lat) * Math.sin(lon);

      // Continental noise (large features)
      const continent = noise(nx * 2 + 10, ny * 2 + 10, 4);
      const detail = noise2(nx * 8 + 5, ny * 8 + 5, 5);

      // Ice caps
      const iceCap = Math.max(0, Math.abs(lat) / (Math.PI / 2) - 0.72) * 5;

      let r, g, b;

      if (iceCap > 0.5) {
        // Ice/snow
        r = 230 + detail * 25;
        g = 235 + detail * 20;
        b = 245;
      } else if (continent > 0.48) {
        // Land
        const elevation = (continent - 0.48) * 5;
        const forestNoise = noise2(nx * 15, nz * 15, 3);

        if (elevation > 0.8) {
          // Mountains
          r = 140 + detail * 40;
          g = 130 + detail * 30;
          b = 100 + detail * 20;
        } else if (Math.abs(lat) < 0.6 && forestNoise > 0.4) {
          // Tropical/forest
          r = 30 + detail * 30;
          g = 80 + detail * 50 + elevation * 20;
          b = 20 + detail * 20;
        } else if (Math.abs(lat) < 0.3 && continent < 0.55) {
          // Desert
          r = 190 + detail * 30;
          g = 170 + detail * 25;
          b = 110 + detail * 20;
        } else {
          // Grassland/temperate
          r = 60 + detail * 40;
          g = 100 + detail * 40 + elevation * 15;
          b = 40 + detail * 25;
        }
      } else {
        // Ocean with depth variation
        const depth = (0.48 - continent) * 6;
        const shallow = Math.max(0, 1 - depth * 2);
        r = 10 + shallow * 30;
        g = 40 + shallow * 60 + detail * 15;
        b = 120 + shallow * 40 + detail * 20 - depth * 15;
      }

      const i = (y * size + x) * 4;
      data[i] = Math.min(255, Math.max(0, r));
      data[i + 1] = Math.min(255, Math.max(0, g));
      data[i + 2] = Math.min(255, Math.max(0, b));
      data[i + 3] = 255;
    }
  }

  ctx.putImageData(imageData, 0, 0);
  return canvasToTexture(canvas);
}

export function generateEarthNightTexture(size = 2048) {
  const { canvas, ctx } = createCanvas(size, size / 2);
  const noise = createNoise2D(42); // Same seed as day map for alignment
  const cityNoise = createNoise2D(999);
  const imageData = ctx.createImageData(size, size / 2);
  const data = imageData.data;

  for (let y = 0; y < size / 2; y++) {
    for (let x = 0; x < size; x++) {
      const u = x / size;
      const v = y / (size / 2);
      const lat = (v - 0.5) * Math.PI;
      const lon = u * Math.PI * 2;

      const nx = Math.cos(lat) * Math.cos(lon);
      const ny = Math.sin(lat);
      const nz = Math.cos(lat) * Math.sin(lon);

      const continent = noise(nx * 2 + 10, ny * 2 + 10, 4);
      const city = cityNoise(nx * 30, nz * 30, 4);

      let r = 0, g = 0, b = 0;

      // City lights only on land, concentrated in certain areas
      if (continent > 0.48 && Math.abs(lat) < 1.2) {
        const density = Math.max(0, city - 0.45) * 6;
        const population = Math.max(0, continent - 0.5) * 3;
        const intensity = density * population * (1 - Math.abs(lat) / 1.5);

        r = Math.min(255, intensity * 255);
        g = Math.min(255, intensity * 200);
        b = Math.min(255, intensity * 80);
      }

      const i = (y * size + x) * 4;
      data[i] = r;
      data[i + 1] = g;
      data[i + 2] = b;
      data[i + 3] = 255;
    }
  }

  ctx.putImageData(imageData, 0, 0);
  return canvasToTexture(canvas);
}

// ─── SUN ───
export function generateSunTexture(size = 1024) {
  const { canvas, ctx } = createCanvas(size, size / 2);
  const noise = createNoise2D(7);
  const noise2 = createNoise2D(77);
  const imageData = ctx.createImageData(size, size / 2);
  const data = imageData.data;

  for (let y = 0; y < size / 2; y++) {
    for (let x = 0; x < size; x++) {
      const u = x / size, v = y / (size / 2);
      const lat = (v - 0.5) * Math.PI;
      const lon = u * Math.PI * 2;
      const nx = Math.cos(lat) * Math.cos(lon), ny = Math.sin(lat), nz = Math.cos(lat) * Math.sin(lon);

      const granulation = noise(nx * 12, ny * 12, 5);
      const prominence = noise2(nx * 4, nz * 4, 3);
      const spot = Math.max(0, 1 - noise(nx * 6 + 3, ny * 6 + 3, 3) * 3);

      const r = Math.min(255, 255 * (0.85 + granulation * 0.15) * (1 - spot * 0.3));
      const g = Math.min(255, 180 * (0.8 + granulation * 0.2 + prominence * 0.1) * (1 - spot * 0.4));
      const b = Math.min(255, 50 * (0.7 + granulation * 0.3) * (1 - spot * 0.5));

      const i = (y * size + x) * 4;
      data[i] = r; data[i + 1] = g; data[i + 2] = b; data[i + 3] = 255;
    }
  }

  ctx.putImageData(imageData, 0, 0);
  return canvasToTexture(canvas);
}

// ─── URANUS ───
export function generateUranusTexture(size = 1024) {
  const { canvas, ctx } = createCanvas(size, size / 2);
  const noise = createNoise2D(97);
  const imageData = ctx.createImageData(size, size / 2);
  const data = imageData.data;

  for (let y = 0; y < size / 2; y++) {
    for (let x = 0; x < size; x++) {
      const v = y / (size / 2);
      const band = Math.sin(v * Math.PI * 12) * 0.06;
      const n = noise(x / size * 6, v * 6, 4) * 0.08;

      const base = 0.6 + band + n;
      const r = Math.min(255, Math.max(0, 140 * base + 40));
      const g = Math.min(255, Math.max(0, 210 * base + 20));
      const b = Math.min(255, Math.max(0, 235 * base + 10));

      const i = (y * size + x) * 4;
      data[i] = r; data[i + 1] = g; data[i + 2] = b; data[i + 3] = 255;
    }
  }

  ctx.putImageData(imageData, 0, 0);
  return canvasToTexture(canvas);
}

// ─── NEPTUNE ───
export function generateNeptuneTexture(size = 1024) {
  const { canvas, ctx } = createCanvas(size, size / 2);
  const noise = createNoise2D(164);
  const noise2 = createNoise2D(333);
  const imageData = ctx.createImageData(size, size / 2);
  const data = imageData.data;

  for (let y = 0; y < size / 2; y++) {
    for (let x = 0; x < size; x++) {
      const u = x / size, v = y / (size / 2);
      const lat = (v - 0.5) * Math.PI;
      const band = Math.sin(v * Math.PI * 16) * 0.05;
      const streak = noise(u * 20 + v * 2, v * 3, 4) * 0.08;
      const storm = noise2(u * 8, v * 8, 3);

      const base = 0.5 + band + streak;
      let r = 30 * base + 10;
      let g = 60 * base + 30;
      let b = 180 * base + 40;

      // Great Dark Spot region
      const spotDist = Math.sqrt((u - 0.35) ** 2 * 9 + (v - 0.6) ** 2 * 16);
      if (spotDist < 0.3) {
        const spotFade = 1 - spotDist / 0.3;
        r -= spotFade * 15;
        g -= spotFade * 25;
        b -= spotFade * 40;
      }

      // White methane clouds
      if (storm > 0.72 && Math.abs(lat) < 1.0) {
        const cloudIntensity = (storm - 0.72) * 8;
        r += cloudIntensity * 60;
        g += cloudIntensity * 60;
        b += cloudIntensity * 30;
      }

      const i = (y * size + x) * 4;
      data[i] = Math.min(255, Math.max(0, r));
      data[i + 1] = Math.min(255, Math.max(0, g));
      data[i + 2] = Math.min(255, Math.max(0, b));
      data[i + 3] = 255;
    }
  }

  ctx.putImageData(imageData, 0, 0);
  return canvasToTexture(canvas);
}

// ─── MARS ───
export function generateMarsTexture(size = 1024) {
  const { canvas, ctx } = createCanvas(size, size / 2);
  const noise = createNoise2D(4);
  const noise2 = createNoise2D(44);
  const imageData = ctx.createImageData(size, size / 2);
  const data = imageData.data;

  for (let y = 0; y < size / 2; y++) {
    for (let x = 0; x < size; x++) {
      const u = x / size, v = y / (size / 2);
      const lat = (v - 0.5) * Math.PI;
      const lon = u * Math.PI * 2;
      const nx = Math.cos(lat) * Math.cos(lon), ny = Math.sin(lat), nz = Math.cos(lat) * Math.sin(lon);

      const terrain = noise(nx * 4 + 5, ny * 4 + 5, 5);
      const detail = noise2(nx * 12, nz * 12, 4);
      const iceCap = Math.max(0, Math.abs(lat) / (Math.PI / 2) - 0.82) * 8;

      let r, g, b;
      if (iceCap > 0.5) {
        r = 220 + detail * 30; g = 215 + detail * 30; b = 210 + detail * 30;
      } else if (terrain > 0.55) {
        // Highlands (lighter red)
        r = 200 + detail * 30; g = 130 + detail * 20; b = 80 + detail * 15;
      } else if (terrain > 0.4) {
        // Plains
        r = 175 + detail * 25; g = 100 + detail * 15; b = 60 + detail * 10;
      } else {
        // Lowlands (darker)
        r = 140 + detail * 20; g = 75 + detail * 12; b = 45 + detail * 8;
      }

      const i = (y * size + x) * 4;
      data[i] = Math.min(255, r); data[i + 1] = Math.min(255, g); data[i + 2] = Math.min(255, b); data[i + 3] = 255;
    }
  }

  ctx.putImageData(imageData, 0, 0);
  return canvasToTexture(canvas);
}

// ─── JUPITER ───
export function generateJupiterTexture(size = 1024) {
  const { canvas, ctx } = createCanvas(size, size / 2);
  const noise = createNoise2D(5);
  const imageData = ctx.createImageData(size, size / 2);
  const data = imageData.data;

  for (let y = 0; y < size / 2; y++) {
    for (let x = 0; x < size; x++) {
      const v = y / (size / 2);
      const band = Math.sin(v * Math.PI * 20) * 0.5 + Math.sin(v * Math.PI * 7) * 0.3;
      const n = noise(x / size * 10 + v * 2, v * 8, 4) * 0.15;
      const t = band + n;

      // Color bands
      let r, g, b;
      if (t > 0.3) {
        r = 230 + n * 20; g = 200 + n * 15; b = 150 + n * 10; // Bright zone
      } else if (t > 0) {
        r = 200 + n * 20; g = 160 + n * 15; b = 100 + n * 10; // Zone  
      } else if (t > -0.3) {
        r = 160 + n * 15; g = 110 + n * 10; b = 70 + n * 8; // Belt
      } else {
        r = 130 + n * 15; g = 85 + n * 10; b = 55 + n * 8; // Dark belt
      }

      // Great Red Spot
      const spotU = x / size, spotV = v;
      const spotDist = Math.sqrt((spotU - 0.4) ** 2 * 12 + (spotV - 0.62) ** 2 * 25);
      if (spotDist < 0.15) {
        const spotFade = 1 - spotDist / 0.15;
        r = r * (1 - spotFade * 0.3) + 200 * spotFade * 0.4;
        g = g * (1 - spotFade * 0.4) + 80 * spotFade * 0.3;
        b = b * (1 - spotFade * 0.5) + 40 * spotFade * 0.2;
      }

      const i = (y * size + x) * 4;
      data[i] = Math.min(255, Math.max(0, r)); data[i + 1] = Math.min(255, Math.max(0, g));
      data[i + 2] = Math.min(255, Math.max(0, b)); data[i + 3] = 255;
    }
  }

  ctx.putImageData(imageData, 0, 0);
  return canvasToTexture(canvas);
}

// ─── SATURN ───
export function generateSaturnTexture(size = 1024) {
  const { canvas, ctx } = createCanvas(size, size / 2);
  const noise = createNoise2D(6);
  const imageData = ctx.createImageData(size, size / 2);
  const data = imageData.data;

  for (let y = 0; y < size / 2; y++) {
    for (let x = 0; x < size; x++) {
      const v = y / (size / 2);
      const band = Math.sin(v * Math.PI * 14) * 0.3 + Math.sin(v * Math.PI * 5) * 0.2;
      const n = noise(x / size * 8, v * 6, 4) * 0.1;
      const t = 0.5 + band + n;

      const r = Math.min(255, 200 * t + 30);
      const g = Math.min(255, 180 * t + 20);
      const b = Math.min(255, 120 * t + 15);

      const i = (y * size + x) * 4;
      data[i] = r; data[i + 1] = g; data[i + 2] = b; data[i + 3] = 255;
    }
  }

  ctx.putImageData(imageData, 0, 0);
  return canvasToTexture(canvas);
}

export function generateSaturnRingTexture(size = 1024) {
  const { canvas, ctx } = createCanvas(size, 64);
  const noise = createNoise2D(66);
  const imageData = ctx.createImageData(size, 64);
  const data = imageData.data;

  for (let x = 0; x < size; x++) {
    const u = x / size;
    // Multiple ring bands
    const ringDensity =
      Math.max(0, Math.sin(u * Math.PI * 60) * 0.5 + 0.5) *
      Math.max(0, 1 - Math.abs(u - 0.5) * 2.5) *
      (0.3 + noise(u * 100, 0, 3) * 0.7);

    const r = Math.min(255, 200 + noise(u * 30, 0, 2) * 40);
    const g = Math.min(255, 180 + noise(u * 30, 1, 2) * 35);
    const b = Math.min(255, 140 + noise(u * 30, 2, 2) * 30);
    const a = Math.min(255, ringDensity * 200);

    for (let y = 0; y < 64; y++) {
      const i = (y * size + x) * 4;
      data[i] = r; data[i + 1] = g; data[i + 2] = b; data[i + 3] = a;
    }
  }

  ctx.putImageData(imageData, 0, 0);
  const tex = canvasToTexture(canvas);
  tex.wrapS = THREE.RepeatWrapping;
  return tex;
}

// ─── MERCURY ───
export function generateMercuryTexture(size = 512) {
  const { canvas, ctx } = createCanvas(size, size / 2);
  const noise = createNoise2D(1);
  const craters = createNoise2D(11);
  const imageData = ctx.createImageData(size, size / 2);
  const data = imageData.data;

  for (let y = 0; y < size / 2; y++) {
    for (let x = 0; x < size; x++) {
      const u = x / size, v = y / (size / 2);
      const n = noise(u * 8, v * 8, 5);
      const c = craters(u * 20, v * 20, 3);
      const crater = c > 0.7 ? (c - 0.7) * 4 : 0;

      const base = 0.4 + n * 0.25 - crater * 0.15;
      const r = Math.min(255, base * 180 + 40);
      const g = Math.min(255, base * 170 + 35);
      const b = Math.min(255, base * 160 + 30);

      const i = (y * size + x) * 4;
      data[i] = r; data[i + 1] = g; data[i + 2] = b; data[i + 3] = 255;
    }
  }

  ctx.putImageData(imageData, 0, 0);
  return canvasToTexture(canvas);
}

// ─── VENUS ───
export function generateVenusTexture(size = 1024) {
  const { canvas, ctx } = createCanvas(size, size / 2);
  const noise = createNoise2D(2);
  const imageData = ctx.createImageData(size, size / 2);
  const data = imageData.data;

  for (let y = 0; y < size / 2; y++) {
    for (let x = 0; x < size; x++) {
      const u = x / size, v = y / (size / 2);
      const n = noise(u * 6 + v, v * 4, 5);
      const swirl = Math.sin(u * 12 + n * 4) * 0.15;

      const base = 0.5 + n * 0.2 + swirl;
      const r = Math.min(255, base * 220 + 20);
      const g = Math.min(255, base * 170 + 10);
      const b = Math.min(255, base * 80 + 5);

      const i = (y * size + x) * 4;
      data[i] = r; data[i + 1] = g; data[i + 2] = b; data[i + 3] = 255;
    }
  }

  ctx.putImageData(imageData, 0, 0);
  return canvasToTexture(canvas);
}

// ─── URANUS RING ───
export function generateUranusRingTexture(size = 512) {
  const { canvas, ctx } = createCanvas(size, 32);
  const imageData = ctx.createImageData(size, 32);
  const data = imageData.data;

  for (let x = 0; x < size; x++) {
    const u = x / size;
    const ringDensity = Math.max(0, Math.sin(u * Math.PI * 40) * 0.3 + 0.3) * Math.max(0, 1 - Math.abs(u - 0.5) * 3);
    const a = Math.min(255, ringDensity * 120);

    for (let y = 0; y < 32; y++) {
      const i = (y * size + x) * 4;
      data[i] = 150; data[i + 1] = 200; data[i + 2] = 230; data[i + 3] = a;
    }
  }

  ctx.putImageData(imageData, 0, 0);
  const tex = canvasToTexture(canvas);
  tex.wrapS = THREE.RepeatWrapping;
  return tex;
}

// ─── Cache for texture reuse ───
const textureCache = {};

export function getTexture(name) {
  if (textureCache[name]) return textureCache[name];

  const generators = {
    earth: generateEarthTexture,
    earthNight: generateEarthNightTexture,
    sun: generateSunTexture,
    uranus: generateUranusTexture,
    uranusRing: generateUranusRingTexture,
    neptune: generateNeptuneTexture,
    mars: generateMarsTexture,
    jupiter: generateJupiterTexture,
    saturn: generateSaturnTexture,
    saturnRing: generateSaturnRingTexture,
    mercury: generateMercuryTexture,
    venus: generateVenusTexture,
  };

  if (generators[name]) {
    textureCache[name] = generators[name]();
    return textureCache[name];
  }

  return null;
}
