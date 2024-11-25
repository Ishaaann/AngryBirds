package com.ninjamoney.angrybirds.elements.character.pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class LargePig extends Pigs{
    private Texture pigTexture;

    public LargePig() {
        super("Large Pig", 200, new Texture("elements/char/largepig.png"));
        pigTexture = new Texture("elements/char/largepig.png");
    }

    public Body getLargePigBody() {
        return super.getPigBody();
    }

    @Override
    public void updatePosition() {

    }

    public Texture getPigTexture() {
        return pigTexture;
    }

    @Override
    public void dealDamage() {

    }
}
