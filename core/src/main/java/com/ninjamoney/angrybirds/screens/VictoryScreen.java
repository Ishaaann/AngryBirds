package com.ninjamoney.angrybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ninjamoney.angrybirds.AngryBirds;
import com.ninjamoney.angrybirds.levels.Level1;
import com.ninjamoney.angrybirds.levels.Level2;
import com.ninjamoney.angrybirds.levels.Level3;

public class VictoryScreen implements Screen {
    private AngryBirds game;
    private Stage stage;
    private SpriteBatch batch;
    private Texture background;
    private Texture playAgainTexture;
    private Texture nextButtonTexture;
    private ImageButton playAgainButton;
    private ImageButton nextButton;
    private BitmapFont font;
    private int currentLevel;  // To store the current level number

    public VictoryScreen(AngryBirds game, int currentLevel) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.stage = new Stage();
        this.currentLevel = currentLevel;  // Set the current level
        Gdx.input.setInputProcessor(stage);  // Set input processor for the stage
    }

    @Override
    public void show() {
        // Load textures for buttons
        background = new Texture("game/bg/finalmenu.png");
        playAgainTexture = new Texture("buttons/replay.png");
        nextButtonTexture = new Texture("buttons/next.png");

        // Load font
        font = new BitmapFont();

        // Create button styles for playAgain and next
        ImageButtonStyle playAgainStyle = new ImageButtonStyle();
        playAgainStyle.up = new TextureRegionDrawable(playAgainTexture);

        ImageButtonStyle nextButtonStyle = new ImageButtonStyle();
        nextButtonStyle.up = new TextureRegionDrawable(nextButtonTexture);

        // Create ImageButton instances
        playAgainButton = new ImageButton(playAgainStyle);
        nextButton = new ImageButton(nextButtonStyle);

        // Calculate positions for the buttons
        float screenWidth = stage.getViewport().getWorldWidth();
        float screenHeight = stage.getViewport().getWorldHeight();
        float buttonWidth = playAgainButton.getWidth();
        float buttonHeight = playAgainButton.getHeight();

        float playAgainX = screenWidth / 3 - buttonWidth / 2;
        float nextButtonX = 2 * screenWidth / 3 - buttonWidth / 2;
        float buttonY = screenHeight / 2 - buttonHeight / 2;

        // Set positions of the buttons
        playAgainButton.setPosition(playAgainX, buttonY);
        nextButton.setPosition(nextButtonX, buttonY);

        // Add buttons to the stage
        stage.addActor(playAgainButton);
        stage.addActor(nextButton);

        // Handle button clicks
        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Restart the same level
                restartLevel(currentLevel);
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Go to next level, or if no next level, go to level selector
                nextLevel(currentLevel);
            }
        });
    }

    private void restartLevel(int level) {
        // Dynamically load the level based on the current level
        switch (level) {
            case 1:
                game.setScreen(new Level1(game, level, false)); // Load Level1
                break;
            case 2:
                game.setScreen(new Level2(game, level, false)); // Load Level2
                break;
            case 3:
                game.setScreen(new Level3(game, level, false)); // Load Level3
                break;
            // Add more cases for additional levels if necessary
            default:
                game.setScreen(new Level1(game, level, false)); // Default to Level1
                break;
        }
    }

    private void nextLevel(int level) {
        // Go to the next level (or back to level selector if no more levels)
        if (level == 3) {
            // No Level 4, go back to the level selector screen
            game.setScreen(new LevelSelectorScreen(game)); // Go to level selector
        } else {
            int nextLevel = level + 1;
            switch (nextLevel) {
                case 2:
                    game.setScreen(new Level2(game, nextLevel, false)); // Load Level2
                    break;
                case 3:
                    game.setScreen(new Level3(game, nextLevel, false)); // Load Level3
                    break;
                // You can add more levels here if needed
                default:
                    game.setScreen(new LevelSelectorScreen(game)); // Default to Level Selector if no next level
                    break;
            }
        }
    }

    @Override
    public void render(float delta) {
        batch.begin();
        // Draw the background image
        batch.draw(background, 0, 0, 1280, 720);

        // Draw the "Victory" text
        font.draw(batch, "Victory", 640 - 50, 360 + 200);

        batch.end();

        // Draw buttons on the stage (buttons are automatically drawn via stage)
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f)); // Updates the stage actions
        stage.draw(); // Draw the stage with the buttons
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Handle game pause state (optional)
    }

    @Override
    public void resume() {
        // Handle game resume state (optional)
    }

    @Override
    public void hide() {
        // Clean up when the screen is hidden (optional)
    }

    @Override
    public void dispose() {
        // Dispose resources when the screen is no longer needed
        batch.dispose();
        background.dispose();
        playAgainTexture.dispose();
        nextButtonTexture.dispose();
        stage.dispose();
        font.dispose();
    }
}
