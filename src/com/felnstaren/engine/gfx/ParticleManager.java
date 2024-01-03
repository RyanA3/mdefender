package com.felnstaren.engine.gfx;

import java.util.ArrayList;

import com.felnstaren.engine.Renderer;

public class ParticleManager {

    public static final ParticleManager INSTANCE = new ParticleManager();

    private ArrayList<ParticleGroup>
        groups = new ArrayList<ParticleGroup>(),
        add = new ArrayList<ParticleGroup>(),
        remove = new ArrayList<ParticleGroup>();
    
    private ParticleManager() {};

    public void addGroup(ParticleGroup group) {
        add.add(group);
    }

    public void removeGroup(ParticleGroup group) {
        remove.add(group);
    }

    public void update(float delta_time) {
        for(ParticleGroup g : groups) {
            g.update(delta_time);
            if(g.shouldRemove()) remove.add(g);
        }

        groups.addAll(add);
        add.clear();

        groups.removeAll(remove);
        remove.clear();
    }

    public void render(Renderer renderer) {
        for(ParticleGroup g : groups) {
            g.render(renderer);
        }
    }

}
