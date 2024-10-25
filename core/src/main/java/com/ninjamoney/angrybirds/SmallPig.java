package com.ninjamoney.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class SmallPig {
    private Texture pigTexture;

    public SmallPig() {
        pigTexture = new Texture("smallpig.png");
    }

    public Texture getPigTexture() {
        return pigTexture;
    }
}
