package com.progra.nuclearwar.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.Hud;
import com.progra.nuclearwar.NuclearWarGame;

public class PlayScreen implements Screen {

    NuclearWarGame Game;

    OrthographicCamera mainCamera;
    Viewport gameport;
    Hud hud;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    TmxMapLoader loader;

    public PlayScreen(NuclearWarGame game) {
        this.Game = game;
        mainCamera = new OrthographicCamera();
        gameport = new FitViewport(Game.V_WIDTH, Game.V_HEIGHT,mainCamera);
        hud = new Hud(Game.batch);
        loader = new TmxMapLoader();
        map = loader.load("mapa1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        mainCamera.position.set(gameport.getWorldWidth()/2,gameport.getWorldHeight()/2,0);

    }

        public void handleinput(float dt){
            if(Gdx.input.isTouched()){
                mainCamera.position.x += 200*dt;

            }
    }


     public void update(float dt){
        handleinput(dt);
        mainCamera.update();
        renderer.setView(mainCamera);

     }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        renderer.render();


        Game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

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
