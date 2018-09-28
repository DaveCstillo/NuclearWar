package com.progra.nuclearwar.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.Hud;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Sprites.Character;
import com.progra.nuclearwar.Tools.AController;
import com.progra.nuclearwar.Tools.MController;

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

    Character player;

    //Area de controles
    MController mcontroller;
    AController acontroller;

    public PlayScreen(NuclearWarGame game) {
        this.Game = game;
        mainCamera = new OrthographicCamera();
        gameport = new FitViewport(Game.V_WIDTH /Game.PPM, Game.V_HEIGHT / Game.PPM,mainCamera);
        hud = new Hud(Game.batch);
        loader = new TmxMapLoader();
        map = loader.load("mapa1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1/Game.PPM);
        mainCamera.position.set(gameport.getWorldWidth()/2,gameport.getWorldHeight()/2,0);
        world = new World(new Vector2(0, -10/Game.PPM),true);
        box2drenderer = new Box2DDebugRenderer();
        mcontroller = new MController(this);
        acontroller = new AController(this);

        BodyDef bodydef = new BodyDef();
        PolygonShape polygonshape = new PolygonShape();
        CircleShape circle = new CircleShape();
        FixtureDef fixturedef = new FixtureDef();
        Body body;

        player = new Character(world);

        for (MapObject object : map.getLayers().get("ground").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodydef.type = BodyDef.BodyType.StaticBody;
            bodydef.position.set((rect.getX()+ rect.getWidth()/2)/Game.PPM, (rect.getY()+rect.getHeight()/2)/Game.PPM);

            body = world.createBody(bodydef);

            polygonshape.setAsBox((rect.getWidth()/2)/Game.PPM,(rect.getHeight()/2)/Game.PPM);

            fixturedef.shape = polygonshape;

            body.createFixture(fixturedef);
        }


        for (MapObject object : map.getLayers().get("spikes").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodydef.type = BodyDef.BodyType.StaticBody;
            bodydef.position.set((rect.getX()+ rect.getWidth()/2)/Game.PPM, (rect.getY()+rect.getHeight()/2)/Game.PPM);

            body = world.createBody(bodydef);

            polygonshape.setAsBox((rect.getWidth()/2)/Game.PPM,(rect.getHeight()/2)/Game.PPM);

            fixturedef.shape = polygonshape;

            body.createFixture(fixturedef);
        }

        for (MapObject object : map.getLayers().get("ladder").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodydef.type = BodyDef.BodyType.StaticBody;
            bodydef.position.set((rect.getX()+ rect.getWidth()/2)/Game.PPM, (rect.getY()+rect.getHeight()/2)/Game.PPM);

            body = world.createBody(bodydef);

            polygonshape.setAsBox((rect.getWidth()/2)/Game.PPM,(rect.getHeight()/2)/Game.PPM);

            fixturedef.shape = polygonshape;

            body.createFixture(fixturedef);
        }

        for (MapObject object : map.getLayers().get("platforms").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodydef.type = BodyDef.BodyType.StaticBody;
            bodydef.position.set((rect.getX()+ rect.getWidth()/2)/Game.PPM, (rect.getY()+rect.getHeight()/2)/Game.PPM);

            body = world.createBody(bodydef);

            polygonshape.setAsBox((rect.getWidth()/2)/Game.PPM,(rect.getHeight()/2)/Game.PPM);

            fixturedef.shape = polygonshape;

            body.createFixture(fixturedef);
        }

        for (MapObject object : map.getLayers().get("walls").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodydef.type = BodyDef.BodyType.StaticBody;
            bodydef.position.set((rect.getX()+ rect.getWidth()/2)/Game.PPM, (rect.getY()+rect.getHeight()/2)/Game.PPM);

            body = world.createBody(bodydef);

            polygonshape.setAsBox((rect.getWidth()/2)/Game.PPM,(rect.getHeight()/2)/Game.PPM);

            fixturedef.shape = polygonshape;

            body.createFixture(fixturedef);
        }

        for (MapObject object : map.getLayers().get("coins").getObjects().getByType(EllipseMapObject.class)){
            Ellipse ellipse = ((EllipseMapObject)object).getEllipse();
            bodydef.type = BodyDef.BodyType.StaticBody;
            bodydef.position.set((ellipse.x+ ellipse.width/2) / Game.PPM,(ellipse.y+ellipse.height/2)/Game.PPM);
            body = world.createBody(bodydef);
            circle.setRadius((ellipse.height/2)/Game.PPM);
            fixturedef.shape = circle;

            body.createFixture(fixturedef);
        }
    }

        public void handleinput(float dt){
            if(Gdx.input.isTouched()){
                mainCamera.position.x += 20*dt;
            }
            if(mcontroller.isLpressed()){
                player.body.applyLinearImpulse(new Vector2(-1f,0),player.body.getWorldCenter(),true);
            }
            if(mcontroller.isRpressed()){
                player.body.applyLinearImpulse(new Vector2(1f,0),player.body.getWorldCenter(),true);
            }
            if(acontroller.isJumppressed()){
                player.body.applyLinearImpulse(new Vector2(0,0.03f),player.body.getWorldCenter(),true);
            }
    }


     public void update(float dt){
        handleinput(dt);
        world.step(1/60f,6,2);
        mainCamera.update();
        renderer.setView(mainCamera);


     }

     public Viewport getViewport(){
        return gameport;
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

    //    Game.batch.begin();
  //      player.draw(Game.batch);
//        Game.batch.end();


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
