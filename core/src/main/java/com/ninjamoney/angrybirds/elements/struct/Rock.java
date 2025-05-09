package com.ninjamoney.angrybirds.elements.struct;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.io.Serializable;

public class Rock extends SolidObjects implements Serializable {
    private transient Texture rockTexture;
    private String rockType;
    private transient TextureRegion region;
    private transient Body body;
    private transient World world;

    public Rock(World world, String type, float x, float y, float width, float height) {
        super(10);
        this.world = world;
        this.rockType = type;
        this.body = createBody(world, x, y, width, height);
        if (rockType.equals("circle")) {
            rockTexture = new Texture("elements/struct/rock_circle.png");
            region = new TextureRegion(rockTexture);
            region.flip(true, false);
        }
        if (rockType.equals("rockBlock")) {
            rockTexture = new Texture("elements/struct/rock_block.png");
            region = new TextureRegion(rockTexture);
            region.flip(true, false);
        }
        if(rockType.equals("rockPlank")) {
            rockTexture = new Texture("elements/struct/rock_plank.png");
            region = new TextureRegion(rockTexture);
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

    public Texture getRockTexture() {
        return rockTexture;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public Body getBody() {
        return body;
    }
}
