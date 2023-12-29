package com.felnstaren.defender.particle;

import com.felnstaren.engine.gfx.ParticleGroup;
import com.felnstaren.engine.gfx.ParticleType;

public class MissileTrail extends ParticleGroup {
    
    public MissileTrail() {
        super(100, 0, 0, 0, 0);
        this.constantForceX = 0;
        this.constantForceY = 10;
        this.randomForceX = 10;
        this.randomForceY = 0;
        this.speed = 100;
        this.spread = 10;
        this.type = ParticleType.MISSILE_TRAIL;
    }

    public MissileTrail(int x, int y) {
        super(100, x, y, 0, 0);
        this.constantForceX = 0;
        this.constantForceY = 10;
        this.randomForceX = 50;
        this.randomForceY = 10;
        this.speed = 100;
        this.spread = 20;
        this.type = ParticleType.MISSILE_TRAIL;
    }

    public void update(float delta_time) {
        super.update(delta_time);
    }

}
