package com.ninjamoney.angrybirds.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ninjamoney.angrybirds.AngryBirds;
import com.ninjamoney.angrybirds.GameState;
import com.ninjamoney.angrybirds.elements.character.bird.Birds;
import com.ninjamoney.angrybirds.elements.character.bird.Bomb;
import com.ninjamoney.angrybirds.elements.character.bird.Chuck;
import com.ninjamoney.angrybirds.elements.character.bird.Red;
import com.ninjamoney.angrybirds.elements.character.pig.LargePig;
import com.ninjamoney.angrybirds.elements.character.pig.MediumPig;
import com.ninjamoney.angrybirds.elements.character.pig.Pigs;
import com.ninjamoney.angrybirds.elements.character.pig.SmallPig;
import com.ninjamoney.angrybirds.elements.struct.Catapult;
import com.ninjamoney.angrybirds.elements.struct.SolidObjects;
import com.ninjamoney.angrybirds.elements.struct.Wood;
import com.ninjamoney.angrybirds.phy.Collisions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ninjamoney.angrybirds.phy.GameStateListener;
import com.ninjamoney.angrybirds.phy.PigHealthListener;
import com.ninjamoney.angrybirds.screens.LevelSelectorScreen;
import com.ninjamoney.angrybirds.screens.LoseScreen;
import com.ninjamoney.angrybirds.screens.VictoryScreen;

import java.util.ArrayList;
import java.util.Iterator;

import static com.ninjamoney.angrybirds.phy.Collisions.*;

public class Level1 implements Screen, PigHealthListener {
    private AngryBirds game;
    private SpriteBatch batch;
    private int levelNumber = 1;
    private final boolean isLocked = false;
    private Texture background;
    private static Red red;
    private Chuck chuck;
    private Bomb bomb;
    public static boolean cleared = false;
    public Collisions collisionListener;
    public GameStateListener gameSave;
    public Texture bombTexture;
//    private GameState gs = GameStateListener.loadLevelWiseGameData(1);



    private Texture pauseButton;
    private boolean isPaused = false;
    private Texture pauseOverlay;
    private Texture pauseHeadings;

    private Timer.Task levelTransitionTask = null;

    private Texture MusicOnHoverTexture;
    private Texture MusicOffHoverTexture;
    private float countdown = 10f;       // 10 seconds countdown
    private boolean countdownStarted = false;

    private Texture musicOnButtonTexture;
    private Texture musicOffButtonTexture;
    private Texture backButtonTexture;
    private ImageButton musicButton;
    private ImageButton backButton;
    private ImageButton dynamicResumeButton;
    private Texture resumeButton;
    private Texture resumeButtonHoverTexture;
    private boolean isMusicOn = true; // Assuming music is on by default

    private ArrayList<Birds> birdQueue;
    public static Stage stage;
    private Body gnd;
    public Texture redTexture;
    public Texture chuckTexture;
    private Texture ground;
    private Texture slingshot;
    private ArrayList<SolidObjects> boxes;
    private Texture boxTexture;
    private Texture smallPigTexture;
    private Texture mediumPigTexture;
    private Texture largePigTexture;
    private Catapult cp;
//    public ArrayList<SolidObjects> solidObjectsDestroyed;
//    public static ArrayList<Pigs> pigsDestroyed;

    private Pigs smallpig;
    private Pigs mediumPig;
    private Pigs largePig;
    private ArrayList<Pigs> pigsArray;

    private static transient World world;
    private Box2DDebugRenderer debugRenderer;

