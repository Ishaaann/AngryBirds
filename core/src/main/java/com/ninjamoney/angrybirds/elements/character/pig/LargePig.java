package com.ninjamoney.angrybirds.elements.character.pig;

import com.badlogic.gdx.graphics.Texture;

public class LargePig {
    private Texture pigTexture;

    public LargePig() {
        pigTexture = new Texture("elements/char/largepig.png");
    }

    public Texture getPigTexture() {
        return pigTexture;
    }
}
