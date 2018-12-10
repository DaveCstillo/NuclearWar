package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.BaseScreen;
import com.progra.nuclearwar.Screens.Castle_Screen;
import com.progra.nuclearwar.Screens.PlayScreen;
import com.progra.nuclearwar.Sprites.Items.Coin;
import com.progra.nuclearwar.Sprites.Items.Heart;
import com.progra.nuclearwar.Sprites.Items.ItemDef;

public class Cofre extends InteractiveTileObject {

    BaseScreen pantalla;
    private static TiledMapTileSet tileset;
   // private final int OPEN_CHEST = 406;
    private int OPEN_CHEST;
    TiledMap map;
    private Music openChest;


    public Cofre(BaseScreen screen, MapObject object) {
        super(screen, object);
        pantalla = screen;
        map = screen.getMap();
        tileset = map.getTileSets().getTileSet("miscelanea_stronghold");
        fixture.setUserData(this);

        if(screen.getClass()==PlayScreen.class)
            OPEN_CHEST = 2103;
        else if(screen.getClass()== Castle_Screen.class)
            OPEN_CHEST = 406;


        setCategoryFilter(NuclearWarGame.CHEST_BIT);
        openChest = NuclearWarGame.assetManager.get("audio/sounds/chest_opening.wav", Music.class);
    }

    @Override
    public void onHeadHit() {
    }

    public void openChest(){
        openChest.play();
        TiledMapTile tile = tileset.getTile(OPEN_CHEST);
        if(tile!=null)
            getCell().setTile(tile);
        setCategoryFilter(NuclearWarGame.DESTROYED_BIT);
        if (object.getProperties().containsKey("Coin"))
            screen.spawnItems(new ItemDef(new Vector2(body.getPosition().x,(body.getPosition().y+16/NuclearWarGame.PPM)),Coin.class ));
        if (object.getProperties().containsKey("Heart"))
            screen.spawnItems(new ItemDef(new Vector2(body.getPosition().x,(body.getPosition().y+16/NuclearWarGame.PPM)),Heart.class ));

    }

    @Override
    public void onFeetHit() {

    }
}
