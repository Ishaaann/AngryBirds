//
//package com.ninjamoney.angrybirds.screens;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.utils.OrderedMap;
//import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.ninjamoney.angrybirds.AngryBirds;
//
//public class MainMenuScreen implements Screen {
//    private Stage stage;
//    private AngryBirds game;
//
//    private Texture menuTexture;
//    private Texture logo;
//    OrthographicCamera camera;
//
//    public MainMenuScreen(Game game) {
//        this.game = (AngryBirds) game;
//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(stage);
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, 1280, 720);
//
//        menuTexture = new Texture("game/bg/finalmenu.png");
//        logo = new Texture("game/logo.png");
//        createButtons();
//    }
//
//    private void createButtons() {
//        // Play button
//        Texture playTexture = new Texture("buttons/Play1.png");
//        Texture playHoverTexture = new Texture("buttons/Play2.png");
//        ImageButton playButton = new ImageButton(
//            new TextureRegionDrawable(new TextureRegion(playTexture)),
//            new TextureRegionDrawable(new TextureRegion(playHoverTexture))
//        );
//        playButton.setPosition(422, 111);
//        playButton.setSize(417, 268);
//
//        playButton.addListener(event -> {
//            if (playButton.isPressed()) {
//                game.setScreen(new LevelSelectorScreen(game));
//                return true;
//            }
//
//            return false;
//        });
//
//        // Sound button
//        Texture soundOnTexture = new Texture("buttons/sound/sound.png");
//        Texture soundOnHoverTexture = new Texture("buttons/sound/soundhover.png");
//        Texture soundOffTexture = new Texture("buttons/sound/soundoff.png");
//        Texture soundOffHoverTexture = new Texture("buttons/sound/soundoffhover.png");
//        ImageButton soundButton = new ImageButton(
//            new TextureRegionDrawable(new TextureRegion(soundOnTexture)),
//            new TextureRegionDrawable(new TextureRegion(soundOnHoverTexture))
//        );
//        soundButton.setPosition(1, 1);
//        soundButton.setSize(107, 107);
//
//        soundButton.addListener(event -> {
//            if (soundButton.isPressed()) {
//                boolean isMusicOn = !game.isMusicPlaying();
//                game.toggleMusic();
//                if (isMusicOn) {
//                    soundButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(soundOnTexture));
//                    soundButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(soundOnHoverTexture));
//                } else {
//                    soundButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(soundOffTexture));
//                    soundButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(soundOffHoverTexture));
//                }
//                return true;
//            }
//            return false;
//        });
//
//        stage.addActor(playButton);
//        stage.addActor(soundButton);
//    }
//
//    @Override
//    public void show() {}
//
//    @Override
//    public void render(float delta) {
//        ScreenUtils.clear(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        camera.update();
//        game.batch.setProjectionMatrix(camera.combined);
//
//        stage.getBatch().begin();
//        stage.getBatch().draw(menuTexture, 0, 0, 1280f, 720f);
//        stage.getBatch().draw(logo, 230, 470, 820, 177);
//        stage.getBatch().end();
//
//        stage.act(delta);
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        stage.getViewport().update(width, height, true);
//    }
//
//    @Override
//    public void pause() {}
//
//    @Override
//    public void resume() {}
//
//    @Override
//    public void hide() {
//        dispose();
//    }
//
//    @Override
//    public void dispose() {
//        stage.dispose();
//        menuTexture.dispose();
//        logo.dispose();
//    }
//}

package com.ninjamoney.angrybirds.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

    public MainMenuScreen(Game game) {
        this.game = (AngryBirds) game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        menuTexture = new Texture("game/bg/finalmenu.png");
        logo = new Texture("game/logo.png");

        createButtons();
    }

    private void createButtons() {
        // Play button
        Texture playTexture = new Texture("buttons/Play1.png");
        Texture playHoverTexture = new Texture("buttons/Play2.png");
        playButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(playTexture)),
            new TextureRegionDrawable(new TextureRegion(playHoverTexture))
        );
        playButton.setSize(417, 268);

        soundButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture("buttons/sound/sound.png"))),
            new TextureRegionDrawable(new TextureRegion(new Texture("buttons/sound/soundhover.png")))
        );
        soundButton.setPosition(1, 1);
        soundButton.setSize(100, 100);

        playButton.addListener(event -> {
            if (playButton.isPressed()) {
                game.setScreen(new LevelSelectorScreen(game));
                return true;
            }
            return false;
        });

        soundButton.addListener(event -> {
            if (soundButton.isPressed()) {
                boolean isMusicOn = !game.isMusicPlaying();
                game.toggleMusic();
                if (isMusicOn) {
                    soundButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("buttons/sound/sound.png")));
                    soundButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("buttons/sound/soundhover.png")));
                } else {
                    soundButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("buttons/sound/soundoff.png")));
                    soundButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("buttons/sound/soundoffhover.png")));
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

//    @Override
//    public void resize(int width, int height) {
//        stage.getViewport().update(width, height, true);
//    }

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
    }
}
