//package com.ninjamoney.angrybirds.levels;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.*;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.utils.Array;
//import com.badlogic.gdx.utils.Queue;
//import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.ninjamoney.angrybirds.AngryBirds;
//import com.ninjamoney.angrybirds.elements.character.bird.Birds;
//import com.ninjamoney.angrybirds.elements.character.bird.Bomb;
//import com.ninjamoney.angrybirds.elements.character.bird.Chuck;
//import com.ninjamoney.angrybirds.elements.character.bird.Red;
//import com.ninjamoney.angrybirds.elements.struct.Catapult;
//
//public class Level1 implements Screen {
//    private AngryBirds game;
//    private SpriteBatch batch;
//    private final int levelNumber = 1;
//    private final boolean isLocked = false;
//    private Texture background;
//    private Red red;
//    private Chuck chuck;
//    private Bomb bomb;
//
//
//    private Queue<Birds> birdQueue;
//    public Stage stage;
//    private Body gnd;
//    public Texture redTexture;
//    private Texture ground;
//    private Texture slingshot;
//    private Array<Body> boxes;
//    private Texture boxTexture;
//    private Catapult cp;
//
//    private static World world;
//    private Box2DDebugRenderer debugRenderer;
//
//    public Level1(AngryBirds game, int levelNumber, boolean isLocked) {
//        this.game = game;
//        batch = new SpriteBatch();
//        background = new Texture("game/bg/background.jpg");
//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(stage);
//        redTexture = new Texture("elements/char/red.png");
//        cp = new Catapult(0, 0);
//
//
//         // Assign to the class field
//
//        world = new World(new Vector2(0, -9.8f), true);
//        debugRenderer = new Box2DDebugRenderer();
//
//        // Calculate the position of the slingshot
//        float slingshotX = stage.getViewport().getWorldWidth() / 8f;
//        float slingshotY = stage.getViewport().getWorldHeight() / 5f;
//        float slingshotHeight = 400 / 4f; // Height of the slingshot texture
//        cp = new Catapult(slingshotX, slingshotY);
//
////        // Position Chuck on top of the slingshot
////        float chuckX = slingshotX + 20; // Offset to center Chuck
////        float chuckY = slingshotY + slingshotHeight - 10; // Slightly below the top
//
//        // Initialize birds
//        red = new Red();
//        red.setBirdBody(createCircle(120, 100, 20f, false)); // Initialize the Red bird body
//
//        chuck = new Chuck();
//        chuck.setBirdBody(createCircle(90, 90, 20f, false)); // Initialize Chuck on the slingshot
//
//        bomb = new Bomb();
//        bomb.setBirdBody(createCircle(50, 50, 30f, false)); // Initialize Bomb bird
//
//
//        birdQueue = new Queue<Birds>();
//        birdQueue.addLast(red);
//        birdQueue.addLast(chuck);
//        birdQueue.addLast(bomb);
//
//        gnd = createGround();
//        setNextBirdOnSlingshot();
//    }
//
//
//    private void setNextBirdOnSlingshot() {
//        if (birdQueue.size > 0) {
//            Birds nextBird = birdQueue.removeFirst();
//            float slingshotX = stage.getViewport().getWorldWidth() / 8f;
//            float slingshotY = stage.getViewport().getWorldHeight() / 5f;
//            float slingshotHeight = 400 / 4f; // Height of the slingshot texture
//
//            float birdX = slingshotX + 20; // Offset to center the bird
//            float birdY = slingshotY + slingshotHeight - 10; // Slightly below the top
//
//            nextBird.getBirdBody().setTransform(birdX, birdY, 0);
//            cp.setCurrentBird(nextBird);
//        }
//    }
//
//    private Body createGround() {
//        BodyDef ground = new BodyDef();
//        ground.type = BodyDef.BodyType.StaticBody;
//        ground.position.set(0, 0);
//
//        Body groundBody = world.createBody(ground);
//        PolygonShape groundShape = new PolygonShape();
//        groundShape.setAsBox(stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight() / 5);
//
//        FixtureDef groundFD = new FixtureDef();
//        groundFD.density = 0;
//        groundFD.shape = groundShape;
//
//        groundBody.createFixture(groundFD);
//        groundShape.dispose();
//        return groundBody;
//    }
//
//    public static Body createCircle(float x, float y, float radius, boolean isStatic) {
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        bodyDef.position.set(x, y);
//
//        Body body = world.createBody(bodyDef);
//
//        CircleShape circle = new CircleShape();
//        circle.setRadius(radius);
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = circle;
//        fixtureDef.density = 1.0f;
//        fixtureDef.friction = 0.5f;
//        fixtureDef.restitution = 0.3f;
//
//        body.createFixture(fixtureDef);
//        body.setGravityScale(0);
//        body.setAngularDamping(1f);
//        circle.dispose();
//
//        return body;
//    }
//
//    @Override
//    public void show() {
//        Gdx.input.setInputProcessor(stage);
//        ground = new Texture("game/bg/ground.png");
//        slingshot = cp.getCatapultTexture();
//        boxTexture = new Texture("elements/struct/plank.png");
//
////        boxes = new Array<Body>();
////        createStructure();
//    }
//
//    private Body createBox(float x, float y, float width, float height) {
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        bodyDef.position.set(x, y);
//
//        Body body = world.createBody(bodyDef);
//
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(width/2 , height/2 ); // Box dimensions are half-extents
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//        fixtureDef.density = 1.0f;
//        fixtureDef.friction = 0.5f;
//        fixtureDef.restitution = 0.3f;
//
//        body.createFixture(fixtureDef);
//        shape.dispose();
//
//        return body;
//    }
//
//    @Override
//    public void render(float delta) {
//        ScreenUtils.clear(0, 0, 0, 1);
//
//        batch.begin();
//
//        // Draw the background and ground
//        batch.draw(background, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
//        batch.draw(ground, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight() / 5);
//        batch.draw(slingshot, stage.getViewport().getWorldWidth() / 8, stage.getViewport().getWorldHeight() / 5, 144 / 3f, 400 / 4f);
//
//        // Draw the structure (boxes)
////        for (Body box : boxes) {
////            Vector2 pos = box.getPosition();
////            batch.draw(boxTexture, pos.x - 1f, pos.y - 0.5f, 2f, 1f); // Box size matches dimensions
////        }
//        createBox(4* stage.getWidth()/5,stage.getHeight()/5,20,100);
//
//        // Draw Red bird with angle
//        TextureRegion redTexture = new TextureRegion(red.getBirdTexture());
//        batch.draw(redTexture,
//            red.getRedBody().getPosition().x - 20, red.getRedBody().getPosition().y - 20,
//            20, 20, 40, 40, 1, 1,
//            (float) Math.toDegrees(red.getRedBody().getAngle())); // Apply rotation in degrees
//
//        // Draw Chuck bird with angle
//        TextureRegion chuckTexture = new TextureRegion(chuck.getBirdTexture());
//        batch.draw(chuckTexture,
//            chuck.getChuckBody().getPosition().x - 20f, chuck.getChuckBody().getPosition().y - 20f,
//            20, 20, 40, 40, 1, 1,
//            (float) Math.toDegrees(chuck.getChuckBody().getAngle())); // Apply rotation in degrees
//
//        // Draw Bomb bird with angle
//        TextureRegion bombTexture = new TextureRegion(bomb.getBirdTexture());
//        batch.draw(bombTexture,
//            bomb.getBombBody().getPosition().x - 30f, bomb.getBombBody().getPosition().y - 25f,
//            30, 30, 60, 60, 1, 1,
//            (float) Math.toDegrees(bomb.getBombBody().getAngle())); // Apply rotation in degrees
//
//        // Render the trajectory
//        cp.trajectoryPredictor.render(batch);
//
//        batch.end();
//
//        // Update the physics world
//        world.step(1 / 60f, 6, 2);
//        world.step(1 / 60f, 6, 2);
//
//        // Handle input for pulling and releasing the bird
//        handleInput();
//
//        // Uncomment to visualize Box2D debug shapes
//         debugRenderer.render(world, stage.getViewport().getCamera().combined);
//    }
//
//    public void handleInput(){
//        if (Gdx.input.isTouched()) {
//            if (cp.getCurrentBird() != null) {
//                cp.pull(cp.getCurrentBird()); // Start pulling the current bird
//                cp.updatePull(); // Update the bird's position
//
//                // Update the trajectory
//                Vector2 dragPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
//                stage.getViewport().unproject(dragPos);
//
//                Vector2 slingshotPos = new Vector2(
//                    stage.getViewport().getWorldWidth() / 8f + (144/3f) * 0.75f,  // X position + offset to slingshot pocket
//                    stage.getViewport().getWorldHeight() / 5f + (400/4f) * 0.7f   // Y position + offset to slingshot pocket
//                );
//
//                cp.trajectoryPredictor.updateTrajectory(slingshotPos, dragPos);
//            }
//        } else {
//            if (cp.isPulling()) {
//                cp.release(); // Release the bird
//                setNextBirdOnSlingshot(); // Set the next bird on the slingshot
//            }
//        }
//    }
//
//    @Override
//    public void dispose() {
//        batch.dispose();
//        background.dispose();
//        ground.dispose();
//        slingshot.dispose();
//        boxTexture.dispose();
//        debugRenderer.dispose();
//        world.dispose();
//        cp.trajectoryPredictor.dispose();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        stage.getViewport().update(width, height, true);
//
//        // Destroy and recreate the ground body
//        if (gnd != null) {
//            world.destroyBody(gnd);
//        }
//        gnd = createGround();
//        batch.begin();
//        batch.draw(background, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
//        batch.end();
//        // Center the camera in the new viewport
//        stage.getViewport().getCamera().position.set(stage.getViewport().getWorldWidth() / 2f, stage.getViewport().getWorldHeight() / 2f, 0);
//        stage.getViewport().getCamera().update();
//    }
//
//    @Override
//    public void pause() {}
//
//    @Override
//    public void resume() {}
//
//    @Override
//    public void hide() {}
//
//    public World getWorld() {
//        return world;
//    }
//}

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
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ninjamoney.angrybirds.AngryBirds;
import com.ninjamoney.angrybirds.elements.character.bird.Birds;
import com.ninjamoney.angrybirds.elements.character.bird.Bomb;
import com.ninjamoney.angrybirds.elements.character.bird.Chuck;
import com.ninjamoney.angrybirds.elements.character.bird.Red;
import com.ninjamoney.angrybirds.elements.character.pig.LargePig;
import com.ninjamoney.angrybirds.elements.character.pig.MediumPig;
import com.ninjamoney.angrybirds.elements.character.pig.Pigs;
import com.ninjamoney.angrybirds.elements.character.pig.SmallPig;
import com.ninjamoney.angrybirds.elements.struct.Catapult;
import com.ninjamoney.angrybirds.elements.struct.Wood;

