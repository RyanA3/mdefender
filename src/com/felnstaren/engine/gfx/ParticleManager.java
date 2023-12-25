package com.felnstaren.engine.gfx;

import java.util.ArrayList;

import com.felnstaren.engine.Renderer;

public class ParticleManager {

    public static final ParticleManager INSTANCE = new ParticleManager();

    private ArrayList<ParticleGroup> groups;
    
    private ParticleManager() {
        groups = new ArrayList<ParticleGroup>();
    };

    public void addGroup(ParticleGroup group) {
        groups.add(group);
    }

    public void update(float delta_time) {
        for(ParticleGroup g : groups) {
            g.update(delta_time);
        }
    }

    public void render(Renderer renderer) {
        for(ParticleGroup g : groups) {
            g.render(renderer);
        }
    }

}
