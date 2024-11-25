package com.ninjamoney.angrybirds.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ninjamoney.angrybirds.AngryBirds;
import com.ninjamoney.angrybirds.elements.character.bird.Bomb;
import com.ninjamoney.angrybirds.elements.character.bird.Chuck;
import com.ninjamoney.angrybirds.elements.character.bird.Red;
import com.ninjamoney.angrybirds.elements.struct.Catapult;
import com.ninjamoney.angrybirds.phy.TrajectoryPredictor;

public class Level1 implements Screen {
    private AngryBirds game;
    private SpriteBatch batch;
    private final int levelNumber = 1;
    private final boolean isLocked = false;
    private Texture background;
    private Red red;
    private Chuck chuck;
    private Bomb bomb;
    public Stage stage;
    private Body gnd;
    public Texture redTexture;
    private Texture ground;
    private Texture slingshot;
    private Array<Body> boxes;
    private Texture boxTexture;
    private Catapult cp;
    private TrajectoryPredictor trajectoryPredictor;

    private static World world;
    private Box2DDebugRenderer debugRenderer;

    public Level1(AngryBirds game, int levelNumber, boolean isLocked) {
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("game/bg/background.jpg");
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        redTexture = new Texture("elements/char/red.png");
        cp = new Catapult(0, 0);

         // Assign to the class field

        world = new World(new Vector2(0, -9.8f), true);
        debugRenderer = new Box2DDebugRenderer();

        // Calculate the position of the slingshot
        float slingshotX = stage.getViewport().getWorldWidth() / 8f;
        float slingshotY = stage.getViewport().getWorldHeight() / 5f;
        float slingshotHeight = 400 / 4f; // Height of the slingshot texture
        cp = new Catapult(slingshotX, slingshotY);

        this.trajectoryPredictor = new TrajectoryPredictor();

        // Position Chuck on top of the slingshot
        float chuckX = slingshotX + 20; // Offset to center Chuck
        float chuckY = slingshotY + slingshotHeight - 10; // Slightly below the top

        // Initialize birds
        red = new Red();
        red.setBirdBody(createCircle(120, 100, 20f, false)); // Initialize the Red bird body

        chuck = new Chuck();
        chuck.setBirdBody(createCircle(chuckX, chuckY, 20f, false)); // Initialize Chuck on the slingshot

        bomb = new Bomb();
        bomb.setBirdBody(createCircle(50, 50, 30f, false)); // Initialize Bomb bird

        gnd = createGround();
    }

    private Body createGround() {
        BodyDef ground = new BodyDef();
        ground.type = BodyDef.BodyType.StaticBody;
        ground.position.set(0, 0);

        Body groundBody = world.createBody(ground);
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight() / 5);

        FixtureDef groundFD = new FixtureDef();
        groundFD.density = 0;
        groundFD.shape = groundShape;

        groundBody.createFixture(groundFD);
        groundShape.dispose();
        return groundBody;
    }

    public static Body createCircle(float x, float y, float radius, boolean isStatic) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;

        body.createFixture(fixtureDef);
        body.setGravityScale(0);
        body.setAngularDamping(1f);
        circle.dispose();

        return body;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        ground = new Texture("game/bg/ground.png");
        slingshot = cp.getCatapultTexture();
        boxTexture = new Texture("elements/struct/plank.png");

        boxes = new Array<Body>();
        createStructure();
    }

    private void createStructure() {
        float boxWidth = 50; // Box width in world units
        float boxHeight = 50; // Box height in world units

        // Create a simple stack of boxes
        float startX = stage.getViewport().getWorldWidth() / 2f;
        float startY = stage.getViewport().getWorldHeight() / 4f;
        int rows = 3; // Number of rows
        int columns = 3; // Number of columns

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                float x = startX + col * (boxWidth + 0.1f); // Add spacing between boxes
                float y = startY + row * (boxHeight + 0.1f);
                boxes.add(createBox(x, y, boxWidth, boxHeight));
            }
        }
    }

    private Body createBox(float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2); // Box dimensions are half-extents

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;

        body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();

        // Draw the background and ground
        batch.draw(background, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        batch.draw(ground, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight() / 5);
        batch.draw(slingshot, stage.getViewport().getWorldWidth() / 8, stage.getViewport().getWorldHeight() / 5, 144 / 3f, 400 / 4f);

        // Draw the structure (boxes)
        for (Body box : boxes) {
            Vector2 pos = box.getPosition();
            batch.draw(boxTexture, pos.x - 1f, pos.y - 0.5f, 2f, 1f); // Box size matches dimensions
        }

        // Draw Red bird with angle
        TextureRegion redTexture = new TextureRegion(red.getBirdTexture());
        batch.draw(redTexture,
            red.getRedBody().getPosition().x - 20, red.getRedBody().getPosition().y - 20,
            20, 20, 40, 40, 1, 1,
            (float) Math.toDegrees(red.getRedBody().getAngle())); // Apply rotation in degrees

        // Draw Chuck bird with angle
        TextureRegion chuckTexture = new TextureRegion(chuck.getBirdTexture());
        batch.draw(chuckTexture,
            chuck.getChuckBody().getPosition().x - 20f, chuck.getChuckBody().getPosition().y - 20f,
            20, 20, 40, 40, 1, 1,
            (float) Math.toDegrees(chuck.getChuckBody().getAngle())); // Apply rotation in degrees

        // Draw Bomb bird with angle
        TextureRegion bombTexture = new TextureRegion(bomb.getBirdTexture());
        batch.draw(bombTexture,
            bomb.getBombBody().getPosition().x - 30f, bomb.getBombBody().getPosition().y - 25f,
            30, 30, 60, 60, 1, 1,
            (float) Math.toDegrees(bomb.getBombBody().getAngle())); // Apply rotation in degrees

        // Render the trajectory
        trajectoryPredictor.render(batch);

        batch.end();

        // Update the physics world
        world.step(1 / 60f, 6, 2);

        // Handle input for pulling and releasing the bird
        if (Gdx.input.isTouched()) {
            cp.pull(chuck); // Start pulling Chuck
            cp.updatePull(); // Update Chuck's position

            // Update the trajectory
            Vector2 dragPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            stage.getViewport().unproject(dragPos);

            Vector2 slingshotPos = new Vector2(
                stage.getViewport().getWorldWidth() / 8f + (144/3f) * 0.75f,  // X position + offset to slingshot pocket
                stage.getViewport().getWorldHeight() / 5f + (400/4f) * 0.7f   // Y position + offset to slingshot pocket
            );

            trajectoryPredictor.updateTrajectory(slingshotPos, dragPos);
        } else {
            cp.release(); // Release Chuck
        }

        // Uncomment to visualize Box2D debug shapes
        // debugRenderer.render(world, stage.getViewport().getCamera().combined);
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        ground.dispose();
        slingshot.dispose();
        boxTexture.dispose();
        debugRenderer.dispose();
        world.dispose();
        trajectoryPredictor.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        // Destroy and recreate the ground body
        if (gnd != null) {
            world.destroyBody(gnd);
        }
        gnd = createGround();
        batch.begin();
        batch.draw(background, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        batch.end();
        // Center the camera in the new viewport
        stage.getViewport().getCamera().position.set(stage.getViewport().getWorldWidth() / 2f, stage.getViewport().getWorldHeight() / 2f, 0);
        stage.getViewport().getCamera().update();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    public World getWorld() {
        return world;
    }
}
