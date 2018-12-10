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
import com.progra.nuclearwar.Tools.B2worldcreator;
import com.progra.nuclearwar.Tools.MController;
import com.progra.nuclearwar.Tools.WCL_Castillo;
import com.progra.nuclearwar.Tools.screenControllers;
import com.progra.nuclearwar.Tools.worldContactListener;

import java.util.concurrent.LinkedBlockingQueue;

import static com.progra.nuclearwar.NuclearWarGame.V_WIDTH;


public class PlayScreen extends BaseScreen {

    //Items
    private Array<Item> items;
    private LinkedBlockingQueue<ItemDef> itemsToSpawn;

    //musica
    private Music grassSound;

        B2worldcreator creator;

    public PlayScreen(NuclearWarGame juego) {
        super(juego);
        mainCamera = new OrthographicCamera();
        gameport = new FitViewport((V_WIDTH/2) /game.PPM, (game.V_HEIGHT/2) / game.PPM,mainCamera);
        loader = new TmxMapLoader();
        map = loader.load("mapas/mapa2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1/game.PPM);
        SpriteBatch batch = new SpriteBatch();

        mainCamera.position.set(gameport.getWorldWidth()/2,gameport.getWorldHeight()/2,0);
        world = new World(new Vector2(0, -10),false);//gravedad
        box2drenderer = new Box2DDebugRenderer();
        gameStage = new Stage(gameport,batch);

        controllers = new screenControllers(game.batch);
        hud = new Hud(game.batch);

        mcontroller = controllers.getMovementC();
        acontroller = controllers.getActionC();

        creator = new B2worldcreator(this);

        player = new Character(world,this,64,128);

        world.setContactListener(new worldContactListener());

        mainCamera.position.y = 1f;
        NuclearWarGame.assetManager.get("audio/music/music1.wav",Music.class).setLooping(true);
        NuclearWarGame.assetManager.get("audio/music/music1.wav",Music.class).play();

        grassSound =  NuclearWarGame.assetManager.get("audio/sounds/running_in_grass.wav", Music.class);
        grassSound.setLooping(true);

        items = new Array<Item>();
        itemsToSpawn = new LinkedBlockingQueue<ItemDef>();
    }
    @Override
    public void spawnItems(ItemDef idef){
        itemsToSpawn.add(idef);
    }
    @Override
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

    @Override
    public void handleInput(float dt) {
        if(player.Currentstate != Character.State.DEAD) { //new if oliver is dead, dont handle any input
            if (isOnGround()) {
                if (mcontroller.isLpressed()) {
                    player.body.setLinearVelocity(new Vector2(-1f, 0));
                }
                if (mcontroller.isRpressed()) {
                    player.body.setLinearVelocity(new Vector2(1f, 0));
                }
                //TODO: Arreglar salto.^v
                if (acontroller.isJumppressed()) {
                    mcontroller.setPressedButtons(true);
                    player.body.applyLinearImpulse(new Vector2(0, 1.9f), player.body.getWorldCenter(), true);
                }
                if (!acontroller.isJumppressed()) {
                    mcontroller.setPressedButtons(false);
                }

                if (!mcontroller.isLpressed() && !mcontroller.isRpressed() && !acontroller.isJumppressed()) {
                    player.body.setLinearVelocity(0, 0);
                }
            }
            if (!isOnGround() && (player.getState() == Character.State.JUMPING)) {
                if (mcontroller.isLpressed()) {
                    player.body.setLinearVelocity(new Vector2(-1f, 0));
                }
                if (mcontroller.isRpressed()) {
                    player.body.setLinearVelocity(new Vector2(1f, 0));
                }
            } else {
            }
        }
    }


    @Override
     public void update(float dt){
        player.update(dt);
        hud.update(dt);

         if(player.Currentstate != Character.State.DEAD) { //new if oliver is dead, don´t let camera follow oliver
             mainCamera.position.x = player.body.getPosition().x;    //esto es para que se mueva en x
             //esto es para que solo al caer en el suelo, se mueva la camara
             if (player.body.getPosition().y < 3.7f)
                 mainCamera.position.y = player.body.getPosition().y + 0.4f;
         }


        handleInput(dt);
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
         mainCamera.update();
        //(80/NuclearWarGame.PPM)

         game.batch.setProjectionMatrix(mainCamera.combined);

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

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        box2drenderer.render(world, mainCamera.combined);
        game.batch.setProjectionMatrix(mainCamera.combined);
        game.batch.begin();
        player.draw(game.batch);
        for(Enemy enemy : creator.getEsqueletos()){
            enemy.draw(game.batch);
        }
        for(Enemy enemy : creator.getDuendes()){
            enemy.draw(game.batch);
        }
        for(Item item : items)
            item.draw(game.batch);
        game.batch.end();

        hud.draw();
        controllers.draw();

        if(isChangingMap()){
            game.setScreen(new Castle_Screen(game));
            dispose();
        }

        if(gameOver()){
            game.setScreen(new GameOverScreen(game));
            dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
