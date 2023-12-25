package com.felnstaren.defender;

import java.util.List;
import java.util.ArrayList;

import com.felnstaren.engine.AbstractApp;
import com.felnstaren.engine.AppContainer;
import com.felnstaren.engine.Renderer;
import com.felnstaren.engine.event.EventHandler;
import com.felnstaren.engine.gfx.ParticleManager;
import com.felnstaren.engine.ui.button.ButtonHandler;
import com.felnstaren.defender.entity.Entity;

public class MissileDefender extends AbstractApp {
	
	public static AppContainer app;

	private HUD hud;
	private EventHandler ehandler;
	private ButtonHandler bhandler;

	private Cursor cursor = new Cursor();
	private List<Entity> entities = new ArrayList<Entity>();
		

	public void init(AppContainer ac) {
		this.hud = new HUD();

		this.ehandler = new EventHandler();
		this.bhandler = new ButtonHandler(ehandler);	
		
		Entity e = new Entity(app.getWidth() / 2, app.getHeight() / 2, 20, 20, 1.0f);
		e.applyConstantForce(0, 9.8f);
		entities.add(e);
	}

	public void update(AppContainer ac, float delta_time) {
		bhandler.update(ac);
		ehandler.update(ac.getInput());

		cursor.update(delta_time);
		for(Entity e : entities) e.update(delta_time);
		ParticleManager.INSTANCE.update(delta_time);
	}

	public void render(AppContainer ac, Renderer renderer) {
		hud.render(renderer, ac);
		bhandler.render(renderer);

		cursor.render(renderer);	
		for(Entity e : entities) e.render(renderer);
		ParticleManager.INSTANCE.render(renderer);
	} 
	
	
	
	public static void main(String[] args) {
		app = new AppContainer(new MissileDefender());
		app.start();
	}

}