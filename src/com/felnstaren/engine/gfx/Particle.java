package com.felnstaren.engine.gfx;

import com.felnstaren.engine.Renderer;

public class Particle {

    private static int color1 = 0xffff3333, color2 = 0xff333333;
    public int x, y, vx, vy;
    public int age;
    public ParticleGroup group;

    public Particle(ParticleGroup group) {
        this.group = group;
    };
    public Particle(ParticleGroup group, int x, int y, int vx, int vy) {
        this.group = group;
        this.x = x << 8;
        this.y = y << 8;
        this.vx = vx;
        this.vy = vy;
    }

    public void update(float delta_time) {
        if(group.randomForceX != 0) vx += group.randomForceX * (Math.random() - 0.5);
        if(group.randomForceY != 0) vy += group.randomForceY * (Math.random() - 0.5);
        if(group.constantForceX != 0) vx += group.constantForceX;
        if(group.constantForceY != 0) vy += group.constantForceY;
        x += vx;
        y += vy;
        age++;
        if(age > group.lifetime) age = group.lifetime;
    }

    public void render(Renderer renderer) {
        int ccolor = Color.lerp(color1, color2, (float) age / group.lifetime);
        renderer.setPixel(x >> 8, y >> 8, ccolor);
        //renderer.fillRect((int) x, (int) y, 2, 2, ccolor);
    }

    public boolean isDead() {
        return age >= group.lifetime;
    }
    
}
