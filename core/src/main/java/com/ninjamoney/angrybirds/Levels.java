package com.ninjamoney.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class Levels {
    private Texture levelTexture;
    private int levelNumber;
    private boolean isLocked;
    private Texture background = new Texture("background.jpg");

    public Levels(int levelNumber, boolean isLocked, Texture levelImage) {
        this.levelNumber = levelNumber;
        this.isLocked = isLocked;
        levelTexture = levelImage;
    }

    public Texture getLevelTexture() {
        return levelTexture;
    }

}
