package com.ninjamoney.angrybirds.elements.character.pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

import java.io.Serializable;

public class MediumPig extends Pigs implements Serializable {
    private transient Texture pigTexture;
//    public final static int HEALTH = 10;

    public MediumPig(Body pigBody) {
        super("Medium Pig", 10, new Texture("elements/char/mediumpig.png"));
        pigTexture = new Texture("elements/char/mediumpig.png");
        super.pigBody = pigBody;
    }

    public void setTexture(Texture texture) {
        pigTexture = texture;
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

