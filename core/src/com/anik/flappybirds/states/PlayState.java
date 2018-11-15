package com.anik.flappybirds.states;

import com.anik.flappybirds.FlappyBirds;
import com.anik.flappybirds.sprites.Bird;
import com.anik.flappybirds.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Anik on 24-Dec-16.
 */

public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    private Bird bird;
    private Texture background;
    private Texture ground;
    private Vector2 groundPosition1, groundPosition2;
    private Music music;
    private BitmapFont font;
    private int score;
    private String speedUp;

    private Array<Tube> tubes;

    protected PlayState(GameStateManager gsm) {
        super(gsm);

        font = new BitmapFont();
        font.setColor(Color.CORAL);
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        bird = new Bird(10,300);
        camera.setToOrtho(false, FlappyBirds.width/2,FlappyBirds.height/2);
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPosition1 = new Vector2(camera.position.x - (camera.viewportWidth / 2),GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((camera.position.x - camera.viewportWidth/2) + ground.getWidth(),GROUND_Y_OFFSET);

        tubes = new Array<Tube>();

        for (int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {

        if (Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {

        handleInput();
        updateGround();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPositionTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPositionTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
                score++;
                bird.increaseMovement();
            }

            if (tube.collides(bird.getBounds())){
                gsm.set(new GameOverState(gsm,score));
            }
        }

        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gsm.set(new GameOverState(gsm,score));

        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x-(camera.viewportWidth/2),0);
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);
        for(Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        }
        sb.draw(ground,groundPosition1.x,groundPosition1.y);
        sb.draw(ground,groundPosition2.x,groundPosition2.y);
        font.draw(sb, "Score: "+ score, camera.position.x-35,ground.getHeight()/3);
        sb.end();
    }

    @Override
    public void dispose() {

        music.dispose();
        background.dispose();
        bird.dispose();
        ground.dispose();
        font.dispose();
        for(Tube tube : tubes){
            tube.dispose();
        }
        System.out.println("PlayState disposed");
    }

    private void updateGround(){

        if (camera.position.x - camera.viewportWidth/2 > groundPosition1.x + ground.getWidth()){
            groundPosition1.add(ground.getWidth() * 2 , 0);
        }

        if (camera.position.x - camera.viewportWidth/2 > groundPosition2.x + ground.getWidth()){
            groundPosition2.add(ground.getWidth() * 2 , 0);
        }
    }


}
