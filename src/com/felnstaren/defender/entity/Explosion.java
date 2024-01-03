package com.felnstaren.defender.entity;

import com.felnstaren.defender.particle.Shrapnel;
import com.felnstaren.engine.entity.Entity;

public class Explosion extends Entity {

    private static final float LIFETIME = 0.1f;
    private float age = 0;

    public Explosion(float x, float y, float intensity) {
        this.particles = new Shrapnel((int) x, (int) y, 0, 360, intensity);
    }

    public void update(float delta_time) {
        super.update(delta_time);
        age += delta_time;
        if(age > LIFETIME) this.dead = true;
    }
    
}
