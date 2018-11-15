package com.anik.flappybirds.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Anik on 24-Dec-16.
 */

public class Bird {

    private static final int GRAVITY = -15;
    protected float MOVEMENT = 30;
    private Vector2 position;
    private Vector2 velocity;
    private Texture bird;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Sound flap;

    public Bird(int x, int y) {

        position = new Vector2(x,y);
        velocity = new Vector2(0,0);
        bird = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(bird), 3, 0.5f);
        bounds = new Rectangle(x,y,bird.getWidth() / 3, bird.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){

        birdAnimation.update(dt);
        if (position.y > 0)
            velocity.add(0,GRAVITY);
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y);
        velocity.scl(1/dt);
        if (position.y < 0)
            position.y = 0;

        bounds.setPosition(position.x,position.y);
    }

    public void increaseMovement(){
        MOVEMENT += 3;
    }

    public void jump(){

        if (position.y < 350)
            velocity.y = 250;
        flap.play(0.4f);
    }

    public Vector2 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        bird.dispose();
        flap.dispose();
    }
}
