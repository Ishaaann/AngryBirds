package com.ninjamoney.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ninjamoney.angrybirds.AngryBirds;

public class VictoryScreen implements Screen {
    private int score;
    private int stars;
    private Texture background;
    private SpriteBatch batch;
    private AngryBirds game;
    private Texture nextButton;
    private Texture playAgainButton;
    private Levels l1;
    private Levels l2;

    public VictoryScreen(AngryBirds game) {
        background = new Texture("game/bg/victory.png");
        batch = new SpriteBatch();
        this.game = game;
        nextButton = new Texture("buttons/next.png");
        playAgainButton = new Texture("buttons/replay.png");
    }
    @Override
    public void show() {

    }

//    private void handleInput() {
//        float x = Gdx.input.getX();
//        float y = Gdx.graphics.getHeight() - Gdx.input.getY();
//        if (Gdx.input.isTouched()) {
//            if (x >= 593 && x <= 701 && y >= 123 && y <= 229) {
//                game.setScreen(new GameScreen(game, 1, false));
//            } else if (x >= 718 && x <= 831 && y >= 123 && y <= 229) {
//                game.setScreen(new LevelSelectorScreen(game));
//            }
//        }
//    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(playAgainButton,593,123,108,106);
        batch.draw(nextButton, 718, 123, 113, 106);
        batch.end();
//        handleInput();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
