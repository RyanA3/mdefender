package com.felnstaren.engine.gfx;

import com.felnstaren.engine.Renderer;

public class ParticleGroup {

    private Particle[] particles;
    public float lifetime = 2.0f;
    public float lifetimeVary = 1.5f;
    public int x, y, width, height;

    public ParticleGroup(int numParticles, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.particles = new Particle[numParticles];
        for(int i = 0; i < particles.length; i++) {
            particles[i] = new Particle();
            newParticle(particles[i]);
        }
    }  

    public void newParticle(Particle particle) {
        particle.x = (int) (x + width * Math.random());
        particle.y = (int) (y + height * Math.random());
        particle.vx = 0;
        particle.vy = 0;
        particle.age = lifetimeVary * (float) Math.random();
        particle.lifetime = lifetime;
    }

    public void update(float delta_time) {
        for(int i = 0; i < particles.length; i++) {
            if(particles[i].isDead()) newParticle(particles[i]);
            particles[i].update(delta_time);
        }
    }

    public void render(Renderer renderer) {
        for(int i = 0; i < particles.length; i++) {
            particles[i].render(renderer);
        }
    }
    
}
