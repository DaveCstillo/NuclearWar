package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.progra.nuclearwar.Hitbox.Ground;
import com.progra.nuclearwar.Hitbox.Platforms;
import com.progra.nuclearwar.Hitbox.Puerta1;
import com.progra.nuclearwar.Hitbox.Puerta2;
import com.progra.nuclearwar.Hitbox.Walls;
import com.progra.nuclearwar.Screens.PlayScreen;

public class B2worldcreator {

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
    }
}
