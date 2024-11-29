package com.ninjamoney.angrybirds.elements.character.pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

import java.io.Serializable;

public class SmallPig extends Pigs implements Serializable {
    private transient Texture pigTexture;
//    public final static int HEALTH = 10;

    public SmallPig(Body pigBody) {
        super("Small Pig", 10, new Texture("elements/char/smallpig.png"));
        pigTexture = new Texture("elements/char/smallpig.png");
        super.pigBody = pigBody;
    }

    public Body getSmallPigBody() {
        return super.getPigBody();
    }

    public void setTexture(Texture texture) {
        pigTexture = texture;
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
