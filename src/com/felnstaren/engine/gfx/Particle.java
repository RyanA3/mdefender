package com.felnstaren.engine.gfx;

import com.felnstaren.engine.Renderer;

public class Particle {

    private static int color1 = 0xffff3333, color2 = 0xff333333;
    public float x, y, vx, vy;
    public int lifetime,age=0;
    public ParticleGroup group;

    public Particle(ParticleGroup group) {
        this.group = group;
    };
    public Particle(ParticleGroup group, int x, int y, int vx, int vy, int lifetime) {
        this.group = group;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.lifetime = lifetime;
    }

    public void update(float delta_time) {
        if(group.randomForceX != 0) vx += group.randomForceX * (Math.random() - 0.5) * delta_time;
        if(group.randomForceY != 0) vy += group.randomForceY * (Math.random() - 0.5) * delta_time;
        if(group.constantForceX != 0) vx += group.constantForceX * delta_time;
        if(group.constantForceY != 0) vy += group.constantForceY * delta_time;
        x += vx * delta_time;
        y += vy * delta_time;
        age++;
        if(age > lifetime) age = lifetime;
    }

    public void render(Renderer renderer) {
        int ccolor = Color.lerp(color1, color2, (float) age / lifetime);
        renderer.setPixel((int) x, (int) y, ccolor);
        //renderer.fillRect((int) x, (int) y, 2, 2, ccolor);
    }

    public boolean isDead() {
        return age >= lifetime - 0.05f;
    }
    
}
