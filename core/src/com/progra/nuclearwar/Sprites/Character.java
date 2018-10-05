package com.progra.nuclearwar.Sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;

public class Character extends Sprite {

    TextureRegion stance;


    World world;
    public Body body;

    Stage stage;
    Viewport vport;

    public Character(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("walk"));
        stance = new TextureRegion(getTexture(),576,0,32,32);
        setBounds(0,0,32/NuclearWarGame.PPM,32/NuclearWarGame.PPM);
        setRegion(stance);
        this.world = world;
        defineCharacter();
    }
    public void update(float dt){
        setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
    }


    public void defineCharacter(){
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(160/ NuclearWarGame.PPM,224/NuclearWarGame.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodydef);

        CircleShape circle = new CircleShape();
        FixtureDef fixturedef = new FixtureDef();

        circle.setRadius(14/NuclearWarGame.PPM);
        fixturedef.shape = circle;

        body.createFixture(fixturedef);
    }



}
