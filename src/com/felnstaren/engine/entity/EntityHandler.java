package com.felnstaren.engine.entity;

import java.util.ArrayList;

import com.felnstaren.engine.Renderer;

public class EntityHandler {

    private ArrayList<Entity> 
        entities = new ArrayList<Entity>(),
        add = new ArrayList<Entity>(),
        remove = new ArrayList<Entity>();

    public EntityHandler() {};

    public void update(float delta_time) {
        for(Entity e : entities) {
            e.update(delta_time);
            if(e.isDead()) remove.add(e);
        }

        entities.addAll(add);
        add.clear();

        entities.removeAll(remove);
        remove.clear();
    }

    public void render(Renderer renderer) {
        for(Entity e : entities) e.render(renderer);
    }

    public void spawn(Entity e) {
        add.add(e);
    }

}
