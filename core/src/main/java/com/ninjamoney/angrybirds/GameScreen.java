package com.ninjamoney.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

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
    private Screen prev;
    private Catapult catapult;

    private boolean isPaused = false;

    public GameScreen(AngryBirds game, int levelNumber, boolean isLocked, Levels level) {
        this.game = game;
        this.levelNumber = levelNumber;
        this.isLocked = isLocked;
        this.level = level;
        batch = new SpriteBatch();
        background = new Texture("background.jpg");
        backButton = new Texture("back.png");

        // REPLACE PAUSE.PNG
        pauseButton = new Texture("pause.png");
        pauseOverlay = new Texture("pauseboard.png");
        pauseheadings = new Texture("pause_heading.png");
        resumeButton = new Texture("Play1.png");
        prev = new LevelSelectorScreen(game);
        catapult = new Catapult(209, 103);
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

        if (isPaused) {
            batch.draw(pauseOverlay, 0, 0, 1280, 720);
            batch.draw(pauseheadings, 540, 410, 200, 100);
            batch.draw(resumeButton, 540, 310, 200, 100);
        } else {
            batch.draw(catapult.getCatapultTexture(), 209, 103, 72, 203);
            batch.draw(new Red().getRedTexture(), 10, 99, 110, 142);
            batch.draw(new Bomb().getBombTexture(), 112, 99, 97, 161);
            batch.draw(new Chuck().getChuckTexture(), 202, 251, 71, 55);
        }

        //drawing structure
        batch.draw(new Rock("circle").getRockTexture(), 899, 99, 57, 50);
        batch.draw(new Rock("circle").getRockTexture(), 966, 99, 57, 50);
        batch.draw(new Rock("circle").getRockTexture(), 1033, 99, 57, 50);

        batch.draw(new Wood("plank").getWoodTexture(), 888, 149, 211, 20);
        batch.draw(new Wood("plank").getWoodTexture(), 888, 356, 211, 20);
        batch.draw(new Wood("plank").getWoodTexture(), 888, 488, 211, 20);
        batch.draw(new Wood("plank").getWoodTexture(), 888, 149, 20, 211);
        batch.draw(new Wood("plank").getWoodTexture(), 1078, 149, 20, 211);

        batch.draw(new Rock("rockBlock").getRockTexture(), 899, 377, 40, 39);
        batch.draw(new Rock("rockBlock").getRockTexture(), 1048, 377, 40, 39);

        batch.draw(new Wood("woodTriangle").getRegion(), 1048, 416, 69, 71);
        batch.draw(new Wood("woodTriangle").getWoodTexture(), 870, 416, 69, 71);

        batch.end();
        handleInput();
    }

    @Override
    public void resize(int width, int height) {}

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
    }
}
