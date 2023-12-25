package com.felnstaren.engine.gfx;

import com.felnstaren.engine.Renderer;

public class Particle {

    private static int color1 = 0xffff3333, color2 = 0xff333333;
    public float x,y,vx,vy,lifetime,age=0;

    public Particle() {};
    public Particle(float x, float y, float vx, float vy, float lifetime) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.lifetime = lifetime;
    }

    public void update(float delta_time) {
        x += vx * delta_time;
        y += vy * delta_time;
        age += delta_time;
        if(age > lifetime) age = lifetime;
    }

    public void render(Renderer renderer) {
        int ccolor = Color.lerp(color1, color2, age / lifetime);
        renderer.fillRect((int) x, (int) y, 2, 2, ccolor);
    }

    public boolean isDead() {
        return age >= lifetime - 0.05f;
    }
    
}
