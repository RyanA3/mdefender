package com.felnstaren.defender.entity;

import com.felnstaren.defender.particle.Shrapnel;
import com.felnstaren.engine.entity.Entity;
import com.felnstaren.engine.gfx.ParticleManager;

public class Explosion extends Entity {

    private static final float LIFETIME = 0.1f;
    private float age = 0;

    public Explosion(float x, float y, float intensity) {
        super(x, y, 10, 10, 1);
        this.particles = new Shrapnel((int) x, (int) y, 90, 180, intensity);
        ParticleManager.INSTANCE.addGroup(particles);
    }

    public void update(float delta_time) {
        super.update(delta_time);
        age += delta_time;
        if(age > LIFETIME) this.dead = true;
    }
    
}
