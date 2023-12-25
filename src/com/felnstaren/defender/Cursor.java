package com.felnstaren.defender;

import com.felnstaren.engine.Renderer;
import com.felnstaren.engine.gfx.Image;

public class Cursor {

    private int x, y, width = 16, height = 16;
    private Image sprite;
    public Cursor() {
        sprite = new Image("/resources/icons/cursor.png");
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
    }

    public void update(float delta_time) {
        this.x = MissileDefender.app.getInput().getMouseX();
        this.y = MissileDefender.app.getInput().getMouseY();
    }

    public void render(Renderer renderer) {
        renderer.drawImage(sprite, x - width / 2, y - height / 2);
    }
    
}
