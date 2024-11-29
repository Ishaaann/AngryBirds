package com.ninjamoney.angrybirds.elements.character.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.io.Serializable;

public class Red extends Birds implements Serializable {
    private static final float RED_SPEED = 15.0f;
    private static final float damage = 25.0f;
    private static final transient Texture redTexture = new Texture("elements/char/red.png");

    public Red() {
        super("Red", 100, RED_SPEED, damage, redTexture);
//        this.setBirdBody(createCircle(2f,2f,0.5f,false));
//        pos = this.getRedBody().getPosition();
    }

    @Override
    public void activateSpecialAbility() {
        // Implement Red's special ability (if any)
        System.out.println("Red has no special ability.");
        // You can add a behavior here later if needed
    }

    @Override
    public void updatePosition() {
        // Implement Red's movement in the Box2D world
        // This would be similar to how Chuck and Bomb move in Box2D
    }

    @Override
    public void dealDamage() {
        // Implement logic for dealing damage
        // Red would deal damage when colliding with structures or pigs
    }

    public static Texture getRedTexture() {
        return redTexture;
    }

    public Body getRedBody() {
        return super.getBirdBody();
    }

    public void setBirdBody(Body birdBody) {
        super.birdBody = birdBody;
    }

}
