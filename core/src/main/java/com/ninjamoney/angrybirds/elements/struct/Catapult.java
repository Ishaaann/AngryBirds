package com.ninjamoney.angrybirds.elements.struct;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public final class Catapult {
    private float x; // x coordinate of the catapult
    private float y; // y coordinate of the catapult
    private Texture catapultTexture = new Texture("elements/struct/catapult.png");

    public Catapult(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void drag() {
        // Drag the catapult
        if(Gdx.input.isTouched()) {
            // Drag the catapult
        }
    }

    public void release() {
        // Release the catapult
    }

    public Texture getCatapultTexture() {
        return catapultTexture;
    }
}
