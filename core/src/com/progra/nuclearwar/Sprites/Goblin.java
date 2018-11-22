package com.progra.nuclearwar.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;

import java.awt.Polygon;

public class Goblin  extends Enemy{
    BodyDef bodydef;
    Body body;

    boolean setOnDestroy;
    boolean Destroyed;

    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;

    public Goblin(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for (int i = 0; i<3;i++){
            frames.add(new TextureRegion(screen.getAtlas().findRegion("Goblin"),i*14,0,14,19));
        }
        walkAnimation = new Animation(0.4f,frames);
        stateTime = 0;
        setBounds(getWidth(),getHeight(),14/ NuclearWarGame.PPM,19/NuclearWarGame.PPM);
        setOnDestroy = false;
        Destroyed = false;
    }

    public void update(float dt){
        stateTime += dt;
        if(setOnDestroy && !Destroyed){
            world.destroyBody(body);
            Destroyed = true;
        }else if(!Destroyed){
            setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
            setRegion((TextureRegion)walkAnimation.getKeyFrame(stateTime,true));
        }
    }
    @Override
    protected void defineEnemy() {
        bodydef = new BodyDef();
        bodydef.position.set(getX(),getY());
        bodydef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodydef);

        CircleShape circle = new CircleShape();
        FixtureDef fixturedef = new FixtureDef();

        circle.setRadius(8/NuclearWarGame.PPM);

        fixturedef.filter.categoryBits = NuclearWarGame.ENEMY_BIT;
        fixturedef.filter.maskBits =
                NuclearWarGame.GROUND_BIT |
                        NuclearWarGame.WALL_BIT |
                        NuclearWarGame.ENEMY_BIT |
                        NuclearWarGame.PLAYER_BIT;

        fixturedef.shape = circle;
        body.createFixture(fixturedef).setUserData("goblin");

        PolygonShape head = new PolygonShape();
        Vector2[] vertices = new Vector2[4];
        vertices [0] = new Vector2(-7,10).scl(1/NuclearWarGame.PPM);
        vertices [1] = new Vector2(7,10).scl(1/NuclearWarGame.PPM);
        vertices [2] = new Vector2(-4,4).scl(1/NuclearWarGame.PPM);
        vertices [3] = new Vector2(4,4).scl(1/NuclearWarGame.PPM);
        head.set(vertices);

        fixturedef.shape = head;
        fixturedef.restitution = 0.8f;
        fixturedef.filter.categoryBits = NuclearWarGame.ENEMY_HEAD_BIT;
        body.createFixture(fixturedef).setUserData(this);
    }

    @Override
    public void onHeadHit() {
        setOnDestroy = true;
    }
}
