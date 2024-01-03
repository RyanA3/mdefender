package com.felnstaren.defender.particle;

import com.felnstaren.engine.gfx.ParticleGroup;
import com.felnstaren.engine.gfx.ParticleType;

public class Shrapnel extends ParticleGroup {

    private static final int 
        NUM_PARTICLES_BASE = 10,
        NUM_PARTICLES_RANGE = 100,
        SPEED_BASE = 50,
        SPEED_RANGE = 100;

    public Shrapnel(int x, int y, int direction, int spread, float intensity) {
        super(
            NUM_PARTICLES_BASE + (int) (intensity * NUM_PARTICLES_RANGE), 
            x, y, direction, spread,
            SPEED_BASE + (int) (intensity * SPEED_RANGE),
            -1
            );

        this.type = ParticleType.SHRAPNEL;
    }
    
}
