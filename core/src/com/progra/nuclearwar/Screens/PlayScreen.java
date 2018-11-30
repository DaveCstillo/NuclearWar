package com.progra.nuclearwar.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.Hud;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Sprites.Items.Coin;
import com.progra.nuclearwar.Sprites.Items.Heart;
import com.progra.nuclearwar.Sprites.Items.Item;
import com.progra.nuclearwar.Sprites.Items.ItemDef;
import com.progra.nuclearwar.Sprites.Player.Character;
import com.progra.nuclearwar.Sprites.Enemies.Enemy;
import com.progra.nuclearwar.Sprites.Enemies.FireSkull;
import com.progra.nuclearwar.Sprites.Enemies.Goblin;
import com.progra.nuclearwar.Sprites.Enemies.Mushroom;
import com.progra.nuclearwar.Tools.AController;
import com.progra.nuclearwar.Tools.B2WC_Castillo;
import com.progra.nuclearwar.Tools.MController;
import com.progra.nuclearwar.Tools.WCL_Castillo;
import com.progra.nuclearwar.Tools.screenControllers;

import java.util.concurrent.LinkedBlockingQueue;

import static com.progra.nuclearwar.NuclearWarGame.V_WIDTH;


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

    Stage gameStage;

    Character player;
    TextureAtlas atlas;

    private Music music, grassSound;

    boolean onGround = false;
    boolean onTriangle = false;

    //Area de controles
    MController mcontroller;
    AController acontroller;
    screenControllers controllers;

    //Enemigos

    private FireSkull calaca;
    private Mushroom hongo;



    //Items
    private Array<Item> items;
    private LinkedBlockingQueue<ItemDef> itemsToSpawn;


    B2WC_Castillo creator;
    //TODo: Arreglar bien el mapa.

    public PlayScreen(NuclearWarGame game) {
        atlas = new TextureAtlas("Characters.atlas");

        this.Game = game;
        mainCamera = new OrthographicCamera();
        gameport = new FitViewport((V_WIDTH/2) /Game.PPM, (Game.V_HEIGHT/2) / Game.PPM,mainCamera);
        loader = new TmxMapLoader();
        map = loader.load("mapas/Castillo_Mapa.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1/Game.PPM);
        SpriteBatch batch = new SpriteBatch();

        mainCamera.position.set(gameport.getWorldWidth()/2,gameport.getWorldHeight()/2,0);
        world = new World(new Vector2(0, -10),false);//gravedad
        box2drenderer = new Box2DDebugRenderer();

        gameStage = new Stage(gameport,batch);

        controllers = new screenControllers(Game.batch);
        hud = new Hud(Game.batch);

        mcontroller = controllers.getMovementC();
        acontroller = controllers.getActionC();

        //new B2worldcreator(this);
        creator = new B2WC_Castillo(this);

        player = new Character(world,this);


        //temporal

        calaca = new FireSkull(this, 2.88f,5.28f);
        hongo = new Mushroom(this,1.12f,2.56f);

        //temporal


        //world.setContactListener(new worldContactListener());
        world.setContactListener(new WCL_Castillo());

        mainCamera.position.y = 1f;

        music = NuclearWarGame.assetManager.get("audio/music/music1.wav",Music.class);
        music.setLooping(true);

        grassSound =  NuclearWarGame.assetManager.get("audio/sounds/running_in_grass.wav", Music.class);
        grassSound.setLooping(true);

        //music.play();



        items = new Array<Item>();
        itemsToSpawn = new LinkedBlockingQueue<ItemDef>();
    }

    public void spawnItems(ItemDef idef){
        itemsToSpawn.add(idef);
    }

    public void handleSpawningItems(){
        if(!itemsToSpawn.isEmpty()) {
            ItemDef idef = itemsToSpawn.poll();
            if(idef.type == Coin.class){
                items.add(new Coin(this,idef.position.x,idef.position.y));
            }
            if(idef.type == Heart.class){
                items.add(new Heart(this,idef.position.x,idef.position.y));
            }
        }

    }


    public void setGravity(float x, float y){
        world.setGravity(new Vector2(x,y));
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void handleinput(float dt){
        if (isOnGround()) {
                if (mcontroller.isLpressed()) {
                    player.body.setLinearVelocity(new Vector2(-1f, 0));
                }
                if (mcontroller.isRpressed()) {
                    player.body.setLinearVelocity(new Vector2(1f, 0));
                }
        //TODO: Arreglar salto.^v
        if(acontroller.isJumppressed()) {
               mcontroller.setPressedButtons(true);
                player.body.applyLinearImpulse(new Vector2(0, 1.9f), player.body.getWorldCenter(), true);
                //  player.body.applyForceToCenter(player.body.getLinearVelocity().x,300f,true);
        }
        if(!acontroller.isJumppressed()){
            mcontroller.setPressedButtons(false);
        }

        if(!mcontroller.isLpressed() && ! mcontroller.isRpressed() && !acontroller.isJumppressed()){
                player.body.setLinearVelocity(0,0);
        }
//        if(isOnGround() && acontroller.isJumppressed() && mcontroller.isanypressed()){
//            player.body.applyLinearImpulse(new Vector2(0,5f),player.body.getWorldCenter(),true);
//        }
//        if(!isOnGround() && acontroller.isJumppressed()){
//        }
        }
        if(!isOnGround() && (player.getState() == Character.State.JUMPING)) {
            if (mcontroller.isLpressed()) {
                player.body.setLinearVelocity(new Vector2(-1f, 0));
            }
            if (mcontroller.isRpressed()) {
                player.body.setLinearVelocity(new Vector2(1f, 0));
            }
        }else {
        }
    }


     public void update(float dt){
        player.update(dt);
        hud.update(dt);
        handleinput(dt);
        handleSpawningItems();
        world.step(1/60f,6,2);




         for(Enemy enemy : creator.getEsqueletos()){
            enemy.update(dt);
            if(enemy.getY() < player.getY() + 1.5f)//si la posicion del enemigo es menor a la posicion del jugador mas la distancia a calcular
                enemy.body.setActive(true);//activar al enemigo
        }

         for(Enemy enemy : creator.getDuendes()){
             enemy.update(dt);
             if(enemy.getY() < player.getY() + 1.5f)//si la posicion del enemigo es menor a la posicion del jugador mas la distancia a calcular
                 enemy.body.setActive(true);//activar al enemigo
         }

        for(Item item : items)
             item.update(dt);


        calaca.update(dt);
        hongo.update(dt);

         if(calaca.getY() < player.getY() + 1.5f)
             calaca.body.setActive(true);

         if(hongo.getY() < player.getY() + 1.5f)
             hongo.body.setActive(true);


       /// mainCamera.position.x = player.body.getPosition().x;    //esto es para que no se mueva en x

        /*float res = player.body.getPosition().y%7;
        int simpleRes = (int) res;
        float secRes = simpleRes%2;
        int simpleSecRes = (int) secRes;
        Gdx.app.log("PosicionP", "simpleRes: "+ String.valueOf(simpleRes));
        Gdx.app.log("PosicionP", "simpleSecRes: "+ String.valueOf(simpleSecRes));*/

//esto es para que solo al caer en el suelo, se mueva la camara
        if(((player.getState() != Character.State.JUMPING) && isOnGround()) && player.body.getPosition().y <6.7f)
            mainCamera.position.y = player.body.getPosition().y+0.4f;
//(80/NuclearWarGame.PPM)
         Gdx.app.log("Jugador", "Posicion: (x,y) " + player.body.getPosition().toString());


         mainCamera.update();
         Game.batch.setProjectionMatrix(mainCamera.combined);


        renderer.setView(mainCamera);

        corriendo();


     }

     public void corriendo(){
        if(player.getState() == Character.State.RUNNING && isOnGround()){
            grassSound.play();
        }else{
            grassSound.stop();
         }

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
        Game.batch.setProjectionMatrix(mainCamera.combined);

        Game.batch.begin();
        player.draw(Game.batch);
        for(Enemy enemy : creator.getEsqueletos()){
            enemy.draw(Game.batch);
        }
        for(Enemy enemy : creator.getDuendes()){
            enemy.draw(Game.batch);
        }
        for(Item item : items)
            item.draw(Game.batch);

        calaca.draw(Game.batch);
        hongo.draw(Game.batch);
        Game.batch.end();



        hud.draw();
        controllers.draw();

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

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
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

    public boolean isOnTriangle() {
        return onTriangle;
    }

    public void setOnTriangle(boolean onTriangle) {
        this.onTriangle = onTriangle;
    }
}
