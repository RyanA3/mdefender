package com.felnstaren.engine.terrain;

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
                int k = y;
                if(k > height - 20) {
                    terrain[y * width + x] = (byte) TerrainType.STONE.ordinal();
                }
                else if(k > height - 29) {
                    terrain[y * width + x] = (byte) TerrainType.DIRT.ordinal();
                }
                else if(k > height - 35) {
                    terrain[y * width + x] = (byte) TerrainType.GRASS.ordinal();
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
        for(int y = 0; y < height; y++) {
            if(terrain[y * width + x] == (byte) TerrainType.AIR.ordinal()) continue;
            return y;
        }
        return 0;
    }

    public boolean inBounds(int x, int y) {
        return x > 0 && y > 0 && x < width && y < height;
    }

    public void setTerrain(int x, int y, TerrainType element) {
        if(!inBounds(x,y)) return;
        terrain[y * width + x] = (byte) element.ordinal();
    }



}
