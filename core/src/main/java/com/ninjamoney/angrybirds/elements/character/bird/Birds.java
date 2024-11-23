package com.ninjamoney.angrybirds.elements.character.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Birds {
    private String name;
    private int health;
    private Texture birdTexture;
    private float speed;
    private float damage;
    private Body birdBody;


    public Birds(String name, int health, float speed, float damage, Texture birdTexture) {
        this.name = name;
        this.health = health;
        this.speed = speed;
        this.damage = damage;
        this.birdTexture = birdTexture;
    }



    public abstract void activateSpecialAbility();

    // Method to update the bird's position based on Box2D
    public abstract void updatePosition();

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Texture getBirdTexture() {
        return birdTexture;
    }

    public void setBirdTexture(Texture birdTexture) {
        this.birdTexture = birdTexture;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

//    public Body getBirdBody() {
//        return birdBody;
//    }

//    public void setBirdBody(Body birdBody) {
//        this.birdBody = birdBody;
//    }

    // Method to deal damage to pigs or structures (to be implemented in subclasses)
    public abstract void dealDamage();
}
