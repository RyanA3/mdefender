package com.felnstaren.defender.entity;

import com.felnstaren.defender.MissileDefender;
import com.felnstaren.defender.particle.Shrapnel;
import com.felnstaren.engine.entity.Entity;
import com.felnstaren.engine.gfx.ParticleManager;
import com.felnstaren.engine.terrain.TerrainType;

public class Explosion extends Entity {

    private static final float LIFETIME = 0.1f;
    private float age = 0;

    public Explosion(float x, float y, float intensity) {
        super(x, y, 10, 10, 1);
        this.particles = new Shrapnel((int) x, (int) y, 90, 180, intensity);
        ParticleManager.INSTANCE.addGroup(particles);
        // for(int ox = (int) x - (width / 2); ox < x + (width / 2); ox++) {
        //     for(int oy = (int) y - (height / 2); oy < y + (height / 2); oy++) {
        //         MissileDefender.GAME.terrain.setTerrain(ox, oy, TerrainType.AIR);
        //     }
        // }
        int r = (int) (intensity * 10);
        int r2 = r*r;
        for (int i = (int)y-r; i <= y+r; i++) {
		    // test upper half of circle, stopping when top reached
		    for (int j = (int)x; (j-x)*(j-x) + (i-y)*(i-y) <= r2; j--) {
                MissileDefender.GAME.terrain.setTerrain(j, i, TerrainType.AIR);
		    }
		    // test bottom half of circle, stopping when bottom reached
		    for (int j = (int)x+1; (j-x)*(j-x) + (i-y)*(i-y) <= r2; j++) {
                MissileDefender.GAME.terrain.setTerrain(j, i, TerrainType.AIR);
		    }
		}
    }

    public void update(float delta_time) {
        super.update(delta_time);
        age += delta_time;
        if(age > LIFETIME) this.dead = true;
    }
    
}
