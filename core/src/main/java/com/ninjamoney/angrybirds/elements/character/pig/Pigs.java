package com.ninjamoney.angrybirds.elements.character.pig;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Pigs {
    private String name;
    private float health;
    private Texture pigTexture;
    public Body pigBody;
    public static int HEALTH;

    public Pigs(String name, int health, Texture pigTexture) {
        this.name = name;
        this.HEALTH = health;
        this.health = health;
        this.pigTexture = pigTexture;
    }

    // Method to update the pig's position based on Box2D
    public abstract void updatePosition();

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public Texture getPigTexture() {
        return pigTexture;
    }

    public void setPigTexture(Texture pigTexture) {
        this.pigTexture = pigTexture;
    }

    public Body getPigBody() {
        return pigBody;
    }

    public void setPigBody(Body pigBody) {
        this.pigBody = pigBody;
    }

    // Method to deal damage to birds or structures (to be implemented in subclasses)
    public abstract void dealDamage();
}
