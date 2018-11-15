package com.anik.flappybirds;

import com.anik.flappybirds.states.GameStateManager;
import com.anik.flappybirds.states.MenuState;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBirds implements ApplicationListener {

	public static final int width = 480;
	public static final int height = 800;
	public static final String title = "Flappy Birds";
	private GameStateManager gsm;

	private  SpriteBatch batch;
	
	@Override
	public void create () {
		gsm = new GameStateManager();
		batch = new SpriteBatch();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void pause() {
		this.pause();
	}

	@Override
	public void resume() {
		this.resume();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}


}
