package com.progra.nuclearwar.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;

import org.omg.CORBA.Current;

public class Character extends Sprite {

    TextureRegion characterStand, characterJump;

    public enum State{FALLING, JUMPING, STANDING, RUNNING}

    public State Currentstate, PreviousState;

    Animation characterRun;

    boolean runningRight;
    float stateTimer;

    World world;
    public Body body;

    Stage stage;
    Viewport vport;

    public Character(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("walk"));

        Currentstate = State.STANDING;
        PreviousState = State.STANDING;

        characterStand = new TextureRegion(getTexture(),576,0,32,32);
        setBounds(0,0,32/NuclearWarGame.PPM,32/NuclearWarGame.PPM);
        setRegion(characterStand);
        this.world = world;
        defineCharacter();

        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i=6;i<8;i++){
            frames.add(new TextureRegion(getTexture(),i*32,0,32,32));
        }
        characterRun = new Animation(0.2f,frames);
        frames.clear();

        characterJump = new TextureRegion(getTexture(),64,0,32,32);

    }
    public void update(float dt){
        setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
        setRegion(getFrame(dt));


    }


    public TextureRegion getFrame(float dt){
        Currentstate = getState();

        TextureRegion region;
        switch(Currentstate) {
            case JUMPING:
                region = characterJump;
                break;
            case RUNNING:
                region = (TextureRegion) characterRun.getKeyFrame(stateTimer,true);
                break;
            case FALLING:
                case STANDING:
            default: region = characterStand;
            break;
        }
        if((body.getLinearVelocity().x<0||!runningRight)&&!region.isFlipX()){
            region.flip(true,false);
            runningRight = false;
        }else if((body.getLinearVelocity().x>0||runningRight)&&region.isFlipX()){
            region.flip(true,false);
            runningRight = true;
        }
        stateTimer = Currentstate == PreviousState ? stateTimer + dt :0;
        PreviousState = Currentstate;

        return region;
    }


    public State getState(){
        if(body.getLinearVelocity().y>0||body.getLinearVelocity().y<0&&PreviousState == State.JUMPING){
            return State.JUMPING;
        }else if(body.getLinearVelocity().y<0){
            return State.FALLING;
        }else if(body.getLinearVelocity().x!=0){
            return State.RUNNING;
        }else{
            return State.STANDING;
        }
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
