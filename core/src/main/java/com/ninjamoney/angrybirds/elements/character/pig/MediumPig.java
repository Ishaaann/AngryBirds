package com.ninjamoney.angrybirds.elements.character.pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class MediumPig extends Pigs{
    private Texture pigTexture;
//    public final static int HEALTH = 10;

    public MediumPig() {
        super("Medium Pig", 10, new Texture("elements/char/mediumpig.png"));
        pigTexture = new Texture("elements/char/mediumpig.png");
    }

    public Body getMediumPigBody() {
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

