package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.progra.nuclearwar.Hitbox.Coins;
import com.progra.nuclearwar.Hitbox.Ground;
import com.progra.nuclearwar.Hitbox.GroundTriangles;
import com.progra.nuclearwar.Hitbox.Ladders;
import com.progra.nuclearwar.Hitbox.Platforms;
import com.progra.nuclearwar.Hitbox.Puerta1;
import com.progra.nuclearwar.Hitbox.Puerta2;
import com.progra.nuclearwar.Hitbox.Spikes;
import com.progra.nuclearwar.Hitbox.Walls;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;

public class B2worldcreator {

    public B2worldcreator(PlayScreen playScreen) {
        TiledMap map = playScreen.getMap();


        for (MapObject object : map.getLayers().get("limites").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Walls(playScreen, rect);
        }
        //TODO: decomment plataformas
        for (MapObject object : map.getLayers().get("plataformas").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Platforms(playScreen,rect);
        }
//        for (MapObject object : map.getLayers().get("ladder").getObjects().getByType(RectangleMapObject.class)) {
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//            new Ladders(playScreen,rect);
//        }
//        for (MapObject object : map.getLayers().get("spikes").getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject)object).getRectangle();
//            new Spikes(playScreen,rect);
//        }

        //TODO decomment castillo, puerta1, puerta2
        for (MapObject object : map.getLayers().get("castillo").getObjects().getByType(RectangleMapObject.class)){
                Rectangle rect = ((RectangleMapObject)object).getRectangle();
                new Walls(playScreen,rect);
        }
        for (MapObject object : map.getLayers().get("puerta1").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Puerta1(playScreen,rect);
        }
        for (MapObject object : map.getLayers().get("puerta2").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Puerta2(playScreen,rect);
        }
        for (MapObject object : map.getLayers().get("suelo").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            new Ground(playScreen,rect);
        }
//        for (MapObject object : map.getLayers().get("suelo").getObjects().getByType(PolygonMapObject.class)){
//           PolygonShape polygonShape = getPolygon((PolygonMapObject)object);
//            new GroundTriangles(playScreen,polygonShape);
//        }
            //Coins
       /* for (MapObject object : map.getLayers().get("coins").getObjects().getByType(EllipseMapObject.class)){
            Ellipse ellipse = ((EllipseMapObject)object).getEllipse();
            new Coins(playScreen, ellipse);
        }*/
    }


    private static PolygonShape getPolygon(PolygonMapObject polygonObject) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            System.out.println(vertices[i]);
            worldVertices[i] = vertices[i] / NuclearWarGame.PPM;
        }

        polygon.set(worldVertices);
        return polygon;
    }


}
