package com.anik.flappybirds.states;

import com.anik.flappybirds.FlappyBirds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Anik on 21-Jan-17.
 */

public class GameOverState extends State {

    private Texture background;
    private Texture gameOverBtn;
    private Rectangle gameOverButton;
    private Vector3 touchPoint;
    private int score;
    private BitmapFont font;

    protected GameOverState(GameStateManager gsm, int score) {
        super(gsm);
        this.score = score;
        font = new BitmapFont();
        font.setColor(Color.PINK);
        camera.setToOrtho(false, FlappyBirds.width / 2, FlappyBirds.height / 2);
        background = new Texture("bg.png");
        gameOverBtn = new Texture("gameover.png");
        gameOverButton = new Rectangle(camera.position.x - (gameOverBtn.getWidth() / 2), camera.position.y+gameOverBtn.getHeight(),gameOverBtn.getWidth(),gameOverBtn.getHeight());
        touchPoint=new Vector3();
    }

    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched())
        {
            camera.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
            if(gameOverButton.contains(touchPoint.x,touchPoint.y))
            {
                gsm.set(new MenuState(gsm));

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
        sb.draw(gameOverBtn, camera.position.x - (gameOverBtn.getWidth() / 2), camera.position.y+gameOverBtn.getHeight());
        font.draw(sb, "Score: "+ score, camera.position.x-35,camera.position.y+gameOverBtn.getHeight()/2);
        sb.end();
    }

    @Override
    public void dispose() {

        background.dispose();
        gameOverBtn.dispose();
        font.dispose();
    }
}
