package com.ninjamoney.angrybirds.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ninjamoney.angrybirds.AngryBirds;
import com.ninjamoney.angrybirds.elements.character.bird.Bomb;
import com.ninjamoney.angrybirds.elements.character.bird.Chuck;
import com.ninjamoney.angrybirds.elements.character.bird.Red;
import com.ninjamoney.angrybirds.elements.character.pig.LargePig;
import com.ninjamoney.angrybirds.elements.character.pig.MediumPig;
import com.ninjamoney.angrybirds.elements.character.pig.SmallPig;
import com.ninjamoney.angrybirds.elements.struct.Catapult;
import com.ninjamoney.angrybirds.elements.struct.Rock;
import com.ninjamoney.angrybirds.elements.struct.Wood;
import com.ninjamoney.angrybirds.screens.LevelSelectorScreen;
import com.ninjamoney.angrybirds.screens.Levels;
import com.ninjamoney.angrybirds.screens.LoseScreen;
import com.ninjamoney.angrybirds.screens.VictoryScreen;

public class Level2 implements Screen {
        private AngryBirds game;
        private int levelNumber;
        private boolean isLocked;
        private Levels level;
        private SpriteBatch batch;
        private Texture background;
        private Texture backButton;
        private Texture pauseButton;
        private Texture pauseOverlay;
        private Texture pauseheadings;
        private Texture resumeButton;
        private Texture dummyButtonTexture;
        private Screen prev;
        private Stage stage;
        private Catapult catapult;
        private Rock circleRock1, circleRock2, circleRock3, rockBlock1, rockBlock2;
        private Wood plank1, plank2, plank3, plank4, plank5, woodTriangle1, woodTriangle2;
        private SmallPig smallPig;
        private MediumPig mediumPig;
        private LargePig largePig;
        private Red red;
        private Bomb bomb;
        private Chuck chuck;
        private boolean isPaused = false;
        private Skin skin;
        private Button dummyButton1;
        private Button dummyButton2;
        Viewport viewport;

        public Level2(AngryBirds game, int levelNumber, boolean isLocked) {
            this.game = game;
            this.levelNumber = levelNumber;
            this.isLocked = isLocked;
            this.level = level;

            batch = new SpriteBatch();
            background = new Texture("game/bg/background.jpg");
            backButton = new Texture("buttons/back.png");
            pauseButton = new Texture("buttons/pause.png");
            pauseOverlay = new Texture("game/pauseboard.png");
            pauseheadings = new Texture("game/pause_heading.png");
            resumeButton = new Texture("buttons/Play1.png");
            prev = new LevelSelectorScreen(game);
            catapult = new Catapult(209, 103);

            // Initialize stage and add input processor for stage
            stage = new Stage(new ScreenViewport());
            Gdx.input.setInputProcessor(stage);

            skin = new Skin();
            BitmapFont font = new BitmapFont();
            skin.add("default", font);

            // Dummy button setup
            TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
            textButtonStyle.font = skin.getFont("default");

            dummyButton1 = new TextButton("Win Screen", textButtonStyle);
            dummyButton1.setPosition(0, 0);
            dummyButton1.setSize(100, 50);
            dummyButton1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new VictoryScreen(game));
                }
            });

            dummyButton2 = new TextButton("Lose Screen", textButtonStyle);
            dummyButton2.setPosition(150, 0);
            dummyButton2.setSize(100, 50);
            dummyButton2.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new LoseScreen(game));
                }
            });

            stage.addActor(dummyButton1);
            stage.addActor(dummyButton2);

            // Initialize other game objects
            circleRock1 = new Rock("circle");
            circleRock2 = new Rock("circle");
            circleRock3 = new Rock("circle");
            rockBlock1 = new Rock("rockBlock");
            rockBlock2 = new Rock("rockBlock");
//            plank1 = new Wood("plank");
//            plank2 = new Wood("plank");
//            plank3 = new Wood("plank");
//            plank4 = new Wood("plank");
//            plank5 = new Wood("plank");
//            woodTriangle1 = new Wood("woodTriangle");
//            woodTriangle2 = new Wood("woodTriangle");
            smallPig = new SmallPig();
            mediumPig = new MediumPig();
            largePig = new LargePig();
            red = new Red();
            bomb = new Bomb();
            chuck = new Chuck();
        }

        @Override
        public void show() {}

        public void handleInput() {
            float x = Gdx.input.getX();
            float y = Gdx.graphics.getHeight() - Gdx.input.getY();
            if (x > 1177 && y > 621) {
                if (Gdx.input.isTouched()) {
                    game.setScreen(prev);
                }
            }

            if (x > 0 && x < 100 && y > 620 && y < 720) {
                if (Gdx.input.justTouched()) {
                    isPaused = !isPaused;
                }
            }

            if (isPaused && x > 540 && x < 740 && y > 310 && y < 410) {
                if (Gdx.input.justTouched()) {
                    isPaused = false;
                }
            }
        }

        @Override
        public void render(float delta) {
            batch.begin();
            batch.draw(background, 0, 0, 1280f, 720f);
            batch.draw(backButton, 1177, 621, 103, 93);
            batch.draw(pauseButton, 0, 620, 100, 100);

            // Draw structure
            batch.draw(circleRock1.getRockTexture(), 899, 99, 57, 50);
            batch.draw(circleRock2.getRockTexture(), 966, 99, 57, 50);
            batch.draw(circleRock3.getRockTexture(), 1033, 99, 57, 50);
            batch.draw(plank1.getWoodTexture(), 888, 149, 211, 20);
            batch.draw(plank2.getWoodTexture(), 888, 356, 211, 20);
            batch.draw(plank3.getWoodTexture(), 888, 488, 211, 20);
            batch.draw(plank4.getWoodTexture(), 888, 149, 20, 211);
            batch.draw(plank5.getWoodTexture(), 1078, 149, 20, 211);
            batch.draw(rockBlock1.getRockTexture(), 899, 377, 40, 39);
            batch.draw(rockBlock2.getRockTexture(), 1048, 377, 40, 39);
            batch.draw(woodTriangle1.getRegion(), 1048, 416, 69, 71);
            batch.draw(woodTriangle2.getWoodTexture(), 870, 416, 69, 71);

            // Draw pigs
            batch.draw(smallPig.getPigTexture(), 967, 508, 52, 47);
            batch.draw(mediumPig.getPigTexture(), 945, 376, 98, 95);
            batch.draw(largePig.getPigTexture(), 926, 149, 135, 155);

            if (isPaused) {
                batch.draw(pauseOverlay, 0, 0, 1280, 720);
                batch.draw(pauseheadings, 540, 410, 200, 100);
                batch.draw(resumeButton, 540, 310, 200, 100);
            } else {
                batch.draw(catapult.getCatapultTexture(), 209, 103, 72, 203);
                batch.draw(red.getRedTexture(), 10, 99, 110, 142);
                batch.draw(bomb.getBombTexture(), 112, 99, 97, 161);
                batch.draw(chuck.getChuckTexture(), 202, 251, 71, 55);
            }
            batch.end();

            // Update and draw the stage (for buttons)
            stage.act(delta);
            stage.draw();

            handleInput();
        }

        @Override
        public void resize(int width, int height) {
            stage.getViewport().update(width, height, true);
        }

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {}

        @Override
        public void dispose() {
            batch.dispose();
            background.dispose();
            backButton.dispose();
            pauseButton.dispose();
            pauseOverlay.dispose();
            pauseheadings.dispose();
            resumeButton.dispose();
            dummyButtonTexture.dispose();
            stage.dispose();
            skin.dispose();
        }
    }
