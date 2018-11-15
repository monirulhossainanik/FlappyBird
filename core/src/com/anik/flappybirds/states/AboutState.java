package com.anik.flappybirds.states;

import com.anik.flappybirds.FlappyBirds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Anik on 22-Jan-17.
 */

public class AboutState extends State {

    private Texture background;
    private BitmapFont font;

    protected AboutState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont();
        font.setColor(Color.GOLDENROD);
        camera.setToOrtho(false, FlappyBirds.width/2,FlappyBirds.height/2);
        background = new Texture("bg.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            gsm.push(new MenuState(gsm));

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x-(camera.viewportWidth/2),0);
        font.draw(sb, "Md. Monirul Hossain", camera.position.x-64,camera.position.y+90);
        font.draw(sb, "Email: anik787@gmail.com", camera.position.x-84,camera.position.y+70);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
    }
}
