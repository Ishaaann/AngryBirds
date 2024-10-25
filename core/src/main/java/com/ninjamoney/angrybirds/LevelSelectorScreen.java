package com.ninjamoney.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelSelectorScreen implements Screen {
    private SpriteBatch batch;
    private AngryBirds game;
    private Texture levelSelectorBackground;
    private Texture levelSelectorTexture;
    private Texture l1Texture;
    private Texture l2Texture;
    private Texture l3Texture;
    private Levels level1;
    private Levels level2;
    private Levels level3;
    private Texture backButton;

    public LevelSelectorScreen(AngryBirds game) {
        this.game = game;
        batch = new SpriteBatch();
        levelSelectorTexture = new Texture("lst.png");
        levelSelectorBackground = new Texture("lsmmenu.jpg");
        l1Texture = new Texture("l1.png");
        l2Texture = new Texture("l2.png");
        l3Texture = new Texture("l3.png");
        backButton = new Texture("back.png");
        level1 = new Levels(1, false, l1Texture);
        level2 = new Levels(2, true, l2Texture);
        level3 = new Levels(3, true, l3Texture);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(levelSelectorBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(levelSelectorTexture, 0, 0, 1280f, 266f);
        batch.draw(backButton, 0, 621, 103, 93);
        batch.draw(level1.getLevelTexture(), 177, 261, 238, 242);
        batch.draw(level2.getLevelTexture(), 520, 261, 238, 242);
        batch.draw(level3.getLevelTexture(), 867, 261, 238, 242);
        batch.end();

        handleInput();
    }

    public void handleInput(){
        float x = Gdx.input.getX();
        float y = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert Y coordinate

        if(x > 177 && x < 415 && y > 261 && y < 503){
            if(Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game,1, false, level1));
            }
        }

        if(x>0 && x<103 && y>621){
            if(Gdx.input.isTouched()){
                game.setScreen(new MainMenuScreen(game));
            }
        }

        // if(x > 520 && x < 758 && y > 261 && y < 503){
//            if(Gdx.input.isTouched()) {
//                game.setScreen(new Level2(game));
//            }
//        }
//        if(x > 867 && x < 1105 && y > 261 && y < 503){
//            if(Gdx.input.isTouched()) {
//                game.setScreen(new Level3(game));
//            }
//        }
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
        levelSelectorBackground.dispose();
        levelSelectorTexture.dispose();
        l1Texture.dispose();
        l2Texture.dispose();
        l3Texture.dispose();
        backButton.dispose();
    }
}
