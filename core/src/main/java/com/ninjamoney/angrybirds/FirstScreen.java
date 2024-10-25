package com.ninjamoney.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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


    private final int originalWidth = 1080;
    private final int originalHeight = 720;

    private OrthographicCamera camera;

    public FirstScreen(AngryBirds game) {
        this.game = game;
        batch = new SpriteBatch();
        splashTexture = new Texture("entry.png");
        camera = new OrthographicCamera();
//        camera.setToOrtho(false, originalWidth, originalHeight);
        Music themeMusic = Gdx.audio.newMusic(Gdx.files.internal("game/angry-birds-drip.mp3"));
        themeMusic.setLooping(true);
        themeMusic.setVolume(0.3f);
        themeMusic.play();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(splashTexture, 0, 0, 1280f, 720f);
        batch.setColor(1, 1, 1, alpha);
        batch.end();

        if (!transitioning) {
            alpha -= delta / fadeDuration;
            if (alpha <= 0) {
                alpha = 0;
                transitioning = true;
                System.out.println("Transitioning to MainMenuScreen...");
            }
        }
        else {
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
