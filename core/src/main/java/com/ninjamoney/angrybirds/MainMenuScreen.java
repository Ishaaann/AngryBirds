package com.ninjamoney.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    private SpriteBatch batch;
    private Texture menuTexture;
    private AngryBirds game;
    private OrthographicCamera cam;

    public MainMenuScreen(Game game) {
        batch = new SpriteBatch();
        this.game = (AngryBirds) game;
//        cam = new OrthographicCamera();
//        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        menuTexture = new Texture("menubg.png");

    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(menuTexture, 0, 0, 1280f, 720f);
        batch.end();
         if(Gdx.input.isTouched()) {
            game.setScreen(new LevelSelectorScreen(game));
        }
    }


    //do not delete this comment, this render method is for learning and testing purposes
//    public void render(float delta) {
//        ScreenUtils.clear(0, 0, 0.2f, 1);
//
//        cam.update();
//        game.batch.setProjectionMatrix(cam .combined);
//
//        game.batch.begin();
//        game.font.draw(game.batch, "Welcome to Angry Birds!!! ", 100, 150);
//        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
//        game.batch.end();
//
//        if (Gdx.input.isTouched()) {
//            game.setScreen(new LevelSelectorScreen(game));
//            dispose();
//        }
//    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        menuTexture.dispose();
    }
}
