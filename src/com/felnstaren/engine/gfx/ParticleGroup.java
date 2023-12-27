package com.felnstaren.engine.gfx;

import com.felnstaren.engine.Renderer;

public class ParticleGroup {

    private Particle[] particles;
    public int 
        lifetime = 200,
        lifetimeVary = 10,
        randomForceX = 200, randomForceY = 100,
        constantForceX = 0, constantForceY = 20, 
        x, y, 
        width, height, 
        direction = 90, spread = 90,
        speed = 20, speedVary = 0;

    private static int[]
        sinTable = new int[360],
        cosTable = new int[360];
    
    static {
        for(int i = 0; i < 360; i++) {
            double angle = Math.toRadians(i);
            sinTable[i] = (int) (256 * Math.sin(angle));
            cosTable[i] = (int) (256 * Math.cos(angle));
        }
    }


    public ParticleGroup(int numParticles, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.particles = new Particle[numParticles];
        for(int i = 0; i < particles.length; i++) {
            particles[i] = new Particle(this);
            particles[i].lifetime = lifetime;
            newParticle(particles[i]);
            particles[i].age = (int) (lifetime * Math.random());
        }
    }  

    private int a,s;
    public void newParticle(Particle particle) {
        particle.x = x;
        particle.y = y;
        if(width > 0) particle.x += (int) (width * Math.random());
        if(height > 0) particle.y += (int) (height * Math.random());

        a = (direction + 360 - spread/2 + (int)(Math.random() * spread)) % 360;
        s = speed + (int) (speedVary * Math.random());
        particle.vx = ((cosTable[a] * s)) >> 8;
        particle.vy = -((sinTable[a] * s)) >> 8;
        particle.age = (int) (lifetimeVary * Math.random());
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
