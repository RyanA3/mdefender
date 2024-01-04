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
        lastSpawnedParticle = 0,
        numAlive = 0;

    public float 
        spawnRate = 1,
        spawnTimer = 0;
    
    protected boolean 
        respawn = true;
    
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



    public ParticleGroup(int numParticles, int x, int y, int width, int height, int direction, int spread, int speed, int speedVary, int lifetime, int lifetimeVary, int lastSpawnedParticle) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        this.spread = spread;
        this.speed = speed;
        this.speedVary = speedVary;
        if(lastSpawnedParticle < 0) lastSpawnedParticle = numParticles + lastSpawnedParticle;
        this.lastSpawnedParticle = lastSpawnedParticle;
        this.numAlive = numParticles;
        this.spawnRate = numParticles / 10;
        this.lifetime = lifetime;
        this.lifetimeVary = lifetimeVary;

        this.particles = new Particle[numParticles];
        for(int i = 0; i < particles.length; i++) {
            particles[i] = new Particle(this);
            particles[i].age = ThreadLocalRandom.current().nextInt(lifetime);
        }

        for(int i = 0; i < lastSpawnedParticle; i++) {
            newParticle(particles[i]);
        }
    }

    public ParticleGroup(int numParticles, int x, int y, int width, int height, int direction, int spread, int speed, int speedVary, int lastSpawnedParticle) {
        this(numParticles, x, y, width, height, direction, spread, speed, speedVary, 100, 10, lastSpawnedParticle);
    }

    public ParticleGroup(int numParticles, int x, int y, int width, int height, int direction, int spread, int speed, int lastSpawnedParticle) {
        this(numParticles, x, y, width, height, direction, spread, speed, 0, lastSpawnedParticle);
    }

    public ParticleGroup(int numParticles, int x, int y, int direction, int spread, int speed, int lastSpawnedParticle) {
        this(numParticles, x, y, 0, 0, direction, spread, speed, lastSpawnedParticle);
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
        s = speed;
        if(speedVary > 0) s += ThreadLocalRandom.current().nextInt(speedVary);
        particle.vx = ((cosTable[a] * s) >> 8);
        particle.vy =  -((sinTable[a] * s) >> 8);
        particle.age = ThreadLocalRandom.current().nextInt(lifetimeVary);
    }

    public void update(float delta_time) {
        if(respawn || lastSpawnedParticle < particles.length) {
            spawnTimer ++;
            int numToSpawn = (int) (spawnTimer * spawnRate);
            
            if(numToSpawn > 0) {
                spawnTimer = 0;
                for(int i = 0; i < numToSpawn && lastSpawnedParticle < particles.length; i++) {
                    newParticle(particles[lastSpawnedParticle]);
                    particles[lastSpawnedParticle].age = ThreadLocalRandom.current().nextInt(lifetime);
                    lastSpawnedParticle++;
                }
            }
        }
        
        numAlive = particles.length;
        for(int i = 0; i < lastSpawnedParticle; i++) {
            if(particles[i].isDead()) {
                if(respawn) newParticle(particles[i]);
                else {
                    numAlive--;
                }
            }
            particles[i].update(delta_time);
        }
    }

    public void render(Renderer renderer) {
        for(int i = 0; i < lastSpawnedParticle; i++) {
            if(!particles[i].isDead()) particles[i].render(renderer);
        }
    }

    public void setShouldRespawnParticles(boolean respawn) {
        this.respawn = respawn;
    }
    
    public boolean shouldRemove() {
        return numAlive == 0;
    }

}
