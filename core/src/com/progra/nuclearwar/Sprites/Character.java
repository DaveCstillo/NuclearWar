package com.progra.nuclearwar.Sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.NuclearWarGame;

public class Character extends Sprite {

    World world;
    public Body body;

    Stage stage;
    Viewport vport;

    public Character(World world) {
        this.world = world;
        defineCharacter();
    }


    public void defineCharacter(){
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(160/ NuclearWarGame.PPM,224/NuclearWarGame.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodydef);

        CircleShape circle = new CircleShape();
        FixtureDef fixturedef = new FixtureDef();

        circle.setRadius(15/NuclearWarGame.PPM);
        fixturedef.shape = circle;

        body.createFixture(fixturedef);
    }



}
