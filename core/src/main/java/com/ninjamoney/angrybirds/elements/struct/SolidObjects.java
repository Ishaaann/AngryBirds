package com.ninjamoney.angrybirds.elements.struct;

import com.badlogic.gdx.physics.box2d.Body;

public class SolidObjects {
    private float hp;

    public SolidObjects(float hp) {
        this.hp = hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getHp() {
        return hp;
    }

    public Body getBody() {
        if (this instanceof Rock) {
            return ((Rock) this).getBody();
        }
        if (this instanceof Wood) {
            return ((Wood) this).getBody();
        }
        if (this instanceof Ice) {
            return ((Ice) this).getBody();
        }
        return null;
    }
}
