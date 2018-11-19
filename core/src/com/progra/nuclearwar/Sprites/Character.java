package com.progra.nuclearwar.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;


public class Character extends Sprite {

    TextureRegion characterStand, characterJump;

    public enum State{FALLING, JUMPING, STANDING, RUNNING, CLIMBING}

    public State Currentstate, PreviousState;

    Animation characterRun;
    Animation characterClimb;

    boolean runningRight;
    float stateTimer;

    World world;
    public Body body;
    public BodyDef bodydef;

    Stage stage;
    Viewport vport;

    static boolean isClimbing;


    //posicion del atlas
    int x = 259, y = 67;

    public Character(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("Oliver"));

        Currentstate = State.STANDING;
        PreviousState = State.STANDING;

        characterStand = new TextureRegion(getTexture(),x,y,10,18);
        setBounds(0,0,10/NuclearWarGame.PPM,18/NuclearWarGame.PPM);
        setRegion(characterStand);
        this.world = world;
        defineCharacter();

        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

//        for(int i=0;i<2;i++){
//            frames.add(new TextureRegion(getTexture(),i*8,0,8,16));
//        }
//        characterClimb = new Animation(0.2f,frames);
//        frames.clear();

        for(int i=3;i<7;i++){
            frames.add(new TextureRegion(getTexture(),x+ (i*10),y,10,18));
        }
        characterRun = new Animation(0.2f,frames);
        frames.clear();

        characterJump = new TextureRegion(getTexture(),x+10,y,10,18);

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
            case CLIMBING:
                region = (TextureRegion) characterClimb.getKeyFrame(stateTimer,true);
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
        }else if(isClimbing){
            return State.CLIMBING;
        }
        else{
            return State.STANDING;
        }
    }

    public void defineCharacter(){
        bodydef = new BodyDef();
        bodydef.position.set(64/ NuclearWarGame.PPM,128/NuclearWarGame.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodydef);

        CircleShape circle = new CircleShape();
        FixtureDef fixturedef = new FixtureDef();

        circle.setRadius(8/NuclearWarGame.PPM);

        fixturedef.filter.categoryBits = NuclearWarGame.PLAYER_BIT;
        fixturedef.filter.maskBits = NuclearWarGame.GROUND_BIT | NuclearWarGame.SPIKES_BIT | NuclearWarGame.GROUND_LADDDER_BIT | NuclearWarGame.DOORS_BIT;

        fixturedef.shape = circle;
        body.createFixture(fixturedef).setUserData("player");

        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-9/NuclearWarGame.PPM, -9 /NuclearWarGame.PPM),new Vector2(9/NuclearWarGame.PPM, -9/NuclearWarGame.PPM));
        fixturedef.shape = feet;
        fixturedef.isSensor = true;
        fixturedef.filter.maskBits = NuclearWarGame.SPIKES_BIT | NuclearWarGame.GROUND_LADDDER_BIT | NuclearWarGame.GROUND_BIT;

        body.createFixture(fixturedef).setUserData("feet");
    }

    public void moveCharacter( float x, float y){

        bodydef.position.set(x,y);



    }



    public void setClimbing(boolean climbing) {
        this.isClimbing = climbing;
    }


}
