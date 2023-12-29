package com.felnstaren.engine.gfx;

public enum ParticleType {
    
    FIRE(0xffff3333, 0xff000000),
    MISSILE_TRAIL(0xffff0000, 0xffffffff, 0xffffffff, 0xff000000);

    public final int[] colors;

    private ParticleType(int... colors) {
        this.colors = colors;
    }

}