public class Level1 implements Screen {
    private AngryBirds game;
    private SpriteBatch batch;
    private final int levelNumber = 1;
    private final boolean isLocked = false;
    private Texture background;
    private Red red;
    private Chuck chuck;
    private Bomb bomb;

    private Queue<Birds> birdQueue;
    public Stage stage;
    private Body gnd;
    public Texture redTexture;
    private Texture ground;
    private Texture slingshot;
    private Array<Body> boxes;
    private Texture boxTexture;
    private Catapult cp;

    private Pigs smallpig;
    private Pigs mediumPig;
    private Pigs largePig;

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

        world = new World(new Vector2(0, -9.8f), true);
        debugRenderer = new Box2DDebugRenderer();

        float slingshotX = stage.getViewport().getWorldWidth() / 8f;
        float slingshotY = stage.getViewport().getWorldHeight() / 5f;
        float slingshotHeight = 400 / 4f;
        cp = new Catapult(slingshotX, slingshotY);

        red = new Red();
        red.setBirdBody(createCircle(120, 100, 20f, false));

        chuck = new Chuck();
        chuck.setBirdBody(createCircle(90, 90, 20f, false));

        bomb = new Bomb();
        bomb.setBirdBody(createCircle(50, 50, 30f, false));

        smallpig = new SmallPig();
        smallpig.setPigBody(createCircle(50, 50, 20f, false));

