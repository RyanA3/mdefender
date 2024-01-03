package com.felnstaren.engine.gfx;

import com.felnstaren.engine.Renderer;
import java.util.concurrent.ThreadLocalRandom;

public class ParticleGroup {

    protected Particle[] particles;
    public int 
        lifetime = 200,
        lifetimeVary = 10,
        randomForceX = 10, randomForceY = 10,
        constantForceX = 0, constantForceY = 30, 
        x, y, 
        width, height, 
        direction = 90, spread = 180,
        speed = 100, speedVary = 1,
        numAlive = 0;
    
    public ParticleType type = ParticleType.FIRE;

    private static final int[]
        sinTable = new int[360],
        cosTable = new int[360];
    
    static {
        for(int i = 0; i < 360; i++) {
            double angle = Math.toRadians(i);
            sinTable[i] = (int) (256 * Math.sin(angle));
            cosTable[i] = (int) (256 * Math.cos(angle));
        }
    }

    public ParticleGroup(int numParticles, int x, int y, int width, int height, int direction, int spread, int speed, int numAlive) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        this.spread = spread;
        this.speed = speed;
        this.numAlive = numAlive;

        this.particles = new Particle[numParticles];
        for(int i = 0; i < particles.length; i++) {
            particles[i] = new Particle(this);
            particles[i].age = ThreadLocalRandom.current().nextInt(lifetime);
        }

        for(int i = 0; i < numAlive; i++) {
            newParticle(particles[i]);
        }
    }

    public ParticleGroup(int numParticles, int x, int y, int direction, int spread, int speed, int numAlive) {
        this(numParticles, x, y, 0, 0, direction, spread, speed, numAlive);
    }

    public ParticleGroup(int numParticles, int x, int y, int direction, int spread, int speed) {
        this(numParticles, x, y, 0, 0, direction, spread, speed, 0);
    }

    public ParticleGroup(int numParticles, int x, int y, int width, int height) {
        this(numParticles, x, y, width, height, 0, 360, 100, 0);
    }

    public ParticleGroup(int numParticles, int x, int y) {
        this(numParticles, x, y, 0, 0, 0, 360, 100, 0);
    }

    public void resetParticles() {
        for(int i = 0; i < particles.length; i++) {
            newParticle(particles[i]);
            particles[i].age = ThreadLocalRandom.current().nextInt(lifetime);
        }
    }

    private int a,s;
    public void newParticle(Particle particle) {
        particle.x = (x << 8);
        particle.y = (y << 8);
        if(width > 0) particle.x += ThreadLocalRandom.current().nextInt((width << 8));
        if(height > 0) particle.y += ThreadLocalRandom.current().nextInt((height << 8));

        a = (direction + 360 - spread/2 + ThreadLocalRandom.current().nextInt(spread)) % 360;
        s = speed + ThreadLocalRandom.current().nextInt(speedVary);
        particle.vx = ((cosTable[a] * s) >> 8);
        particle.vy =  -((sinTable[a] * s) >> 8);
        particle.age = ThreadLocalRandom.current().nextInt(lifetimeVary);
    }

    public void update(float delta_time) {
        if(numAlive < particles.length - 1) {
            newParticle(particles[numAlive]);
            numAlive++;
        }
        
        for(int i = 0; i < numAlive; i++) {
            if(particles[i].isDead()) newParticle(particles[i]);
            particles[i].update(delta_time);
        }
    }

    public void render(Renderer renderer) {
        for(int i = 0; i < numAlive; i++) {
            particles[i].render(renderer);
        }
    }
    
}
