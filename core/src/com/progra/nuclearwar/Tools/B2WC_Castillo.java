package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.progra.nuclearwar.Hitbox.Cofre;
import com.progra.nuclearwar.Hitbox.Enemy_Bounds;
import com.progra.nuclearwar.Hitbox.Entrada;
import com.progra.nuclearwar.Hitbox.Suelo;
import com.progra.nuclearwar.Hitbox.Suelo_Temporal;
import com.progra.nuclearwar.Hitbox.Suelo_Total;
import com.progra.nuclearwar.Hitbox.Walls;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;
import com.progra.nuclearwar.Sprites.Enemies.Goblin;
import com.progra.nuclearwar.Sprites.Enemies.Skull;

public class B2WC_Castillo {

    Array<Skull> Esqueletos;
    Array<Goblin> Duendes;


    public B2WC_Castillo(PlayScreen playScreen) {
        TiledMap map = playScreen.getMap();

        Suelo_Temporal temporal = null;
        Suelo_Total total = null;


        //cambio a interactive tileobject, en el constructor ya no pide RectangleMaoObject, ahora pide MapObject
        //eliminamos el rectangulo, y solo pasamos el objeto
        for (MapObject object : map.getLayers().get("limites").getObjects().getByType(RectangleMapObject.class)){
            new Walls(playScreen, object);
        }
        for (MapObject object : map.getLayers().get("suelo").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Suelo(playScreen, object);
        }
        for (MapObject object : map.getLayers().get("Piso_temporal").getObjects().getByType(RectangleMapObject.class)){
           temporal = new Suelo_Temporal(playScreen, object);
        }
        for (MapObject object : map.getLayers().get("Piso_Total").getObjects().getByType(RectangleMapObject.class)){
            total = new Suelo_Total(playScreen, object);
        }
        for (MapObject object : map.getLayers().get("Entrada").getObjects().getByType(RectangleMapObject.class)){
            new Entrada(playScreen, object,temporal,total);
        }
        for (MapObject object : map.getLayers().get("Cofres").getObjects().getByType(RectangleMapObject.class)){
            new Cofre(playScreen, object);
        }
        for (MapObject object : map.getLayers().get("BOUNDS").getObjects().getByType(RectangleMapObject.class)){
            new Enemy_Bounds(playScreen, object);
        }


        //creando enemigos
        Esqueletos = new Array<Skull>();
        for (MapObject object : map.getLayers().get("Esqueletos").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            Esqueletos.add(new Skull(playScreen, rect.getX() / NuclearWarGame.PPM, rect.getY() / NuclearWarGame.PPM));
        }
        Duendes = new Array<Goblin>();
        for (MapObject object : map.getLayers().get("Duendes").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            Duendes.add(new Goblin(playScreen, rect.getX() / NuclearWarGame.PPM, rect.getY() / NuclearWarGame.PPM));
        }
    }


    public Array<Skull> getEsqueletos() {
        return Esqueletos;
    }

    public Array<Goblin> getDuendes() {
        return Duendes;
    }


}
