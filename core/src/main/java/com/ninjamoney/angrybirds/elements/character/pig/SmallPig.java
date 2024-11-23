package com.ninjamoney.angrybirds.elements.character.pig;

import com.badlogic.gdx.graphics.Texture;

public class SmallPig {
    private Texture pigTexture;

    public SmallPig() {
        pigTexture = new Texture("elements/char/smallpig.png");
    }

    public Texture getPigTexture() {
        return pigTexture;
    }
}
