package com.ninjamoney.angrybirds.elements.character.pig;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Pigs {
    protected Body body;
    protected Texture pigTexture;
    protected int health;

    public abstract void createBody(World world, float x, float y);

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            destroy();
        }
    }

    public void destroy() {
        if (body != null) {
            body.getWorld().destroyBody(body);
        }
        System.out.println("Pig destroyed!");
    }

    public void updatePosition() {
        if (body != null) {
            float x = body.getPosition().x;
            float y = body.getPosition().y;
        }
    }

    public void render() {
    }

    public Texture getPigTexture() {
        return pigTexture;
    }
}
