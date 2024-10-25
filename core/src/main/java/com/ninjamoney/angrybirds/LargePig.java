package com.ninjamoney.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class LargePig {
    private Texture pigTexture;

    public LargePig() {
        pigTexture = new Texture("largepig.png");
    }

    public Texture getPigTexture() {
        return pigTexture;
    }
}
