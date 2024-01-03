package com.felnstaren.defender.particle;

import com.felnstaren.engine.gfx.ParticleGroup;
import com.felnstaren.engine.gfx.ParticleType;

public class MissileTrail extends ParticleGroup {

    public MissileTrail(int x, int y) {
        super(1000, x, y, 0, 0);
        this.constantForceX = 0;
        this.constantForceY = 10;
        this.randomForceX = 50;
        this.randomForceY = 10;
        this.speed = 100;
        this.spread = 10;
        this.lifetime = 200;
        this.type = ParticleType.MISSILE_TRAIL;

        resetParticles();
    }

    public MissileTrail() {
        this(0, 0);
    }

    public void update(float delta_time) {
        super.update(delta_time);
    }

}