    public Level1(AngryBirds game, int levelNumber, boolean isLocked, GameState gs) {
        this.game = game;
        world = new World(new Vector2(0, -9.8f), true);
        collisionListener = new Collisions(this);
        world.setContactListener(collisionListener);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        background = new Texture("game/bg/background.jpg");
        bombTexture = new Texture("elements/char/bomb.png");
        redTexture = new Texture("elements/char/red.png");
        chuckTexture = new Texture("elements/char/chuck.png");
        smallPigTexture = new Texture("elements/char/smallpig.png");
        mediumPigTexture = new Texture("elements/char/mediumpig.png");
        largePigTexture = new Texture("elements/char/largepig.png");


        float slingshotX = stage.getViewport().getWorldWidth() / 8f;
        float slingshotY = stage.getViewport().getWorldHeight() / 5f;
        float slingshotHeight = 400 / 4f;

        if(gs != null) {
            this.birdQueue = gs.birdies;
            this.boxes = gs.solidObjects;
            this.levelNumber = gs.levelNum;
            this.pigsArray = gs.piggas;
            initializeStoredLevel(gs);
        }
        else{
            pigsArray = new ArrayList<>();
            birdQueue = new ArrayList<>();
            boxes = new ArrayList<>();

            red = new Red();
            red.setBirdBody(createCircle(slingshotX, slingshotY+80, 20f, false)); // Initialize the Red bird body on the ground
            red.birdBody.setGravityScale(0);
            red.getRedBody().setUserData(red);

            chuck = new Chuck();
            chuck.setBirdBody(createCircle(90, 20, 20f, false)); // Initialize Chuck on the ground
            chuck.getChuckBody().setUserData(chuck);

            bomb = new Bomb();
            bomb.setBirdBody(createCircle(50, 20, 30f, false)); // Initialize Bomb bird on the ground
            bomb.getBombBody().setUserData(bomb);
//            bodiesToDestroy = new ArrayList<>();

            smallpig = new SmallPig(createCirclePiggas(50, 50, 20f, false));
//        smallpig.setPigBody(createCirclePiggas(50, 50, 20f, false));
            smallpig.getPigBody().setUserData(smallpig);
            pigsArray.add(smallpig);

            mediumPig = new MediumPig(createCirclePiggas(50, 50, 20f, false));
//        mediumPig.setPigBody(createCirclePiggas(50, 50, 20f, false));
            mediumPig.getPigBody().setUserData(mediumPig);
            pigsArray.add(mediumPig);

            largePig = new LargePig(createCirclePiggas(50, 50, 30f, false));
//        largePig.setPigBody(createCirclePiggas(50, 50, 20f, false));
            largePig.getPigBody().setUserData(largePig);
            pigsArray.add(largePig);

            birdQueue.addLast(red);
            birdQueue.addLast(chuck);
            birdQueue.addLast(bomb);

            createStructure();
        }

        gameSave = new GameStateListener();
        debugRenderer = new Box2DDebugRenderer();
        cp = new Catapult(slingshotX, slingshotY+80);
        gnd = createGround();
        setNextBirdOnSlingshot();
    }

