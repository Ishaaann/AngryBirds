package com.ninjamoney.angrybirds.elements.struct;

import com.badlogic.gdx.graphics.Texture;

public final class Catapult {
    private int x; // x coordinate of the catapult
    private int y; // y coordinate of the catapult
    private Texture catapultTexture = new Texture("elements/struct/catapult.png");

    public Catapult(int x, int y) {
        this.x = x;


        this.y = y;
    }

    public void drag() {
        // Drag the catapult
    }

    public void release() {
        // Release the catapult
    }

    public Texture getCatapultTexture() {
        return catapultTexture;
    }
}
