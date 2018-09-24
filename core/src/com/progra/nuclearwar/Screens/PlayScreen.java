package com.progra.nuclearwar.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
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

    World world;
    Box2DDebugRenderer box2drenderer;

    public PlayScreen(NuclearWarGame game) {
        this.Game = game;
        mainCamera = new OrthographicCamera();
        gameport = new FitViewport(Game.V_WIDTH, Game.V_HEIGHT,mainCamera);
        hud = new Hud(Game.batch);
        loader = new TmxMapLoader();
        map = loader.load("mapa1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        mainCamera.position.set(gameport.getWorldWidth()/2,gameport.getWorldHeight()/2,0);
        world = new World(new Vector2(0, 0),true);
        Box2dRenderer = new Box2DDebugRenderer();

        BodyDef bodydef = new BodyDef();
        PolygonShape polygonshape = new PolygonShape();
        FixtureDef fixturedef = new FixtureDef();
        Body body;


        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodydef.type = BodyDef.BodyType.StaticBody;
            bodydef.position.set(rect.getX()+ rect.getWidth()/2, rect.getY()+rect.getHeight()/2);

            body = world.createBody(bodydef);

            polygonshape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);

            fixturedef.shape = polygonshape;

            body.createFixture(fixturedef);
        }


        for (MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodydef.type = BodyDef.BodyType.StaticBody;
            bodydef.position.set(rect.getX()+ rect.getWidth()/2, rect.getY()+rect.getHeight()/2);

            body = world.createBody(bodydef);

            polygonshape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);

            fixturedef.shape = polygonshape;

            body.createFixture(fixturedef);
        }

        for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodydef.type = BodyDef.BodyType.StaticBody;
            bodydef.position.set(rect.getX()+ rect.getWidth()/2, rect.getY()+rect.getHeight()/2);

            body = world.createBody(bodydef);

            polygonshape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);

            fixturedef.shape = polygonshape;

            body.createFixture(fixturedef);
        }

        for (MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodydef.type = BodyDef.BodyType.StaticBody;
            bodydef.position.set(rect.getX()+ rect.getWidth()/2, rect.getY()+rect.getHeight()/2);

            body = world.createBody(bodydef);

            polygonshape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);

            fixturedef.shape = polygonshape;

            body.createFixture(fixturedef);
        }

        for (MapObject object : map.getLayers().get(14).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodydef.type = BodyDef.BodyType.StaticBody;
            bodydef.position.set(rect.getX()+ rect.getWidth()/2, rect.getY()+rect.getHeight()/2);

            body = world.createBody(bodydef);

            polygonshape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);

            fixturedef.shape = polygonshape;

            body.createFixture(fixturedef);
        }
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

        box2drenderer.render(world, mainCamera.combined);

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
