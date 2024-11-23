package com.ninjamoney.angrybirds.phy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class Physics {

    public World world;
    public Box2DDebugRenderer debugRenderer;
    public ShapeRenderer shapeRenderer;

    public Physics() {
        world = new World(new Vector2(0, -9.8f), true);
        debugRenderer = new Box2DDebugRenderer();
        shapeRenderer = new ShapeRenderer();
    }

    public Body createBird(float x, float y, float radius, float density) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;

        body.createFixture(fixtureDef);

        return body;
    }

    public Body createBlock(float x, float y, float width, float height, float density) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.2f;

        body.createFixture(fixtureDef);

        return body;
    }

    public void launchBird(Body bird, float forceX, float forceY) {
        bird.applyForceToCenter(new Vector2(forceX, forceY), true);
    }

    public void setContactListener(ContactListener listener) {
        world.setContactListener(listener);
    }

    public void update(float deltaTime) {
        world.step(1 / 60f, 6, 2);
    }

    public void destroyBody(Body body) {
        world.destroyBody(body);
    }

    public void renderDebug() {
        debugRenderer.render(world, shapeRenderer.getProjectionMatrix());
    }

    public World getWorld() {
        return world;
    }

    public static class MyContactListener implements ContactListener {

        @Override
        public void beginContact(Contact contact) {
            System.out.println("Collision started!");
        }

        @Override
        public void endContact(Contact contact) {
            System.out.println("Collision ended!");
        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {
        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {
        }
    }
}
