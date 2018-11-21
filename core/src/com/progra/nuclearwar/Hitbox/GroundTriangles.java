package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;

import java.awt.Polygon;

public class GroundTriangles {

    World world;
    Map map;
    Fixture fixture;
    PlayScreen pantalla;

    public GroundTriangles(PlayScreen screen, PolygonShape polygon) {
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.pantalla = screen;


        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = polygon;
        Body body;


        //bdef.position.set((polygon. + polygon.getWidth() / 2)  / NuclearWarGame.PPM, (polygon.getY() + polygon.getHeight() / 2)  / NuclearWarGame.PPM);


        bdef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bdef);

        //shape.setAsBox((polygon.getWidth()/2) / NuclearWarGame.PPM , (polygon.getHeight()/2)  / NuclearWarGame.PPM);
        fdef.shape = shape;
        //fixture = body.createFixture(fdef);
        fixture = body.createFixture(polygon, 1);
        fixture.setUserData(this);
        setCategoryFilter(NuclearWarGame.GROUND_BIT);
    }

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }


    public void onFeetHit(boolean onTriangle){
        Gdx.app.log("Triangle", "tochando");
        pantalla.setOnTriangle(onTriangle);
    }

}
