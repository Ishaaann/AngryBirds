package com.ninjamoney.angrybirds.elements.character.pig;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class Pigs {
    private Body pigBody;

    public Pigs() {
        // Constructor for Pigs
    }

    public Body getPigBody(){
        return pigBody;
    }

    public void setPigBody(Body pigBody){
        this.pigBody = pigBody;
    }
}

