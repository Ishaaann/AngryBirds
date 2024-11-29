package com.ninjamoney.angrybirds.elements.character.pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

import java.io.Serializable;

public class LargePig extends Pigs implements Serializable {
    private transient Texture pigTexture;
//    public final static int HEALTH = 100;

    public LargePig(Body pigBody) {
        super("Large Pig", 100, new Texture("elements/char/largepig.png"));
        pigTexture = new Texture("elements/char/largepig.png");
        super.pigBody = pigBody;
    }

    public void setTexture(Texture texture) {
        pigTexture = texture;
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
