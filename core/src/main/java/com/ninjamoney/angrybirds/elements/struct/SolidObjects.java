package com.ninjamoney.angrybirds.elements.struct;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.io.Serializable;
import java.util.Vector;

public class SolidObjects implements Serializable {
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

    public Vector2 getPos() {
        if( this.getBody()!=null) {
            Vector2 posi = getBody().getPosition();
            return posi;
        }
        return null;
    }

    public Vector2 getVelocity() {
        Vector2 vel = getBody().getLinearVelocity();
        return vel;
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
