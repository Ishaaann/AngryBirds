package com.ninjamoney.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ninjamoney.angrybirds.screens.FirstScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class AngryBirds extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public Music themeMusic;


    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        themeMusic = Gdx.audio.newMusic(Gdx.files.internal("game/angry-birds-drip.mp3"));
        setScreen(new FirstScreen(this));
//        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    public boolean isMusicPlaying() {
        return themeMusic.isPlaying();
    }

    public void toggleMusic() {
        if(themeMusic.isPlaying()) {
            themeMusic.pause();
        } else {
            themeMusic.play();
        }
    }
}
