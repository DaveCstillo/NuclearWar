package com.progra.nuclearwar.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.NuclearWarGame;

public class PlayScreen implements Screen {

    NuclearWarGame Game;
    Texture texture;

    OrthographicCamera mainCamera;
    Viewport gameport;

    public PlayScreen(NuclearWarGame game) {
        this.Game = game;
        texture = new Texture("badlogic.jpg");
        mainCamera = new OrthographicCamera();
        gameport = new FitViewport(800,480, mainCamera);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Game.batch.setProjectionMatrix(mainCamera.combined);
        Game.batch.begin();
        Game.batch.draw(texture,0,0);
        Game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width,height);

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
