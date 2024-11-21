package com.ninjamoney.angrybirds.elements.character.bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class Birds {
    private String name;
    private int health;
    private Texture birdTexture;
    private float speed;
    private float damage;
    private Body birdBody;
    private World world;

    public Birds(String name, int health, float speed, float damage, Texture birdTexture, World world) {
        this.name = name;
        this.health = health;
        this.speed = speed;
        this.damage = damage;
        this.birdTexture = birdTexture;
        this.world = world;
        createBirdBody();
    }

    public abstract void activateSpecialAbility();

    public abstract void updatePosition();

    public abstract void dealDamage();

    private void createBirdBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.2f;

        birdBody = world.createBody(bodyDef);
        birdBody.createFixture(fixtureDef);

        shape.dispose();
    }

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

    public Body getBirdBody() {
        return birdBody;
    }

    public void setBirdBody(Body birdBody) {
        this.birdBody = birdBody;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
