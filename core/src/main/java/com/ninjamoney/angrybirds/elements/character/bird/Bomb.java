package com.ninjamoney.angrybirds.elements.character.bird;

import com.badlogic.gdx.graphics.Texture;

public class Bomb extends Birds {
    private com.ninjamoney.angrybirds.elements.struct.specialAbility specialAbility;
    private final Texture bombTexture;

    public Bomb() {
        super("Bomb", 100);
        bombTexture = new Texture("elements/char/bomb.png");
    }

    public Texture getBombTexture() {
        return bombTexture;
    }
}
