package com.felnstaren.engine.entity;

import com.felnstaren.engine.Renderer;
import com.felnstaren.engine.gfx.ParticleGroup;
import com.felnstaren.engine.gfx.ParticleManager;

public class Entity {

    protected int width = 1, height = 1;
    protected float x = 0, y = 0, vx = 0, vy = 0, ax = 0, ay = 0, iax = 0, iay = 0;
    protected float invMass = 1.0f;
    protected ParticleGroup particles;
    protected boolean dead = false;

    public Entity() {
        particles = new ParticleGroup(32, (int) x, (int) y, width, height);
        ParticleManager.INSTANCE.addGroup(particles);
    }
    public Entity(float x, float y, int width, int height, float mass) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.invMass = 1.0f / mass;
        //particles = new ParticleGroup(100, (int) x, (int) y, width, height);
        //ParticleManager.INSTANCE.addGroup(particles);
    }

    public void update(float delta_time) {
        vx += (ax + iax) * delta_time * 0.5;
        vy += (ay + iay) * delta_time * 0.5;
        x += vx * delta_time;
        y += vy * delta_time;
        vx += (ax + iax) * delta_time * 0.5;
        vy += (ay + iay) * delta_time * 0.5;
        iax = 0;
        iay = 0;
        if(particles != null) {
            particles.x = (int) x + width / 2;
            particles.y = (int) y + height / 2;
        }
    }

    public void render(Renderer renderer) {
        renderer.drawRect((int) x, (int) y, width, height, 0xFF00FF00);
    }

    public void applyConstantForce(float fx, float fy) {
        ax += fx * invMass;
        ay += fy * invMass;
    }

    public void applyInstantaneousForce(float ifx, float ify) {
        iax += ifx * invMass;
        iay += ify * invMass;
    }

    public boolean isDead() {
        return dead;
    }
    
}
