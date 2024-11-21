package com.ninjamoney.angrybirds.elements.character.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Red extends Birds {
    private static final float RED_SPEED = 15.0f;
    private static final float RED_DAMAGE = 25.0f;
    private static final Texture redTexture = new Texture("elements/char/red.png");

    public Red(World world) {
        super("Red", 100, RED_SPEED, RED_DAMAGE, redTexture, world);
    }

    @Override
    public void activateSpecialAbility() {
        System.out.println("Red has no special ability.");
    }

    @Override
    public void updatePosition() {
        // Implement Red's movement in the Box2D world
    }

    @Override
    public void dealDamage() {
        // Implement logic for dealing damage
    }

    public static Texture getRedTexture() {
        return redTexture;
    }
}
