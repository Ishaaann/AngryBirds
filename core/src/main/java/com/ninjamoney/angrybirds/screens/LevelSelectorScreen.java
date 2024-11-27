//package com.ninjamoney.angrybirds.screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.ninjamoney.angrybirds.AngryBirds;
//
//public class LevelSelectorScreen implements Screen {
//    private SpriteBatch batch;
//    private AngryBirds game;
//    private Texture levelSelectorBackground;
//    private Texture levelSelectorTexture;
//    private Texture l1Texture;
//    private Texture l2Texture;
//    private Texture l3Texture;
//    private Levels level1;
//    private Levels level2;
//    private Levels level3;
//    private Texture backButton;
//
//    public LevelSelectorScreen(AngryBirds game) {
//        this.game = game;
//        batch = new SpriteBatch();
//        levelSelectorTexture = new Texture("game/bg/lst.png");
//        levelSelectorBackground = new Texture("game/bg/lsmmenu.jpg");
//        l1Texture = new Texture("buttons/levels/l1.png");
//        l2Texture = new Texture("buttons/levels/l2.png");
//        l3Texture = new Texture("buttons/levels/l3.png");
//        backButton = new Texture("buttons/back.png");
//        level1 = new Levels(1, false, l1Texture);
//        level2 = new Levels(2, true, l2Texture);
//        level3 = new Levels(3, true, l3Texture);
//    }
//
//    @Override
//    public void show() {}
//
//    @Override
//    public void render(float delta){
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        batch.begin();
//        batch.draw(levelSelectorBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        batch.draw(levelSelectorTexture, 0, 0, 1280f, 266f);
//        batch.draw(backButton, 0, 621, 103, 93);
//        batch.draw(level1.getLevelTexture(), 177, 261, 238, 242);
//        batch.draw(level2.getLevelTexture(), 520, 261, 238, 242);
//        batch.draw(level3.getLevelTexture(), 867, 261, 238, 242);
//        batch.end();
//
//        handleInput();
//    }
//
//    public void handleInput(){
//        float x = Gdx.input.getX();
//        float y = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert Y coordinate
//
//        if(x > 177 && x < 415 && y > 261 && y < 503){
//            if(Gdx.input.isTouched()) {
//                game.setScreen(new GameScreen(game,1, false));
//            }
//        }
//
//        if(x>0 && x<103 && y>621){
//            if(Gdx.input.isTouched()){
//                game.setScreen(new MainMenuScreen(game));
//            }
//        }
//
//        // if(x > 520 && x < 758 && y > 261 && y < 503){
////            if(Gdx.input.isTouched()) {
////                game.setScreen(new Level2(game));
////            }
////        }
////        if(x > 867 && x < 1105 && y > 261 && y < 503){
////            if(Gdx.input.isTouched()) {
////                game.setScreen(new Level3(game));
////            }
////        }
//    }
//
//    @Override
//    public void resize(int width, int height) {}
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
//    @Override
//    public void dispose() {
//        batch.dispose();
//        levelSelectorBackground.dispose();
//        levelSelectorTexture.dispose();
//        l1Texture.dispose();
//        l2Texture.dispose();
//        l3Texture.dispose();
//        backButton.dispose();
//    }
//}
package com.ninjamoney.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ninjamoney.angrybirds.AngryBirds;
import com.ninjamoney.angrybirds.levels.Level1;
import com.ninjamoney.angrybirds.levels.Level2;
import com.ninjamoney.angrybirds.levels.Level3;

public class LevelSelectorScreen implements Screen {
    private Stage stage;
    private AngryBirds game;

    private Texture levelSelectorBackground;
    private Texture levelSelectorTexture;
    private Texture backButtonTexture;
    private Texture l1Texture, l2Texture, l3Texture;

    private ImageButton backButton;
    private ImageButton level1Button, level2Button, level3Button;

    public LevelSelectorScreen(AngryBirds game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        levelSelectorBackground = new Texture("game/bg/lsmmenu.jpg");
        levelSelectorTexture = new Texture("game/bg/lst.png");
        backButtonTexture = new Texture("buttons/back.png");
        l1Texture = new Texture("buttons/levels/l1.png");
        l2Texture = new Texture("buttons/levels/l2.png");
        l3Texture = new Texture("buttons/levels/l3.png");

        createButtons();
    }

    private void createButtons() {
        // Back button
        backButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(backButtonTexture))
        );
        backButton.setSize(103, 93);
        backButton.addListener(event -> {
            if (backButton.isPressed()) {
                game.setScreen(new MainMenuScreen(game));
                return true;
            }
            return false;
        });

        // Level 1 button
        level1Button = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(l1Texture))
        );
        level1Button.setSize(238, 242);
        level1Button.addListener(event -> {
            if (level1Button.isPressed()) {
                game.setScreen(new Level1(game, 1, false));
                return true;
            }
            return false;
        });

        // Level 2 button
        level2Button = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(l2Texture))
        );
        level2Button.setSize(238, 242);
        level2Button.addListener(e -> {
//            if (level2Button.isPressed()) {
//                if(Level1.cleared){
//                    game.setScreen(new Level2(game, 2, false));
//                }
////                game.setScreen(new Level2(game, 2, true));
//                return true;
//            }
            game.setScreen(new Level2(game, 2, true));
            return false;
        });

        // Level 3 button
        level3Button = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(l3Texture))
        );
        level3Button.setSize(238, 242);
        level3Button.addListener(e -> {
//            if (level3Button.isPressed()) {
//                if(Level2.cleared){
//                    game.setScreen(new Level3(game, 3, false));
//                }
//                return true;
//            }
            game.setScreen(new Level3(game, 3, true));
            return false;
        });

        // Add buttons to the stage
        stage.addActor(backButton);
        stage.addActor(level1Button);
        stage.addActor(level2Button);
        stage.addActor(level3Button);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background and title
        stage.getBatch().begin();
        stage.getBatch().draw(levelSelectorBackground, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        stage.getBatch().draw(levelSelectorTexture, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight() / 4);
        stage.getBatch().end();

        // Render the buttons
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        float viewportWidth = stage.getViewport().getWorldWidth();
        float viewportHeight = stage.getViewport().getWorldHeight();

        // Center the back button at the top-left corner
        backButton.setPosition(10, viewportHeight - backButton.getHeight() - 10);

        // Dynamically position the level buttons
        float buttonSpacing = 30;
        float totalButtonWidth = (3 * level1Button.getWidth()) + (2 * buttonSpacing);

        // Calculate starting X position for centering the buttons
        float startX = (viewportWidth - totalButtonWidth) / 2;
        float buttonY = viewportHeight / 2 - level1Button.getHeight() / 2;

        // Set positions for the buttons
        level1Button.setPosition(startX, buttonY);
        level2Button.setPosition(startX + level1Button.getWidth() + buttonSpacing, buttonY);
        level3Button.setPosition(startX + 2 * (level1Button.getWidth() + buttonSpacing), buttonY);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        levelSelectorBackground.dispose();
        levelSelectorTexture.dispose();
        backButtonTexture.dispose();
        l1Texture.dispose();
        l2Texture.dispose();
        l3Texture.dispose();
    }
}

