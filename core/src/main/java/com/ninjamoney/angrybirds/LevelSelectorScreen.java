package com.ninjamoney.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelSelectorScreen implements Screen {
    private SpriteBatch batch;
    private AngryBirds game;
    private Texture levelSelectorBackground;
    private Texture levelSelectorTexture;
    private Texture l1Texture;
    private Texture l2Texture;
    private Texture l3Texture;
    private Levels level1;
    private Levels level2;
    private Levels level3;

    public LevelSelectorScreen(AngryBirds game) {
        this.game = game;
        batch = new SpriteBatch();
        levelSelectorTexture = new Texture("lst.png");
        levelSelectorBackground = new Texture("lsmmenu.jpg");
//        level1 = new Levels(1, false, new Texture("l1.png"));
        l1Texture = new Texture("l1.png");
        l2Texture = new Texture("l2.png");
        l3Texture = new Texture("l3.png");
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
//        game.setScreen(new LevelSelectorScreen(game));
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(levelSelectorBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(levelSelectorTexture, 0, 0, 1280f, 266f);
        level1 = new Levels(1, false, new Texture("l1.png"));
        level2 = new Levels(2, true, new Texture("l2.png"));
        level3 = new Levels(3, true, new Texture("l3.png"));
        batch.draw(level1.getLevelTexture(),177,261, 238, 242);
        batch.draw(level2.getLevelTexture(),520,261, 238, 242);
        batch.draw(level3.getLevelTexture(),867,261, 238, 242);
        batch.end();
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
