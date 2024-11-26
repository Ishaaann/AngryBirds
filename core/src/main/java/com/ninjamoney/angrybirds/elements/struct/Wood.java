package com.ninjamoney.angrybirds.elements.struct;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Wood extends SolidObjects {
    private Texture woodTexture;
    private String woodType;
    private TextureRegion region;
    private Body body;
    private World world;
    private float hp;

    public Wood(World world, String type, float x, float y, float width, float height) {
        this.world = world;
        this.woodType = type;
        this.body = createBody(world, x, y, width, height);
        this.hp = 40;
        if (woodType.equals("plank")) {
            woodTexture = new Texture("elements/struct/plank.png");
            region = new TextureRegion(woodTexture);
            region.flip(true, false);
        }
        if (woodType.equals("woodTriangle")) {
            woodTexture = new Texture("elements/struct/wood_triangle.png");
            region = new TextureRegion(woodTexture);
            region.flip(true, false);
        }
        body.setUserData(this);
    }

    private Body createBody(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.001f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.1f;

        body.createFixture(fixtureDef);
//        body.setGravityScale(0.8f);
        shape.dispose();

        return body;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getHp() {
        return hp;
    }

    public Texture getWoodTexture() {
        return woodTexture;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public Body getBody() {
        return body;
    }

}
