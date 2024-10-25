package com.ninjamoney.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

    private AngryBirds game;
    private int levelNumber;
    private boolean isLocked;
    private Levels level;
    private SpriteBatch batch;
    private Texture background;
    private Texture backButton;
    private Screen prev;
    private Catapult catapult;

    public GameScreen(AngryBirds game, int levelNumber, boolean isLocked, Levels level) {
        this.game = game;
        this.levelNumber = levelNumber;
        this.isLocked = isLocked;
        this.level = level;
        batch = new SpriteBatch();
        background = new Texture("background.jpg");
        backButton = new Texture("back.png");
        prev = new LevelSelectorScreen(game);
        catapult = new Catapult(209, 103);
    }
    @Override
    public void show() {}

    public void handleInput(){
        float x = Gdx.input.getX();
        float y = Gdx.graphics.getHeight() - Gdx.input.getY();
        if(x > 1177 && y > 621){
            if(Gdx.input.isTouched()) {
                game.setScreen(prev);
            }
        }
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, 1280f, 720f);
        batch.draw(backButton, 1177, 621, 103, 93);
        batch.draw(catapult.getCatapultTexture(), 209, 103, 72,203);
        batch.draw(new Red().getRedTexture(),214,254,49,44);
        batch.draw(new Bomb().getBombTexture(), 154, 103, 55, 66);
        batch.draw(new Chuck().getChuckTexture(),69,103,71,55);
        batch.end();
        handleInput();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
