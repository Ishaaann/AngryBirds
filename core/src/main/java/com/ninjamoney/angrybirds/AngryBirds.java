package com.ninjamoney.angrybirds;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class AngryBirds extends Game {

    @Override
    public void create() {
        setScreen(new FirstScreen(this));
//        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
