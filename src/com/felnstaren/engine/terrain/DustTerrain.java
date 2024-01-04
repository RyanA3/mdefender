package com.felnstaren.engine.terrain;

import com.felnstaren.defender.MissileDefender;

import com.felnstaren.engine.Renderer;

public class DustTerrain {
    
    private byte[] terrain;
    private int width, height;

    public DustTerrain(int width, int height) {
        terrain = new byte[width * height];
        this.width = width;
        this.height = height;

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(y + (x / 10) > height - 100) {
                    terrain[y * width + x] = (byte) TerrainType.DIRT.ordinal();
                }
            }
        }
    }

    public void update(float delta_time) {

    }

    public void render(Renderer renderer) {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(terrain[y * width + x] == (byte) TerrainType.AIR.ordinal()) continue;
                renderer.setPixel(x, y, TerrainType.values()[terrain[y * width + x]].color);
            }
        }
    }

    public int sampleHeight(int x) {
        for(int y = height-1; y > 0; y--) {
            if(terrain[y * width + x] != (byte) TerrainType.AIR.ordinal()) continue;
            return y+1;
        }
        return 0;
    }

}
