package com.ninjamoney.angrybirds.elements.character.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

import java.io.Serializable;

public class Chuck extends Birds implements Serializable {
    private static final float CHUCK_SPEED = 20.0f;
    private static final float damage = 30.0f;
    private static final transient Texture chuckTexture = new Texture("elements/char/chuck.png");

    public Chuck() {
        super("Chuck", 100, CHUCK_SPEED, damage, chuckTexture);
    }

    @Override
    public void activateSpecialAbility() {
        // Implement Chuck's special ability (e.g., splitting into multiple birds)
        System.out.println("Chuck splits into three!");
        // Add logic for splitting here or any other special ability
    }

    @Override
    public void updatePosition() {
        // Implement Chuck's movement in the Box2D world
        // Update Chuck's position based on its Box2D body
    }

    @Override
    public void dealDamage() {
        // Implement logic for dealing damage with Chuck
        // Apply damage to pigs or structures when Chuck collides
    }

    public static Texture getChuckTexture() {
        return chuckTexture;
    }

    public Body getChuckBody() {
        return super.getBirdBody();
    }

    public void setBirdBody(Body birdBody) {
        super.birdBody = birdBody;
    }
}
