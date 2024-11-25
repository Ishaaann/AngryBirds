package com.ninjamoney.angrybirds.elements.character.pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class SmallPig extends Pigs{
    private Texture pigTexture;

    public SmallPig() {
        super("Small Pig", 50, new Texture("elements/char/smallpig.png"));
        pigTexture = new Texture("elements/char/smallpig.png");
    }

    public Body getSmallPigBody() {
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
