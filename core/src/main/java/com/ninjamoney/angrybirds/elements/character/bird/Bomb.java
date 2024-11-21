package com.ninjamoney.angrybirds.elements.character.bird;

import com.badlogic.gdx.graphics.Texture;

public class Bomb extends Birds {
    private static final float BOMB_SPEED = 12.0f; // Speed of the Bomb bird
    private static final float BOMB_DAMAGE = 50.0f; // Damage dealt by the Bomb bird
    private static final Texture bombTexture = new Texture("elements/char/bomb.png"); // Texture for Bomb

    public Bomb() {
        super("Bomb", 100, BOMB_SPEED, BOMB_DAMAGE, bombTexture); // Pass properties to superclass
    }

    @Override
    public void activateSpecialAbility() {
        // Implement Bomb's special ability (e.g., explode upon impact)
        // You can add logic for explosion behavior here
        System.out.println("Boom! Bomb explodes on impact.");
    }

    @Override
    public void updatePosition() {
        // Implement the Bomb bird's movement in the Box2D world
        // Typically, you'd update the bird's position based on its Box2D body
    }

    @Override
    public void dealDamage() {
        // Implement logic for dealing damage
        // Typically, you'd apply damage to pigs or structures upon impact
    }

    public static Texture getBombTexture() {
        return bombTexture;
    }
}
