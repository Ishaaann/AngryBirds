package com.ninjamoney.angrybirds.elements.struct;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.ninjamoney.angrybirds.levels.Level1;

import static com.ninjamoney.angrybirds.phy.Collisions.queueBodyForDestruction;

public class Ice extends SolidObjects {
    private Texture iceTexture;
    private String iceType;
    private TextureRegion region;
    private Body body;
    private World world;
    private float hp;

    public Ice(World world, String type, float x, float y, float width, float height) {
        super(10);
        this.world = world;
        this.iceType = type;
        this.body = createBody(world, x, y, width, height);
        this.hp = 30;
        if (iceType.equals("block")) {
            iceTexture = new Texture("elements/struct/ice_block.png");
            region = new TextureRegion(iceTexture);
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
        shape.dispose();

        return body;
    }

    public void reduceHp(float damage) {
        this.hp -= damage;
        if (this.hp <= 0) {
            queueBodyForDestruction(this.body);
        }
    }

    public float getHp() {
        return hp;
    }

    public Texture getIceTexture() {
        return iceTexture;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public Body getBody() {
        return body;
    }
}
