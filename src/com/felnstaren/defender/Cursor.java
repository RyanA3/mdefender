package com.felnstaren.defender;

import com.felnstaren.defender.entity.Missile;
import com.felnstaren.engine.Renderer;
import com.felnstaren.engine.gfx.Image;
import java.awt.event.MouseEvent;

public class Cursor {

    private int x, y, width = 16, height = 16;
    private Image sprite;
    public Cursor() {
        sprite = new Image("/resources/icons/cursor.png");
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
    }

    public void update(float delta_time) {
        this.x = MissileDefender.APP.getInput().getMouseX();
        this.y = MissileDefender.APP.getInput().getMouseY();

        if(MissileDefender.APP.getInput().isButtonDown(MouseEvent.BUTTON1)) {
            int maxHeight = MissileDefender.GAME.sampleTerrainHeight(MissileDefender.APP.getWidth()-1) - 10;
            int spawnHeight = (int) (Math.random() * maxHeight);
            int spawnX = MissileDefender.APP.getWidth();
            MissileDefender.GAME.spawn(new Missile(spawnX, spawnHeight, 0, x, MissileDefender.GAME.sampleTerrainHeight(x), 1));
        }
    }

    public void render(Renderer renderer) {
        renderer.drawImage(sprite, x - width / 2, y - height / 2);
    }
    
}