    private void initializeStoredLevel(GameState gs) {
        this.pigsArray = gs.piggas;
        ArrayList<Birds> birdsArray = gs.birdies;
        this.birdQueue = gs.birdies;
        this.boxes = gs.solidObjects;
        this.levelNumber = gs.levelNum;
        collisionListener.piggaDestroyed = gs.piggasDestroyed;
        collisionListener.solidObjectsDestroyed = gs.solidObjectsDestroyed;
        ArrayList<Vector2> pigpos = gs.pigPositions;

        // Load textures for pigs
        Texture smallPigTexture = new Texture("elements/char/smallpig.png");
        Texture mediumPigTexture = new Texture("elements/char/mediumpig.png");
        Texture largePigTexture = new Texture("elements/char/largepig.png");
        System.out.println("Texture rendered.");

        for (int i = 0; i < pigsArray.size(); i++) {
            if (i < pigpos.size() && !gs.piggasDestroyed.contains(pigsArray.get(i))) {
                pigsArray.get(i).setPigBody(createCirclePiggas(pigpos.get(i).x, pigpos.get(i).y, 20f, false));
                pigsArray.get(i).getPigBody().setUserData(pigsArray.get(i));
                pigsArray.get(i).setHealth(gs.pigHealth.get(i));

                // Set the texture based on the pig type
                if (pigsArray.get(i) instanceof SmallPig) {
                    System.out.println("Small Pig texture set.");
                    ((SmallPig)pigsArray.get(i)).setTexture(smallPigTexture);
                } else if (pigsArray.get(i) instanceof MediumPig) {
                    System.out.println("Medium Pig texture set.");
                    ((MediumPig)pigsArray.get(i)).setTexture(mediumPigTexture);
                } else if (pigsArray.get(i) instanceof LargePig) {
                    System.out.println("Large Pig texture set.");
                    ((LargePig)pigsArray.get(i)).setTexture(largePigTexture);
//                    Texture largePigTexture = new Texture("elements/char/largepig.png");
                    TextureRegion largePigTR = new TextureRegion(largePigTexture);

                }
            }
        }

        for (Birds birds : birdsArray) {
            if (birds != null) {
                if (birds instanceof Red) {
                    birds.setBirdBody(createCircle(50, 50, 20f, false));
                    birds.getBirdBody().setUserData(birds);
                    red = (Red) birds;
                } else if (birds instanceof Chuck) {
                    birds.setBirdBody(createCircle(50, 50, 20f, false));
                    birds.getBirdBody().setUserData(birds);
                    chuck = (Chuck) birds;
                } else if (birds instanceof Bomb) {
                    birds.setBirdBody(createCircle(50, 50, 30f, false));
                    birds.getBirdBody().setUserData(birds);
                    bomb = (Bomb) birds;
                }
            }
        }

        for (int i = 0; i < birdsArray.size(); i++) {
            if (birdsArray.get(i) != null && birdsArray.get(i).getBirdBody() != null) {
                if (i < gs.birdPositions.size()) {
                    birdsArray.get(i).getBirdBody().setTransform(gs.birdPositions.get(i), 0);
                }
                if (i < gs.birdVelocity.size()) {
                    birdsArray.get(i).getBirdBody().setLinearVelocity(gs.birdVelocity.get(i));
                }
            }
        }

        for (int i = 0; i < boxes.size(); i++) {
            if (boxes.get(i) instanceof Wood && !gs.solidObjectsDestroyed.contains(boxes.get(i))) {
                Wood wood = (Wood) boxes.get(i);
                if (!wood.isVertical) {
                    if(i<gs.solidObjectPositions.size()) {
                        wood = new Wood(world, "plank", 0, 0, 20, 120);
                        wood.getBody().setTransform(gs.solidObjectPositions.get(i), (float) Math.toRadians(90));
                    }
                } else {
                    if(i<gs.solidObjectPositions.size()) {
                        wood = new Wood(world, "plank", gs.solidObjectPositions.get(i).x, gs.solidObjectPositions.get(i).y, 20, 140);
                    }
                }
                boxes.set(i, wood);
            }
        }
    }

    public Body createCirclePiggas(float x, float y, float radius, boolean isStatic) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.0001f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;

        body.createFixture(fixtureDef);
        body.setGravityScale(1);
        body.setAngularDamping(1f);
        circle.dispose();

