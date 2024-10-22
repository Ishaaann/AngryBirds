package com.ninjamoney.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.w3c.dom.Text;

public class MainMenuScreen implements Screen {
    private SpriteBatch batch;
    private Texture menuTexture;
    private Texture playbutton;
    private AngryBirds game;
    private OrthographicCamera cam;
    private Texture logo;
    private Texture playbutton1;

    public MainMenuScreen(Game game) {
        batch = new SpriteBatch();
        this.game = (AngryBirds) game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        menuTexture = new Texture("finalmenu.png");
//        playbutton = new Texture("playbutton.png");
        logo = new Texture("logo.png");
        playbutton = new Texture("Play1.png");
        playbutton1 = new Texture("Play2.png");

    }

    @Override
    public void show() {}

    public void handleInput(){
        float x = Gdx.input.getX();
        float y = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert Y coordinate

        if(x > 422 && x < 839 && y > 111 && y < 241){
            batch.draw(playbutton1, 422, 111, 417, 268);
            if(Gdx.input.isTouched()) {
                game.setScreen(new LevelSelectorScreen(game));
            }
        }
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(menuTexture, 0, 0, 1280f, 720f);
        batch.draw(logo, 230, 470, 820, 177);
        batch.draw(playbutton, 422,111, 417, 268);
        handleInput();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
//        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        menuTexture.dispose();
        playbutton.dispose();
    }
}
