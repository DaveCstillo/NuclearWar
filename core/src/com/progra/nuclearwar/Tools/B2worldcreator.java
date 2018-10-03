package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.progra.nuclearwar.Hitbox.Coins;
import com.progra.nuclearwar.Hitbox.Ground;
import com.progra.nuclearwar.Hitbox.Ladders;
import com.progra.nuclearwar.Hitbox.Platforms;
import com.progra.nuclearwar.Hitbox.Spikes;
import com.progra.nuclearwar.Hitbox.Walls;
import com.progra.nuclearwar.Screens.PlayScreen;

public class B2worldcreator {

    public B2worldcreator(PlayScreen playScreen) {
        TiledMap map = playScreen.getMap();

        for (MapObject object : map.getLayers().get("walls").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Walls(playScreen, rect);
        }
        for (MapObject object : map.getLayers().get("platforms").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Platforms(playScreen,rect);
        }
        for (MapObject object : map.getLayers().get("ladder").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Ladders(playScreen,rect);
        }
        for (MapObject object : map.getLayers().get("spikes").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Spikes(playScreen,rect);
        }
        for (MapObject object : map.getLayers().get("ground").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Ground(playScreen,rect);
        }
            //Coins
        for (MapObject object : map.getLayers().get("coins").getObjects().getByType(EllipseMapObject.class)){
            Ellipse ellipse = ((EllipseMapObject)object).getEllipse();
            new Coins(playScreen, ellipse);
        }
    }

}
