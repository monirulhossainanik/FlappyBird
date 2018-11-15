package com.anik.flappybirds.states;

import com.anik.flappybirds.FlappyBirds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Anik on 24-Dec-16.
 */

public class MenuState extends State {

    private Texture title;
    private Texture background;
    private Texture playBtn;
    private Texture aboutBtn;
    private Texture exitBtn;
    private Rectangle aboutButton;
    private Rectangle exitButton;
    private Rectangle playButton;
    private Vector3 touchPoint;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyBirds.width / 2, FlappyBirds.height / 2);
        title = new Texture("title.jpg");
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        aboutBtn = new Texture("about.png");
        exitBtn = new Texture("exit.png");
        playButton = new Rectangle(camera.position.x - (playBtn.getWidth() / 2), camera.position.y+40,playBtn.getWidth(),playBtn.getHeight());
        aboutButton = new Rectangle(camera.position.x - (aboutBtn.getWidth() / 2), camera.position.y,aboutBtn.getWidth(),aboutBtn.getHeight());
        exitButton = new Rectangle(camera.position.x - (exitBtn.getWidth() / 2), camera.position.y-40,exitBtn.getWidth(),exitBtn.getHeight());
        touchPoint=new Vector3();

    }

    @Override
    public void handleInput() {

        if(Gdx.input.justTouched())
        {
            camera.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
            if(playButton.contains(touchPoint.x,touchPoint.y))
            {
                gsm.set(new PlayState(gsm));
            }
            else if (aboutButton.contains(touchPoint.x,touchPoint.y))
            {
                gsm.set(new AboutState(gsm));
            }
            else if (exitButton.contains(touchPoint.x,touchPoint.y))
            {
                Gdx.app.exit();
            }
        }


    }

    @Override
    public void update(float dt) {

        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(title, 0,camera.position.y+100);
        sb.draw(playBtn, camera.position.x - (playBtn.getWidth() / 2), camera.position.y+40);
        sb.draw(aboutBtn, camera.position.x - (playBtn.getWidth() / 2), camera.position.y);
        sb.draw(exitBtn, camera.position.x - (playBtn.getWidth() / 2), camera.position.y-40);
        sb.end();
    }

    @Override
    public void dispose() {

        background.dispose();
        playBtn.dispose();
        System.out.println("MenuState disposed");
    }

}
