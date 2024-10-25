package com.ninjamoney.angrybirds.elements.character.bird;

import com.badlogic.gdx.graphics.Texture;

public abstract class Birds {
    private String name;
    private int health;
    private Texture birdTexture;

    public Birds(String name, int health) {
        this.name = name;
        this.health = health;
//        birdTexture = new Texture();
    }
}
