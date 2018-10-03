package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;

public class Coins {
World world;
TiledMap map;
Ellipse bounds;
Body body;
Fixture fixture;
    public Coins(PlayScreen screen, Ellipse bounds) {
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;

        BodyDef bodydef = new BodyDef();
        FixtureDef fixdef = new FixtureDef();
        CircleShape circle = new CircleShape();

        bodydef.type = BodyDef.BodyType.StaticBody;
        bodydef.position.set((bounds.x + bounds.width/2)/ NuclearWarGame.PPM,(bounds.y+bounds.height/2)/NuclearWarGame.PPM);
        body = world.createBody(bodydef);
        circle.setRadius((bounds.height/2)/NuclearWarGame.PPM);

        fixdef.shape = circle;
        fixture = body.createFixture(fixdef);

    }

}
