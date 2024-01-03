package com.felnstaren.defender.entity;

import com.felnstaren.defender.MissileDefender;
import com.felnstaren.defender.particle.MissileTrail;
import com.felnstaren.engine.AppContainer;
import com.felnstaren.engine.Renderer;
import com.felnstaren.engine.entity.Entity;
import com.felnstaren.engine.gfx.ParticleManager;

public class Missile extends Entity {

    public static final float GRAVITY = 10f;
    private int targetY = 0;

    public Missile(int x, int y, int vy, int targetX, int targetY, float mass) {
        super(x, y, 2, 2, mass);
        this.ay = GRAVITY;
        this.vy = vy;
        this.targetY = targetY;
        int dx = targetX - x;
        int dy = targetY - y;

        //dx = v0t + 1/2at^2; v0 = vx, a = 0
        //dy = v0t + 1/2at^2; v0 = vy, a = GRAVITY, t = ?
        //Calculate the amount of time to fall a distance of dy given gravity
        //0 = 0.5at^2 + v0t - dy
        //solve by quadratic equation (-b +/- sqrt(b^2 - 4ac)) / 2a
        //t = (-v0 + sqrt(v0^2 - 4*0.5*GRAVITY*-dy)) / 2(0.5*GRAVITY)
        float timeToImpact = (float) ((-vy + Math.sqrt((vy*vy) + 4*0.5*ay*dy)) / (2*0.5*ay));

        //Solve for vx to reach target destination
        //0 = 0.5*0*t^2 + v0t - dx
        //vx = dx / t
        vx = dx / timeToImpact;

        this.particles = new MissileTrail(x, y);
        ParticleManager.INSTANCE.addGroup(particles);
    }

    public void update(float delta_time) {
        super.update(delta_time);

        particles.direction = (int) (Math.toDegrees(Math.atan(-vy / vx)));
        
        if(y > targetY && !dead) {
            //Explode
            this.dead = true;
            particles.setShouldRespawnParticles(false);
            MissileDefender.GAME.spawn(new Explosion(x, y, 1));
        }
    }

    public void render(Renderer renderer) {
        renderer.fillRect((int) x, (int) y, width, height, 0xffff3333);
    }
    
}
