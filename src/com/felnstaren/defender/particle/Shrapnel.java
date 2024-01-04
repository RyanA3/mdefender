package com.felnstaren.defender.particle;

import com.felnstaren.engine.gfx.ParticleGroup;
import com.felnstaren.engine.gfx.ParticleType;

public class Shrapnel extends ParticleGroup {

    private static final int 
        NUM_PARTICLES_BASE = 100,
        NUM_PARTICLES_RANGE = 1000,
        SPEED_BASE = 200,
        SPEED_RANGE = 90;

    public Shrapnel(int x, int y, int direction, int spread, float intensity) {
        super(
            NUM_PARTICLES_BASE + (int) (intensity * NUM_PARTICLES_RANGE), 
            x, y, 0, 0,
            direction, spread,
            SPEED_BASE + (int) (intensity * SPEED_RANGE),
            SPEED_BASE + (int) (intensity * SPEED_RANGE)-1,
            100,
            90,
            -1
            );
            this.constantForceY = 100;
        this.respawn = false;
        this.type = ParticleType.SHRAPNEL;
    }
    
}
