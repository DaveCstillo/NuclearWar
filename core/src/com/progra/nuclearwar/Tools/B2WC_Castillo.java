package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.Hitbox.Entrada;
import com.progra.nuclearwar.Hitbox.Suelo;
import com.progra.nuclearwar.Hitbox.Suelo_Temporal;
import com.progra.nuclearwar.Hitbox.Suelo_Total;
import com.progra.nuclearwar.Hitbox.Walls;
import com.progra.nuclearwar.Screens.PlayScreen;

public class B2WC_Castillo {

    public B2WC_Castillo(PlayScreen playScreen) {
        TiledMap map = playScreen.getMap();

        Suelo_Temporal temporal = null;
        Suelo_Total total = null;
        for (MapObject object : map.getLayers().get("limites").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Walls(playScreen, rect);
        }
        for (MapObject object : map.getLayers().get("suelo").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Suelo(playScreen, rect);
        }
        for (MapObject object : map.getLayers().get("Piso_temporal").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
           temporal = new Suelo_Temporal(playScreen, rect);
        }
        for (MapObject object : map.getLayers().get("Piso_Total").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            total = new Suelo_Total(playScreen, rect);
        }
        for (MapObject object : map.getLayers().get("Entrada").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Entrada(playScreen, rect,temporal,total);
        }
        for (MapObject object : map.getLayers().get("Cofres").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Walls(playScreen, rect);
        }
    }
}
