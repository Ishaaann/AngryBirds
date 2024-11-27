package com.ninjamoney.angrybirds.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ninjamoney.angrybirds.AngryBirds;

public class MainMenuScreen implements Screen {
    private final Stage stage;
    private final AngryBirds game;

    private final Texture menuTexture;
    private final Texture logo;
    private ImageButton playButton;
    private ImageButton soundButton;

    private Texture playButtonTexture;
    private Texture playButtonHoverTexture;

    private Texture soundButtonTexture;
    private Texture soundButtonHoverTexture;
    private Texture soundOffButtonTexture;
    private Texture soundOffButtonHoverTexture;

    public MainMenuScreen(Game game) {
        this.game = (AngryBirds) game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        menuTexture = new Texture("game/bg/finalmenu.png");
        logo = new Texture("game/logo.png");

        playButtonTexture = new Texture("buttons/Play1.png");
        playButtonHoverTexture = new Texture("buttons/Play2.png");

        // Sound button textures
        soundButtonTexture = new Texture("buttons/sound/sound.png");
        soundButtonHoverTexture = new Texture("buttons/sound/soundhover.png");
        soundOffButtonTexture = new Texture("buttons/sound/soundoff.png");
        soundOffButtonHoverTexture = new Texture("buttons/sound/soundoffhover.png");

        createButtons();
    }

    private void createButtons() {
        // Play button with dynamic hover effect
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(playButtonTexture));
        playButtonStyle.imageOver = new TextureRegionDrawable(new TextureRegion(playButtonHoverTexture));

        playButton = new ImageButton(playButtonStyle);
        playButton.setSize(417, 268); // Set the size of the button
        playButton.setPosition((stage.getViewport().getWorldWidth() - playButton.getWidth()) / 2,
            (stage.getViewport().getWorldHeight() - playButton.getHeight()) / 4);

        playButton.addListener(event -> {
            if (playButton.isPressed()) {
                game.setScreen(new LevelSelectorScreen(game));
                return true;
            }
            return false;
        });

        // Sound button with hover effect
        ImageButton.ImageButtonStyle soundButtonStyle = new ImageButton.ImageButtonStyle();
        soundButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(soundButtonTexture));
        soundButtonStyle.imageOver = new TextureRegionDrawable(new TextureRegion(soundButtonHoverTexture));

        soundButton = new ImageButton(soundButtonStyle);
        soundButton.setPosition(1, 1);
        soundButton.setSize(100, 100);

        soundButton.addListener(event -> {
            if (soundButton.isPressed()) {
                boolean isMusicOn = !game.isMusicPlaying();
                game.toggleMusic();

                if (isMusicOn) {
                    // If music is on, set the sound button to "on" textures and hover state
                    soundButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(soundButtonTexture));
                    soundButtonStyle.imageOver = new TextureRegionDrawable(new TextureRegion(soundButtonHoverTexture));
                } else {
                    // If music is off, set the sound button to "off" textures and hover state
                    soundButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(soundOffButtonTexture));
                    soundButtonStyle.imageOver = new TextureRegionDrawable(new TextureRegion(soundOffButtonHoverTexture));
                }
                return true;
            }
            return false;
        });

        stage.addActor(playButton);
        stage.addActor(soundButton);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        float x = (stage.getViewport().getWorldWidth() - playButton.getWidth()) / 2;
        float y = (stage.getViewport().getWorldHeight() - playButton.getHeight()) / 4;
        playButton.setPosition(x, y);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        // Render background and logo using stage's camera
        stage.getViewport().apply();
        stage.getBatch().begin();
        stage.getBatch().draw(menuTexture, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        stage.getBatch().draw(logo, (stage.getViewport().getWorldWidth() - 820) / 2, stage.getViewport().getWorldHeight() -(stage.getViewport().getWorldHeight()) / 3, 820, 177);
        stage.getBatch().end();

        // Update and render the stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        menuTexture.dispose();
        logo.dispose();
        playButtonTexture.dispose();
        playButtonHoverTexture.dispose();
        soundButtonTexture.dispose();
        soundButtonHoverTexture.dispose();
        soundOffButtonTexture.dispose();
        soundOffButtonHoverTexture.dispose();
    }
}
