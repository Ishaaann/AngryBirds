package com.ninjamoney.angrybirds.elements.character.bird;

import com.badlogic.gdx.graphics.Texture;

public class Red extends Birds {
    private com.ninjamoney.angrybirds.elements.struct.specialAbility specialAbility;
    private final Texture redTexture;

    public Red() {
        super("Red", 100);
        redTexture = new Texture("elements/char/red.png");
    }

    public Texture getRedTexture() {
        return redTexture;
    }
}
