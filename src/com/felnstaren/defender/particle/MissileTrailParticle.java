package com.felnstaren.defender.particle;

import com.felnstaren.engine.gfx.Particle;
import com.felnstaren.engine.gfx.ParticleGroup;

public class MissileTrailParticle extends Particle {

    public MissileTrailParticle(ParticleGroup group) {
        super(group);
    }
    
    public MissileTrailParticle(ParticleGroup group, int x, int y, int vx, int vy) {
        super(null, x, y, vx, vy);
    }
    
}
