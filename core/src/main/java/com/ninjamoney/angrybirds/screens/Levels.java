package com.ninjamoney.angrybirds.screens;

import com.badlogic.gdx.graphics.Texture;
import com.ninjamoney.angrybirds.elements.struct.Structure;

public class Levels {
    private Texture levelTexture;
    private int levelNumber;
    private boolean isLocked;
    private Structure structure;
    private Texture background = new Texture("game/bg/background.jpg");

    public Levels(int levelNumber, boolean isLocked, Texture levelImage) {
        this.levelNumber = levelNumber;
        this.isLocked = isLocked;
        levelTexture = levelImage;
    }

    public Texture getLevelTexture() {
        return levelTexture;
    }

}
