package com.felnstaren.engine.terrain;

public enum TerrainType {
    
    AIR(0x00000000),
    DIRT(0xff774422),
    GRASS(0xff556622),
    STONE(0xff777777);

    public final int color;

    private TerrainType(int color) {
        this.color = color;
    }

}
