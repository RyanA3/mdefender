package com.felnstaren.defender;

import com.felnstaren.engine.AbstractApp;
import com.felnstaren.engine.AppContainer;
import com.felnstaren.engine.Renderer;
import com.felnstaren.engine.entity.Entity;
import com.felnstaren.engine.entity.EntityHandler;
import com.felnstaren.engine.event.EventHandler;
import com.felnstaren.engine.gfx.ParticleManager;
import com.felnstaren.engine.ui.button.ButtonHandler;

import java.awt.event.KeyEvent;

public class MissileDefender extends AbstractApp {
	
	public static AppContainer APP;
	public static MissileDefender GAME;

	private HUD hud;
	private EventHandler ehandler;
	private ButtonHandler bhandler;
	private EntityHandler entityHandler;

	private Cursor cursor = new Cursor();

	public void init(AppContainer ac) {
		this.hud = new HUD();

		this.ehandler = new EventHandler();
		this.bhandler = new ButtonHandler(ehandler);	
		this.entityHandler = new EntityHandler();
		
		Entity e = new Entity(100, APP.getHeight() - 20, 20, 20, 1.0f);
		spawn(e);
	}

	public void update(AppContainer ac, float delta_time) {
		bhandler.update(ac);
		ehandler.update(ac.getInput());

		cursor.update(delta_time);
		entityHandler.update(delta_time);
		ParticleManager.INSTANCE.update(delta_time);

		if(ac.getInput().isKeyDown(KeyEvent.VK_ESCAPE)) {
			ac.stop();
		}
	}

	public void render(AppContainer ac, Renderer renderer) {
		hud.render(renderer, ac);
		bhandler.render(renderer);

		cursor.render(renderer);	
		entityHandler.render(renderer);
		ParticleManager.INSTANCE.render(renderer);
	} 

	public void spawn(Entity entity) {
		entityHandler.spawn(entity);
	}
	
	
	
	public static void main(String[] args) {
		GAME = new MissileDefender();
		APP = new AppContainer(GAME);
		APP.start();
	}

}
