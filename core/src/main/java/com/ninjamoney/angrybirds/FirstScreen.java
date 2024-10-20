package com.ninjamoney.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class FirstScreen implements Screen {
    private SpriteBatch batch;
    private Texture splashTexture;
    private float alpha = 1f;
    private float fadeDuration = 2f;
    private boolean transitioning = false;
    private AngryBirds game;


    private final int originalWidth = 1154;
    private final int originalHeight = 692;

    private OrthographicCamera camera;

    public FirstScreen(AngryBirds game) {
        this.game = game;
        batch = new SpriteBatch();
        splashTexture = new Texture("entryScreen.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, originalWidth, originalHeight);
        Music themeMusic = Gdx.audio.newMusic(Gdx.files.internal("angry_birds.mp3"));
        themeMusic.setLooping(true);
        themeMusic.setVolume(0.5f);
        themeMusic.play();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.setColor(1, 1, 1, alpha);
        float x = (Gdx.graphics.getWidth() - originalWidth) / 2;
        float y = (Gdx.graphics.getHeight() - originalHeight) / 2;
        batch.draw(splashTexture, x, y, originalWidth, originalHeight);
        batch.end();

        if (!transitioning) {
            alpha -= delta / fadeDuration;
            if (alpha <= 0) {
                alpha = 0;
                transitioning = true;
                System.out.println("Transitioning to MainMenuScreen...");
            }
        } else {
            alpha += delta / fadeDuration;
            if (alpha >= 1) {
                alpha = 1;
                game.setScreen(new MainMenuScreen(game));
                System.out.println("Switched to MainMenuScreen.");
            }
        }


    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

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
        splashTexture.dispose();
    }
}
