package com.felnstaren.engine.gfx;

public enum ParticleType {
    
    FIRE(0xffff3333, 0xff000000),
    MISSILE_TRAIL(0xffff8888, 0xffffffff, 0xffffffff, 0xff000000),
    SHRAPNEL(0xff999999);

    public final int[] colors;

    private ParticleType(int... colors) {
        this.colors = colors;
    }

}
