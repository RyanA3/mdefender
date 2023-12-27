package com.felnstaren.engine.gfx;

import com.felnstaren.engine.Renderer;

public class Particle {

    private static int color1 = 0xffff3333, color2 = 0xff333333;
    public float x,y;
    public int vx,vy,lifetime,age=0;

    public Particle() {};
    public Particle(int x, int y, int vx, int vy, int lifetime) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.lifetime = lifetime;
    }

    public void update(float delta_time) {
        x += vx * delta_time;
        y += vy * delta_time;
        age++;
        if(age > lifetime) age = lifetime;
    }

    public void render(Renderer renderer) {
        int ccolor = Color.lerp(color1, color2, (float) age / lifetime);
        renderer.fillRect((int) x, (int) y, 2, 2, ccolor);
    }

    public boolean isDead() {
        return age >= lifetime - 0.05f;
    }
    
}
