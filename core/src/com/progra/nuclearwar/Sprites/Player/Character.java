package com.progra.nuclearwar.Sprites.Player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

    public enum State{FALLING, JUMPING, STANDING, RUNNING}

    public State Currentstate, PreviousState;

    Animation characterRun;
    Animation characterClimb;

    boolean runningRight;
    float stateTimer;

    World world;
    public Body body;
    public BodyDef bodydef;

    public Character(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("Oliver"));

        TextureAtlas.AtlasRegion atlas = screen.getAtlas().findRegion("Oliver");
        Currentstate = State.STANDING;
        PreviousState = State.STANDING;

        characterStand = new TextureRegion(atlas,0,0,8,16);
        setBounds(0,0,8/NuclearWarGame.PPM,16/NuclearWarGame.PPM);
        setRegion(characterStand);
        this.world = world;
        defineCharacter();

        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i=3;i<7;i++){
            frames.add(new TextureRegion(atlas,i*8,0,8,16));
        }
        characterRun = new Animation(0.2f,frames);
        frames.clear();

        characterJump = new TextureRegion(atlas,8,0,8,16);

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
        fixturedef.filter.maskBits =
                NuclearWarGame.GROUND_BIT |
                NuclearWarGame.CHEST_BIT |
                NuclearWarGame.WALL_BIT |
                NuclearWarGame.ENEMY_BIT |
                NuclearWarGame.ENEMY_HEAD_BIT |
                NuclearWarGame.ITEM_BIT;

        fixturedef.shape = circle;
        body.createFixture(fixturedef).setUserData(this);

        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-4/NuclearWarGame.PPM, -9 /NuclearWarGame.PPM),new Vector2(4/NuclearWarGame.PPM, -9/NuclearWarGame.PPM));
        fixturedef.shape = feet;
        fixturedef.isSensor = true;
        fixturedef.filter.maskBits = NuclearWarGame.GROUND_BIT | NuclearWarGame.DOORS_BIT;

        body.createFixture(fixturedef).setUserData("feet");
    }

    public State getPreviousState() {
        return PreviousState;
    }

    public void moveCharacter(float x, float y){

        bodydef.position.set(x,y);



    }

}
