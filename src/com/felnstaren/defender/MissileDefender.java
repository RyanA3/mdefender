package com.felnstaren.defender;

import com.felnstaren.engine.AbstractApp;
import com.felnstaren.engine.AppContainer;
import com.felnstaren.engine.Renderer;
import com.felnstaren.engine.entity.Entity;
import com.felnstaren.engine.entity.EntityHandler;
import com.felnstaren.engine.event.EventHandler;
import com.felnstaren.engine.gfx.ParticleManager;
import com.felnstaren.engine.terrain.DustTerrain;
import com.felnstaren.engine.ui.button.ButtonHandler;

import java.awt.event.KeyEvent;

public class MissileDefender extends AbstractApp {
	
	public static AppContainer APP;
	public static MissileDefender GAME;

	public HUD hud;
	public EventHandler events;
	public ButtonHandler buttons;
	public EntityHandler entities;
	public DustTerrain terrain;

	private Cursor cursor = new Cursor();

	public void init(AppContainer ac) {
		this.hud = new HUD();

		this.events = new EventHandler();
		this.buttons = new ButtonHandler(events);	
		this.entities = new EntityHandler();
		this.terrain = new DustTerrain(ac.getWidth(), ac.getHeight());
		
		Entity e = new Entity(100, APP.getHeight() - 20, 20, 20, 1.0f);
		entities.spawn(e);
	}

	public void update(AppContainer ac, float delta_time) {
		buttons.update(ac);
		events.update(ac.getInput());

		cursor.update(delta_time);
		entities.update(delta_time);
		ParticleManager.INSTANCE.update(delta_time);
		terrain.update(delta_time);

		if(ac.getInput().isKeyDown(KeyEvent.VK_ESCAPE)) {
			ac.stop();
		}
	}

	public void render(AppContainer ac, Renderer renderer) {
		hud.render(renderer, ac);
		buttons.render(renderer);

		cursor.render(renderer);	
		entities.render(renderer);
		
		ParticleManager.INSTANCE.render(renderer);
		terrain.render(renderer);
	} 
	
	
	
	public static void main(String[] args) {
		GAME = new MissileDefender();
		APP = new AppContainer(GAME);
		APP.start();
	}

}