        return body;
    }

    private void setNextBirdOnSlingshot() {
        if (birdQueue.size() > 0) {
            birdJumpToCatapult(cp.getCurrentBird());
            Birds nextBird = birdQueue.removeFirst();
            float slingshotX = stage.getViewport().getWorldWidth() / 8f;
            float slingshotY = stage.getViewport().getWorldHeight() / 5f;
            float slingshotHeight = 400 / 4f;

            float birdX = slingshotX + 20;
            float birdY = slingshotY + slingshotHeight - 10;
            if(nextBird instanceof Red){
                nextBird.getBirdBody().setTransform(birdX, birdY, 0);
                cp.setCurrentBird(nextBird);
            } else if(nextBird instanceof Chuck){
                nextBird.getBirdBody().setTransform(birdX, birdY, 0);
                cp.setCurrentBird(nextBird);
            } else if(nextBird instanceof Bomb){
                nextBird.getBirdBody().setTransform(birdX, birdY, 0);
                cp.setCurrentBird(nextBird);
        }
    }}

    private Body createGround() {
        BodyDef ground = new BodyDef();
        ground.type = BodyDef.BodyType.StaticBody;
        ground.position.set(0, 0);

        Body groundBody = world.createBody(ground);
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(stage.getWidth(), stage.getHeight() / 5);

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
        fixtureDef.density =  0.001f;
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
        boxes.add(new Wood(world, "plank", 4 * stage.getWidth() / 5, stage.getHeight() / 5, 20, 140));
        boxes.add(new Wood(world, "plank", 4 * stage.getWidth() / 5 - 100, stage.getHeight() / 5, 20, 140));
        boxes.add(new Wood(world, "plank", 4 * stage.getWidth() / 5 - 200, stage.getHeight() / 5, 20, 140));

        // Create the fourth box rotated by 90 degrees and place it on top of the base boxes
        Wood fourthBox = new Wood(world, "plank", 4 * stage.getWidth() / 5 - 150, stage.getHeight() / 5 + 140, 20, 120);
        fourthBox.getBody().setTransform(fourthBox.getBody().getPosition(), (float) Math.toRadians(90));
        fourthBox.isVertical = false;
        boxes.add(fourthBox);

        // Create additional boxes to stabilize the structure
        Wood fifthBox = new Wood(world, "plank", 4 * stage.getWidth() / 5 - 50, stage.getHeight() / 5 + 140, 20, 110);
        fifthBox.getBody().setTransform(fifthBox.getBody().getPosition(), (float) Math.toRadians(90));
        fifthBox.isVertical = false;
        boxes.add(fifthBox);

        // Add a top box to complete the structure
        boxes.add(new Wood(world, "plank", 4 * stage.getWidth() / 5 - 150, stage.getHeight() / 5 + 260, 20, 140));
        boxes.add(new Wood(world, "plank", 4 * stage.getWidth() / 5 - 50, stage.getHeight() / 5 + 260, 20, 140));

        Wood sixthBox = new Wood(world, "plank", 4 * stage.getWidth() / 5 - 110, stage.getHeight() / 5 + 400, 20, 120);
        sixthBox.getBody().setTransform(sixthBox.getBody().getPosition(), (float) Math.toRadians(90));
        sixthBox.isVertical = false;
        boxes.add(sixthBox);

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
        body.setGravityScale(0.8f);
        shape.dispose();

        return body;
    }


    @Override
    public void show() {
        // Set the input processor to handle UI events
        Gdx.input.setInputProcessor(stage);

        // Load textures
        ground = new Texture("game/bg/ground.png");
        slingshot = cp.getCatapultTexture();
        boxTexture = new Texture("elements/struct/plank.png");
        pauseButton = new Texture("buttons/pause.png");
        pauseOverlay = new Texture("game/pauseboard.png");
        pauseHeadings = new Texture("game/pause_heading.png");
        resumeButton = new Texture("buttons/Play1.png");
        resumeButtonHoverTexture = new Texture("buttons/Play2.png");
        musicOnButtonTexture = new Texture("buttons/sound/sound.png");
        musicOffButtonTexture = new Texture("buttons/sound/soundoff.png");
        backButtonTexture = new Texture("buttons/back.png");

        // Setup resume button with hover state
        ImageButton.ImageButtonStyle resumeButtonStyle = new ImageButton.ImageButtonStyle();
        resumeButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(resumeButton));
        resumeButtonStyle.imageOver = new TextureRegionDrawable(new TextureRegion(resumeButtonHoverTexture));

        // Initialize the dynamic resume button
        dynamicResumeButton = new ImageButton(resumeButtonStyle);
        dynamicResumeButton.setSize(250, 250); // Set the size of the button
        dynamicResumeButton.setPosition(0, 0); // Initial position, will be updated in render()

        // Add listener for the resume button to resume the game
        dynamicResumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isPaused = false; // Resume the game
            }
        });

        // Add the resume button to the stage
        stage.addActor(dynamicResumeButton);

        // Create and initialize the music button
        ImageButton.ImageButtonStyle musicButtonStyle = new ImageButton.ImageButtonStyle();
        if (game.themeMusic.isPlaying()) {
            musicButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(musicOnButtonTexture));
            musicButtonStyle.imageOver = new TextureRegionDrawable(new TextureRegion(new Texture("buttons/sound/soundhover.png"))); // Hover state
        } else {
            musicButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(musicOffButtonTexture));
            musicButtonStyle.imageOver = new TextureRegionDrawable(new TextureRegion(new Texture("buttons/sound/soundoffhover.png"))); // Hover state
        }

        // Create the music button
        musicButton = new ImageButton(musicButtonStyle);
        musicButton.setSize(100, 100);
        musicButton.setPosition(stage.getViewport().getWorldWidth() / 2 - 150, stage.getViewport().getWorldHeight() / 2 - 50);
        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Toggle music state on click
                isMusicOn = !isMusicOn;

                if (isMusicOn) {
                    game.themeMusic.play();
                    musicButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(musicOnButtonTexture));
                    musicButtonStyle.imageOver = new TextureRegionDrawable(new TextureRegion(new Texture("buttons/sound/soundhover.png"))); // Update hover state
                } else {
                    game.themeMusic.pause();
                    musicButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(musicOffButtonTexture));
                    musicButtonStyle.imageOver = new TextureRegionDrawable(new TextureRegion(new Texture("buttons/sound/soundoffhover.png"))); // Update hover state
                }
            }
        });

        // Initialize back button
        backButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(backButtonTexture)));
        backButton.setSize(100, 100);
        backButton.setPosition(stage.getViewport().getWorldWidth() / 2 + 50, stage.getViewport().getWorldHeight() / 2 - 50);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Go back to the level selector screen when clicked
                ArrayList<Birds> newBirdQueue = new ArrayList<>();
                if(cp.getCurrentBird() != null) {
                    newBirdQueue.addFirst(cp.getCurrentBird());
                }
                newBirdQueue.addAll(birdQueue);
                gameSave.saveGameState(boxes, pigsArray, newBirdQueue, collisionListener.solidObjectsDestroyed, collisionListener.piggaDestroyed, bodiesToDestroy, levelNumber);
                game.setScreen(new LevelSelectorScreen(game));
                dispose();
            }
        });

        // Add the music and back buttons to the stage
        stage.addActor(musicButton);
        stage.addActor(backButton);
    }




    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();

        // Draw the background and ground
        batch.draw(background, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        batch.draw(ground, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight() / 5);
        batch.draw(slingshot, stage.getViewport().getWorldWidth() / 8, stage.getViewport().getWorldHeight() / 5, 144 / 3f, 400 / 4f);


        for (SolidObjects box : boxes) {
            if (box != null && box instanceof Wood) {
                Wood woodBox = (Wood) box;
                Body body = woodBox.getBody();
                if (body != null) {
                    Vector2 pos = body.getPosition();
                    batch.draw(boxTexture, pos.x - 1f, pos.y - 0.5f, 2f, 1f); // Box size matches dimensions
                    float angle = body.getAngle();
                    if (body.getFixtureList().size > 0) {
                        PolygonShape shape = (PolygonShape) body.getFixtureList().get(0).getShape();
                        Vector2 size = new Vector2();
                        shape.getVertex(0, size);
                        size.scl(2);
                        TextureRegion boxTR = new TextureRegion(boxTexture);
                        batch.draw(boxTR, pos.x - size.x / 2, pos.y - size.y / 2, size.x / 2, size.y / 2, size.x, size.y, 1, 1, (float) Math.toDegrees(angle));
                    }
                }
            }
        }

        for (Pigs pig : pigsArray) {
            if (pig != null && pig.getPigBody() != null && pig.getHealth() > 0) {
                Vector2 position = pig.getPigBody().getPosition();
                Texture texture = pig.getPigTexture();
                TextureRegion tr = new TextureRegion(texture);
                batch.draw(tr, pig.getPigBody().getPosition().x-25, pig.getPigBody().getPosition().y-25,
                    25, 25,50,50,1,1,pig.getPigBody().getAngle()* MathUtils.radiansToDegrees);
            }
        }

        // Draw the birds with angle
        if(red!=null && red.getRedBody()!=null) {
            TextureRegion redTR = new TextureRegion(redTexture);
            batch.draw(redTR,
                red.getRedBody().getPosition().x - 20, red.getRedBody().getPosition().y - 20,
                20, 20, 40, 40, 1, 1,
                (float) Math.toDegrees(red.getRedBody().getAngle())); // Apply rotation in degrees
        }

        if(chuck!=null && chuck.getChuckBody()!=null) {
            TextureRegion chuckTR = new TextureRegion(chuckTexture);
            batch.draw(chuckTR,
                chuck.getChuckBody().getPosition().x - 20f, chuck.getChuckBody().getPosition().y - 20f,
                20, 20, 40, 40, 1, 1,
                (float) Math.toDegrees(chuck.getChuckBody().getAngle())); // Apply rotation in degrees
        }

//        bombTexture = new Texture("elements/char/bomb.png");
        if(bomb!=null && bomb.getBombBody()!=null) {
            TextureRegion bombTR = new TextureRegion(bombTexture);
            batch.draw(bombTR,
                bomb.getBombBody().getPosition().x - 30f, bomb.getBombBody().getPosition().y - 25f,
                30, 30, 60, 60, 1, 1,
                (float) Math.toDegrees(bomb.getBombBody().getAngle())); // Apply rotation in degrees
        }

        batch.draw(pauseButton, stage.getViewport().getWorldWidth() - 100, stage.getViewport().getWorldHeight() - 100, 100, 100);

        if (isPaused) {
            float overlayWidth = stage.getViewport().getWorldWidth() / 2;
            float overlayHeight = stage.getViewport().getWorldHeight() / 2;
            float overlayX = (stage.getViewport().getWorldWidth() - overlayWidth) / 2;
            float overlayY = (stage.getViewport().getWorldHeight() - overlayHeight) / 2;

            batch.draw(pauseOverlay, overlayX, overlayY, overlayWidth, overlayHeight);
            batch.draw(pauseHeadings, overlayX + overlayWidth / 4, overlayY + overlayHeight + 20, overlayWidth / 2, overlayHeight / 4);

            // Draw the dynamic resume button at the bottom center of the pause board
            dynamicResumeButton.setPosition(overlayX + (overlayWidth - dynamicResumeButton.getWidth()) / 2, overlayY + 20);
            dynamicResumeButton.draw(batch, 1);

            // Draw the music and back buttons (positioned relative to the pause overlay)
            musicButton.setPosition(overlayX + 20, overlayY + 20);
            backButton.setPosition(overlayX + overlayWidth - backButton.getWidth() - 20, overlayY + 20);
            musicButton.draw(batch, 1);
            backButton.draw(batch, 1);
        }
//        System.out.println("Small pi health: " + smallpig.getHealth());
//        System.out.println("Medium pi health: " + mediumPig.getHealth());
//        System.out.println("Large pi health: " + largePig.getHealth());
//        System.out.println(red.getRedBody().getLinearVelocity());
        System.out.println("Pigs destroyed: " + collisionListener.piggaDestroyed.size());
        System.out.println("pig array size: " + pigsArray.size());


        // Render the trajectory
        if (cp.isShowTrajectory()) {
            cp.trajectoryPredictor.render(batch);
        }


        batch.end();


        if (!isPaused) {
            // Update the physics world
            world.step(1 / 60f, 6, 2);
            world.step(1/60f,6,2);

//            checkBounds(smallpig);
//            checkBounds(mediumPig);
//            checkBounds(largePig);
            processBodyDestructionQueue();
            handleInput();
            levelCleared();
        }

        handleInput();
        debugRenderer.render(world, stage.getViewport().getCamera().combined);
    }

    private void checkBounds(Pigs pig) {
        if(pig!=null && pig.getPigBody()!=null){
            float x = pig.getPigBody().getPosition().x;
            float y = pig.getPigBody().getPosition().y;
            float worldWidth = stage.getViewport().getWorldWidth();
            float worldHeight = stage.getViewport().getWorldHeight();
            System.out.println("x: " + x + " y: " + y);

            if (x < 0 || x > worldWidth || y < 0 || y > worldHeight) {
                System.out.println("checked");
                pig.setHealth(0);
                collisionListener.piggaDestroyed.add(pig);
                queueBodyForDestruction(pig.getPigBody());
            }
        }
//        float x = pig.getPigBody().getPosition().x;
//        float y = pig.getPigBody().getPosition().y;
//        float worldWidth = stage.getViewport().getWorldWidth();
//        float worldHeight = stage.getViewport().getWorldHeight();
//
//        if (x < 0 || x > worldWidth || y < 0 || y > worldHeight) {
////            pigsArray.remove(pig);
//            pig.setHealth(0);
////            queueBodyForDestruction(pig.getPigBody());
//        }
    }

    public void processBodyDestructionQueue() {
        Iterator<Object> iterator = bodiesToDestroy.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
                if (obj instanceof Body) {
                    if(((Body) obj).getUserData() instanceof Pigs){
                        Pigs pig = (Pigs) ((Body) obj).getUserData();
                        if(pig.getPigBody()!=null){
                            world.destroyBody(pig.getPigBody());
                            System.out.println("Pigga destroyed");
                        }
                    } else if(((Body) obj).getUserData() instanceof SolidObjects){
                        SolidObjects solidObject = (SolidObjects) ((Body) obj).getUserData();
                        if(solidObject.getBody()!=null){
                            boxes.remove(solidObject.getBody());
                            world.destroyBody(solidObject.getBody());
                            System.out.println("Solid object destroyed");
                        }
                    }
                }
                System.out.println("Destroying body: " + obj);
                iterator.remove();

        }
    }

    public void handleInput() {
        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            stage.getViewport().unproject(touchPos);

            if (isPaused) {
                // Check if resume button is clicked
                if (touchPos.x >= stage.getViewport().getWorldWidth() / 2 - 100 && touchPos.x <= stage.getViewport().getWorldWidth() / 2 + 100 &&
                    touchPos.y >= stage.getViewport().getWorldHeight() / 2 - 50 && touchPos.y <= stage.getViewport().getWorldHeight() / 2 + 50) {
                    isPaused = false;
                }
            } else {
                // Check if pause button is clicked
                if (touchPos.x >= stage.getViewport().getWorldWidth() - 100 && touchPos.x <= stage.getViewport().getWorldWidth() &&
                    touchPos.y >= stage.getViewport().getWorldHeight() - 100 && touchPos.y <= stage.getViewport().getWorldHeight()) {
                    isPaused = true;
                }

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
            }
        } else {
            if (cp.isPulling() && !isPaused) {
                cp.release();

                // Wait for 3 seconds before placing the next bird on the slingshot
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        setNextBirdOnSlingshot();
                    }
                }, 3);
            }
        }
    }

    private void birdJumpToCatapult(Birds bird){
        if(bird == null || bird.getBirdBody() == null){
            return;
        }
        Vector2 catapultPosition = new Vector2(cp.getX(), cp.getY()+80);
        Vector2 birdPosition = new Vector2(bird.getBirdBody().getPosition().x, bird.getBirdBody().getPosition().y);
        float jumpTime = 0.5f;
        int steps = 60;
        float stepTime = jumpTime/steps;

        Timer.schedule(new Timer.Task() {
            int currentStep = 0;
            @Override
            public void run(){
                if(currentStep<steps){
                    float progress = currentStep/steps;
                    float x = birdPosition.x + progress * (catapultPosition.x - birdPosition.x);
                    float y = birdPosition.y + progress * (catapultPosition.y - birdPosition.y);
                    bird.getBirdBody().setTransform(x, y, 0);
                    currentStep++;
                } else {
                    bird.getBirdBody().setTransform(catapultPosition, 0);
                    cp.setCurrentBird(bird);
                    this.cancel();
                }
            }
        }, 0, stepTime, steps);
    }


    @Override
    public void dispose() {
        // Dispose of all textures

        if (levelTransitionTask != null) {
            levelTransitionTask.cancel();
        }
        if (background != null) background.dispose();
        if (ground != null) ground.dispose();
        if (slingshot != null) slingshot.dispose();
        if (boxTexture != null) boxTexture.dispose();
        if (pauseButton != null) pauseButton.dispose();
        if (pauseOverlay != null) pauseOverlay.dispose();
        if (pauseHeadings != null) pauseHeadings.dispose();
        if (resumeButton != null) resumeButton.dispose();
        if (musicOnButtonTexture != null) musicOnButtonTexture.dispose();
        if (musicOffButtonTexture != null) musicOffButtonTexture.dispose();
        if (MusicOnHoverTexture != null) MusicOnHoverTexture.dispose();
        if (MusicOffHoverTexture != null) MusicOffHoverTexture.dispose();
        if (backButtonTexture != null) backButtonTexture.dispose();

        // Dispose of the batch
        if (batch != null) batch.dispose();

        // Dispose of the debug renderer
        if (debugRenderer != null) debugRenderer.dispose();

        // Dispose of the world
        if (world != null) world.dispose();

        // Dispose of the stage
        if (stage != null) stage.dispose();

        // Dispose of the trajectory predictor
        if (cp != null && cp.trajectoryPredictor != null) cp.trajectoryPredictor.dispose();
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

    public void levelCleared() {
//        System.out.println("Checking if level is cleared...");
//        System.out.println("Total pigs: " + pigsArray.size());
//        System.out.println("Destroyed pigs: " + collisionListener.piggaDestroyed.size());

        if (pigsArray.size() == collisionListener.piggaDestroyed.size()) {
            cleared = true;
            System.out.println("Level cleared! Transitioning to VictoryScreen.");
            game.setScreen(new VictoryScreen(game, 1));
            resetCountdown();
        } else if (birdQueue.size() == 0 && pigsArray.size() > 0 && cp.getCurrentBird() == null && !countdownStarted) {
            countdownStarted = true;
            System.out.println("Starting countdown...");
        }

        if (countdownStarted) {
            countdown -= Gdx.graphics.getDeltaTime();
            System.out.println("Countdown: " + countdown);
            if (countdown <= 0) {
                if (pigsArray.size() == collisionListener.piggaDestroyed.size()) {
                    System.out.println("Countdown finished. Transitioning to VictoryScreen.");
                    game.setScreen(new VictoryScreen(game, 1));
                } else {
                    System.out.println("Countdown finished. Transitioning to LoseScreen.");
                    game.setScreen(new LoseScreen(game, 1));
                }
                resetCountdown();
            }
        }
    }
    private void resetCountdown() {
        countdownStarted = false;
        countdown = 10f; // Reset to 10 seconds for future use
    }

//    public void levelCleared() {
//        // Cancel any existing task if the level is cleared again to avoid multiple tasks being scheduled
//        if (levelTransitionTask != null) {
//            levelTransitionTask.cancel();
//        }
//
//        // Check if all pigs are cleared (level won)
//

    //to be implemented properly
    private void destroyBirds(Birds bird){
        if(bird != null && bird.getBirdBody() != null) {
            if (bird.state.equals("launched")) {
                if (bird.getBirdBody().getLinearVelocity().len()==0) {
                    queueBodyForDestruction(bird.getBirdBody());
                }
            }
        }
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

    public static void onWoodHealthZero(Wood wood) {
        queueBodyForDestruction(wood.getBody());
    }

    @Override
    public void onPigHealthZero(Pigs pig) {
        queueBodyForDestruction(pig.getPigBody());
//        score+=pig.getHealth();
    }
}