        mediumPig = new MediumPig();
        mediumPig.setPigBody(createCircle(50, 50, 20f, false));

        largePig = new LargePig();
        largePig.setPigBody(createCircle(50, 50, 20f, false));

        birdQueue = new Queue<Birds>();
        birdQueue.addLast(red);
        birdQueue.addLast(chuck);
        birdQueue.addLast(bomb);

        gnd = createGround();
        setNextBirdOnSlingshot();

        boxes = new Array<Body>();
        createStructure();
    }

    private void setNextBirdOnSlingshot() {
        if (birdQueue.size > 0) {
            Birds nextBird = birdQueue.removeFirst();
            float slingshotX = stage.getViewport().getWorldWidth() / 8f;
            float slingshotY = stage.getViewport().getWorldHeight() / 5f;
            float slingshotHeight = 400 / 4f;

            float birdX = slingshotX + 20;
            float birdY = slingshotY + slingshotHeight - 10;

            nextBird.getBirdBody().setTransform(birdX, birdY, 0);
            cp.setCurrentBird(nextBird);
        }
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
        body.setGravityScale(1);
        body.setAngularDamping(1f);
        circle.dispose();

        return body;
    }


    private void createStructure() {
        // Create the base boxes
        boxes.add(new Wood(world, "plank", 4 * stage.getWidth() / 5, stage.getHeight() / 5, 20, 140).getBody());
        boxes.add(new Wood(world, "plank", 4 * stage.getWidth() / 5 - 100, stage.getHeight() / 5, 20, 140).getBody());
        boxes.add(new Wood(world, "plank", 4 * stage.getWidth() / 5 - 200, stage.getHeight() / 5, 20, 140).getBody());

        // Create the fourth box rotated by 90 degrees and place it on top of the base boxes
        Wood fourthBox = new Wood(world, "plank", 4 * stage.getWidth() / 5 - 150, stage.getHeight() / 5 + 140, 20, 120);
        fourthBox.getBody().setTransform(fourthBox.getBody().getPosition(), (float) Math.toRadians(90));
        boxes.add(fourthBox.getBody());

        // Create additional boxes to stabilize the structure
        Wood fifthBox = new Wood(world, "plank", 4 * stage.getWidth() / 5 - 50, stage.getHeight() / 5 + 140, 20, 110);
        fifthBox.getBody().setTransform(fifthBox.getBody().getPosition(), (float) Math.toRadians(90));
        boxes.add(fifthBox.getBody());

        // Add a top box to complete the structure
        boxes.add(new Wood(world, "plank", 4 * stage.getWidth() / 5 - 150, stage.getHeight() / 5 + 260, 20, 140).getBody());
        boxes.add(new Wood(world, "plank", 4 * stage.getWidth() / 5 - 50, stage.getHeight() / 5 + 260, 20, 140).getBody());

        Wood sixthBox = new Wood(world, "plank", 4 * stage.getWidth() / 5 - 110, stage.getHeight() / 5 + 400, 20, 120);
        sixthBox.getBody().setTransform(sixthBox.getBody().getPosition(), (float) Math.toRadians(90));
        boxes.add(sixthBox.getBody());

        // Place pigs on the structure
        smallpig.getPigBody().setTransform(4 * stage.getWidth() / 5 - 150, stage.getHeight() / 5 + 300, 0);
        mediumPig.getPigBody().setTransform(4 * stage.getWidth() / 5 - 50, stage.getHeight() / 5 + 300, 0);
        largePig.getPigBody().setTransform(4 * stage.getWidth() / 5 - 100, stage.getHeight() / 5 + 420, 0);
    }


    private Body createBox(float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

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
    public void show() {
        Gdx.input.setInputProcessor(stage);
        ground = new Texture("game/bg/ground.png");
        slingshot = cp.getCatapultTexture();
        boxTexture = new Texture("elements/struct/plank.png");
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
            float angle = box.getAngle();
            PolygonShape shape = (PolygonShape) box.getFixtureList().get(0).getShape();
            Vector2 size = new Vector2();
            shape.getVertex(0, size);
            size.scl(2); // Box2D uses half-widths, so multiply by 2
            TextureRegion boxTR = new TextureRegion(boxTexture);
            batch.draw(boxTR, pos.x - size.x / 2, pos.y - size.y / 2, size.x / 2, size.y / 2, size.x, size.y, 1, 1, (float) Math.toDegrees(angle));
        }

        // Draw the pigs with angle
        TextureRegion smallPigTexture = new TextureRegion(smallpig.getPigTexture());
        batch.draw(smallPigTexture,
            smallpig.getPigBody().getPosition().x - 20, smallpig.getPigBody().getPosition().y - 20,
            20, 20, 40, 40, 1, 1,
            (float) Math.toDegrees(smallpig.getPigBody().getAngle())); // Apply rotation in degrees

        TextureRegion mediumPigTexture = new TextureRegion(mediumPig.getPigTexture());
        batch.draw(mediumPigTexture,
            mediumPig.getPigBody().getPosition().x - 20, mediumPig.getPigBody().getPosition().y - 20,
            20, 20, 40, 40, 1, 1,
            (float) Math.toDegrees(mediumPig.getPigBody().getAngle())); // Apply rotation in degrees

        TextureRegion largePigTexture = new TextureRegion(largePig.getPigTexture());
        batch.draw(largePigTexture,
            largePig.getPigBody().getPosition().x - 30, largePig.getPigBody().getPosition().y - 25,
            30, 30, 60, 60, 1, 1,
            (float) Math.toDegrees(largePig.getPigBody().getAngle())); // Apply rotation in degrees

        // Draw the birds with angle
        TextureRegion redTexture = new TextureRegion(red.getBirdTexture());
        batch.draw(redTexture,
            red.getRedBody().getPosition().x - 20, red.getRedBody().getPosition().y - 20,
            20, 20, 40, 40, 1, 1,
            (float) Math.toDegrees(red.getRedBody().getAngle())); // Apply rotation in degrees

        TextureRegion chuckTexture = new TextureRegion(chuck.getBirdTexture());
        batch.draw(chuckTexture,
            chuck.getChuckBody().getPosition().x - 20f, chuck.getChuckBody().getPosition().y - 20f,
            20, 20, 40, 40, 1, 1,
            (float) Math.toDegrees(chuck.getChuckBody().getAngle())); // Apply rotation in degrees

        TextureRegion bombTexture = new TextureRegion(bomb.getBirdTexture());
        batch.draw(bombTexture,
            bomb.getBombBody().getPosition().x - 30f, bomb.getBombBody().getPosition().y - 25f,
            30, 30, 60, 60, 1, 1,
            (float) Math.toDegrees(bomb.getBombBody().getAngle())); // Apply rotation in degrees

        // Render the trajectory
        cp.trajectoryPredictor.render(batch);

        batch.end();

        // Update the physics world
        world.step(1 / 60f, 6, 2);

        // Handle input for pulling and releasing the bird
        handleInput();

        // Uncomment to visualize Box2D debug shapes
        debugRenderer.render(world, stage.getViewport().getCamera().combined);
    }

    public void handleInput() {
        if (Gdx.input.isTouched()) {
            if (cp.getCurrentBird() != null) {
                cp.pull(cp.getCurrentBird());
                cp.updatePull();

                Vector2 dragPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                stage.getViewport().unproject(dragPos);

                Vector2 slingshotPos = new Vector2(
                    stage.getViewport().getWorldWidth() / 8f + (144 / 3f) * 0.75f,
                    stage.getViewport().getWorldHeight() / 5f + (400 / 4f) * 0.7f
                );

                cp.trajectoryPredictor.updateTrajectory(slingshotPos, dragPos);
            }
        } else {
            if (cp.isPulling()) {
                cp.release();
                setNextBirdOnSlingshot();
            }
        }
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
        cp.trajectoryPredictor.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        if (gnd != null) {
            world.destroyBody(gnd);
        }
        gnd = createGround();
        batch.begin();
        batch.draw(background, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        batch.end();
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
