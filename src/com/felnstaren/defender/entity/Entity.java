package com.felnstaren.defender.entity;

import com.felnstaren.engine.Renderer;
import com.felnstaren.engine.gfx.ParticleGroup;
import com.felnstaren.engine.gfx.ParticleManager;

public class Entity {

    private int width = 1, height = 1;
    private float x = 0, y = 0, vx = 0, vy = 0, ax = 0, ay = 0, iax = 0, iay = 0;
    private float invMass = 1.0f;
    private ParticleGroup particles;

    public Entity() {
        particles = new ParticleGroup(32, (int) x, (int) y, width, height);
        ParticleManager.INSTANCE.addGroup(particles);
    }
    public Entity(int x, int y, int width, int height, float mass) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.invMass = 1.0f / mass;
        particles = new ParticleGroup(1000, (int) x, (int) y, width, height);
        ParticleManager.INSTANCE.addGroup(particles);
    }

    public void update(float delta_time) {
        vx += (ax + iax) * delta_time;
        vy += (ay + iay) * delta_time;
        x += vx * delta_time;
        y += vy * delta_time;
        iax = 0;
        iay = 0;
        particles.x = (int) x;
        particles.y = (int) y;
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
    
}
