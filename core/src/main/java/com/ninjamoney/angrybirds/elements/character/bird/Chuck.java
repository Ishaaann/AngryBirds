package com.ninjamoney.angrybirds.elements.character.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class Chuck extends Birds {
    private static final float CHUCK_SPEED = 20.0f;
    private static final float CHUCK_DAMAGE = 30.0f;
    private static final Texture chuckTexture = new Texture("elements/char/chuck.png");

    public Chuck(World world) {
        super("Chuck", 100, CHUCK_SPEED, CHUCK_DAMAGE, chuckTexture, world);
    }

    @Override
    public void activateSpecialAbility() {
        System.out.println("Chuck splits into three!");
    }

    @Override
    public void updatePosition() {
        // Implement Chuck's movement in the Box2D world
    }

    @Override
    public void dealDamage() {
        // Implement logic for dealing damage with Chuck
    }

    public static Texture getChuckTexture() {
        return chuckTexture;
    }
}
