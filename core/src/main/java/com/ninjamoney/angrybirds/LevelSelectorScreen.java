package com.ninjamoney.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelSelectorScreen implements Screen {
    private SpriteBatch batch;
    private AngryBirds game;
    private Texture levelSelectorTexture;

    public LevelSelectorScreen(AngryBirds game) {
        this.game = game;
        batch = new SpriteBatch();
        levelSelectorTexture = new Texture("levels.png");
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
//        game.setScreen(new LevelSelectorScreen(game));
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(levelSelectorTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
