package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.progra.nuclearwar.Hitbox.Cofre;
import com.progra.nuclearwar.Hitbox.Enemy_Bounds;
import com.progra.nuclearwar.Hitbox.Ground;
import com.progra.nuclearwar.Hitbox.Platforms;
import com.progra.nuclearwar.Hitbox.Puerta1;
import com.progra.nuclearwar.Hitbox.Puerta2;
import com.progra.nuclearwar.Hitbox.Walls;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;
import com.progra.nuclearwar.Sprites.Enemies.Goblin;
import com.progra.nuclearwar.Sprites.Enemies.Skull;

public class B2worldcreator {
    Array<Skull> Esqueletos;
    Array<Goblin> Duendes;


    public B2worldcreator(PlayScreen playScreen) {
        TiledMap map = playScreen.getMap();//para regresar el mapa para controlar

        //cambio a interactive tileobject, en el constructor ya no pide RectangleMaoObject, ahora pide MapObject
        //eliminamos el rectangulo, y solo pasamos el objeto
        //para cada objeto(hitbox) de cierta capa de objetos, convertir los objetos en sus respectivas clases
        for (MapObject object : map.getLayers().get("limites").getObjects().getByType(RectangleMapObject.class)){
            new Walls(playScreen, object);
        }
        for (MapObject object : map.getLayers().get("plataformas").getObjects().getByType(RectangleMapObject.class)) {
            new Platforms(playScreen,object);
        }
        for (MapObject object : map.getLayers().get("castillo").getObjects().getByType(RectangleMapObject.class)){
                new Walls(playScreen,object);
        }
        for (MapObject object : map.getLayers().get("puerta1").getObjects().getByType(RectangleMapObject.class)){
            new Puerta1(playScreen,object);
        }
        for (MapObject object : map.getLayers().get("puerta2").getObjects().getByType(RectangleMapObject.class)){
            new Puerta2(playScreen,object);
        }
        for (MapObject object : map.getLayers().get("suelo").getObjects().getByType(RectangleMapObject.class)){
            new Ground(playScreen,object);
        }
        for (MapObject object : map.getLayers().get("Cofres").getObjects().getByType(RectangleMapObject.class)){
            new Cofre(playScreen, object);
        }
        for (MapObject object : map.getLayers().get("BOUNDS").getObjects().getByType(RectangleMapObject.class)){
            new Enemy_Bounds(playScreen, object);
        }

        Esqueletos = new Array<Skull>();
        for (MapObject object : map.getLayers().get("Esqueletos").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            Esqueletos.add(new Skull(playScreen, rect.getX() / NuclearWarGame.PPM, rect.getY() / NuclearWarGame.PPM));
            //para agregar los esqueletos en la posicion del objeto dentro del mapa
        }
        Duendes = new Array<Goblin>();
        for (MapObject object : map.getLayers().get("Duendes").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            Duendes.add(new Goblin(playScreen, rect.getX() / NuclearWarGame.PPM, rect.getY() / NuclearWarGame.PPM));
            //para agregar los duendes en la posicion del objeto dentro del mapa
        }
    }
    public Array<Skull> getEsqueletos() {
        return Esqueletos;
    }

    public Array<Goblin> getDuendes() {
        return Duendes;
    }
}
