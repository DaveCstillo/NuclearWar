package com.progra.nuclearwar.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.Hud;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Sprites.Items.ItemDef;
import com.progra.nuclearwar.Sprites.Player.Character;
import com.progra.nuclearwar.Tools.AController;
import com.progra.nuclearwar.Tools.MController;
import com.progra.nuclearwar.Tools.screenControllers;

public abstract class BaseScreen implements Screen {

    NuclearWarGame game;

    //declaarciones de variables generales
    OrthographicCamera mainCamera;
    Viewport gameport;
    Hud hud;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    TmxMapLoader loader;
    World world;
    Box2DDebugRenderer box2drenderer;
    Stage gameStage;

    //declaracion del jugador
    Character player;
    TextureAtlas atlas;



    //controlador que determina si esta tocando el suelo
    boolean onGround = false;

    boolean changingMap = false;

    //Area de controles
    MController mcontroller;
    AController acontroller;
    screenControllers controllers;


    public BaseScreen(NuclearWarGame game) {
        this.game = game;
        atlas = new TextureAtlas("Characters.atlas");
    }

    public abstract void handleInput(float dt);
    public abstract void update(float dt);
    public abstract void spawnItems(ItemDef idef);
    public abstract void handleSpawningItems();

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void show() {

    }

    public Viewport getGameport() {
        return gameport;
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width,height);
        acontroller.resize(width,height);
        mcontroller.resize(width,height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        box2drenderer.dispose();
        hud.dispose();
        controllers.dispose();
    }

    public void setGravity(float x, float y){
        world.setGravity(new Vector2(x,y));
    }

    public Character getPlayer() {
        return player;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }


    public boolean gameOver(){
        if(player.Currentstate==Character.State.DEAD && player.getStateTimer()> 3)
            return true;
        else
            return false;
    }

    public boolean isChangingMap() {
        return changingMap;
    }

    public void setChangingMap(boolean changingMap) {
        this.changingMap = changingMap;
    }

    public NuclearWarGame getGame() {
        return game;
    }
}
