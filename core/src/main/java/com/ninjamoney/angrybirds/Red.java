package com.ninjamoney.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class Red extends Birds {
    private com.ninjamoney.angrybirds.specialAbility specialAbility;
    private final Texture redTexture;

    public Red() {
        super("Red", 100);
        redTexture = new Texture("elements/char/red.png");
    }

    public Texture getRedTexture() {
        return redTexture;
    }
}
