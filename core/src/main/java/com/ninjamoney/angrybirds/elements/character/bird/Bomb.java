package com.ninjamoney.angrybirds.elements.character.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Bomb extends Birds {
    private static final float BOMB_SPEED = 12.0f;
    private static final float BOMB_DAMAGE = 50.0f;
    private static final Texture bombTexture = new Texture("elements/char/bomb.png");

    public Bomb(World world) {
        super("Bomb", 100, BOMB_SPEED, BOMB_DAMAGE, bombTexture, world);
    }

    @Override
    public void activateSpecialAbility() {
        System.out.println("Boom! Bomb explodes on impact.");
    }

    @Override
    public void updatePosition() {
        // Implement Bomb's movement in the Box2D world
    }

    @Override
    public void dealDamage() {
        // Implement logic for dealing damage
    }

    public static Texture getBombTexture() {
        return bombTexture;
    }
}
