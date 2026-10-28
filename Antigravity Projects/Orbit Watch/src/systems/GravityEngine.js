/* GravityEngine — The heart of the Active Theory feel
   lerp(current, target, 0.05) on everything = "heavy objects falling into position"
*/

class GravityEngine {
  constructor() {
    this.mouse = { x: 0, y: 0 };
    this.smoothMouse = { x: 0, y: 0 };
    this.normalizedMouse = { x: 0, y: 0 }; // -1 to 1
    this.velocity = { x: 0, y: 0 };
    this.speed = 0;
    this.prevMouse = { x: 0, y: 0 };
    this.isMoving = false;
    this.idleTimer = 0;
    this.listeners = new Set();

    this._onMouseMove = this._onMouseMove.bind(this);
    this._onTouchMove = this._onTouchMove.bind(this);
    this._raf = null;
    this._lastTime = 0;
    this._initialized = false;
  }

  init() {
    if (this._initialized) return;
    this._initialized = true;

    window.addEventListener('mousemove', this._onMouseMove, { passive: true });
    window.addEventListener('touchmove', this._onTouchMove, { passive: true });

    this._lastTime = performance.now();
    this._raf = requestAnimationFrame(this._loop.bind(this));
  }

  destroy() {
    this._initialized = false;
    window.removeEventListener('mousemove', this._onMouseMove);
    window.removeEventListener('touchmove', this._onTouchMove);
    if (this._raf) cancelAnimationFrame(this._raf);
  }

  subscribe(callback) {
    this.listeners.add(callback);
    return () => this.listeners.delete(callback);
  }

  _onMouseMove(e) {
    this.mouse.x = e.clientX;
    this.mouse.y = e.clientY;
    this.normalizedMouse.x = (e.clientX / window.innerWidth) * 2 - 1;
    this.normalizedMouse.y = -(e.clientY / window.innerHeight) * 2 + 1;
  }

  _onTouchMove(e) {
    if (e.touches.length > 0) {
      this.mouse.x = e.touches[0].clientX;
      this.mouse.y = e.touches[0].clientY;
      this.normalizedMouse.x = (this.mouse.x / window.innerWidth) * 2 - 1;
      this.normalizedMouse.y = -(this.mouse.y / window.innerHeight) * 2 + 1;
    }
  }

  _loop(now) {
    if (!this._initialized) return;

    const delta = Math.min((now - this._lastTime) / 1000, 0.1); // Cap at 100ms
    this._lastTime = now;

    // THE Active Theory signature: lerp with 0.05
    const lerpFactor = 1 - Math.pow(0.05, delta * 60);
    this.smoothMouse.x += (this.mouse.x - this.smoothMouse.x) * lerpFactor;
    this.smoothMouse.y += (this.mouse.y - this.smoothMouse.y) * lerpFactor;

    // Velocity
    this.velocity.x = this.mouse.x - this.prevMouse.x;
    this.velocity.y = this.mouse.y - this.prevMouse.y;
    this.speed = Math.sqrt(this.velocity.x ** 2 + this.velocity.y ** 2);

    // Idle detection
    if (this.speed > 0.5) {
      this.isMoving = true;
      this.idleTimer = 0;
    } else {
      this.idleTimer += delta;
      if (this.idleTimer > 0.5) {
        this.isMoving = false;
      }
    }

    this.prevMouse.x = this.mouse.x;
    this.prevMouse.y = this.mouse.y;

    // Notify subscribers
    for (const cb of this.listeners) {
      cb(this, delta);
    }

    this._raf = requestAnimationFrame(this._loop.bind(this));
  }
}

// Singleton
const gravityEngine = new GravityEngine();
export default gravityEngine;
export { GravityEngine };
