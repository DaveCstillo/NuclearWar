package com.progra.nuclearwar.Sprites.Player;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;


public class Character extends Sprite {
//imagenes del personaje, estando parado y saltando
    TextureRegion characterStand, characterJump, characterDead;
//es una enumeracion que indica que en que estado se encuentra el personaje
    public enum State{FALLING, JUMPING, STANDING, RUNNING, DEAD} //new enum DEAD
//para verificar en que estado esta y estuvo el personaje
    public State Currentstate, PreviousState;

    Animation characterRun;//animacion del personaje, corriendo

    boolean runningRight;//verifica si el personaje esta coriendo hacia la derecha
    float stateTimer;//actualizaion del mundo

    World world;
    public Body body;
    public BodyDef bodydef;

    public boolean toMove;
    public boolean isOliverDead;//new control variable
    public float moveToX, moveToY;

    public Character(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("Oliver"));
//la imagen del personaje esta en la region de "Oliver" de la imagen Characters.png
        TextureAtlas.AtlasRegion atlas = screen.getAtlas().findRegion("Oliver");
        Currentstate = State.STANDING;
        PreviousState = State.STANDING;

        characterStand = new TextureRegion(atlas,0,0,8,16);
        setBounds(0,0,8/NuclearWarGame.PPM,16/NuclearWarGame.PPM);//escalar la imagen
        setRegion(characterStand);//pone la imagen del personaje al hitbox
        this.world = world;
        defineCharacter();//define el cuerpo de box2d(hitbox) en el mundo

        stateTimer = 0;
        runningRight = true;
        toMove = false;
        isOliverDead = false;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        //animacion del personaje corriendo
        for(int i=3;i<7;i++){
            //i=3 quiere decir que la imagen comienza en la tercera posicion, i<7 quiere decir que la animcaion termina antes de la septima posicion
            frames.add(new TextureRegion(atlas,i*8,0,8,16));
            //i*8 indica el desplazamiento de posicion de cada imagen en el atlas, porque cada imagen tiene 8 pixeles de ancho
        }
        characterRun = new Animation(0.2f,frames);//se agrega las imagenes a la animacion y se coloca un tiempo de duracion
        frames.clear();

        characterJump = new TextureRegion(atlas,8,0,8,16);//para definir la imagen del personaje saltando

        characterDead = new TextureRegion(atlas, 16,0,8,16);//new textureRegion para el personaje muerto
    }

    public void update(float dt){
        setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
        setRegion(getFrame(dt));
        if(toMove){
            tpCharacter();
        }
    }


    public TextureRegion getFrame(float dt){//determina en que estado se encuentra el personaje para darle una imagen
        Currentstate = getState();

        TextureRegion region;
        switch(Currentstate) {
            case JUMPING:
                region = characterJump;
                break;
            case RUNNING:
                region = (TextureRegion) characterRun.getKeyFrame(stateTimer,true);
                break;
            case DEAD: //new case to set the TextureRegion
                region = characterDead;
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
        }else if(isOliverDead){//new if statement to get State of character
            return State.DEAD;
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
                NuclearWarGame.ITEM_BIT |
                NuclearWarGame.DOORS_BIT;

        fixturedef.shape = circle;
        body.createFixture(fixturedef).setUserData(this);

        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-4/NuclearWarGame.PPM, -9 /NuclearWarGame.PPM),new Vector2(4/NuclearWarGame.PPM, -9/NuclearWarGame.PPM));
        fixturedef.shape = feet;
        fixturedef.isSensor = true;
        fixturedef.filter.maskBits = NuclearWarGame.GROUND_BIT;

        body.createFixture(fixturedef).setUserData("feet");
    }

    public State getPreviousState() {
        return PreviousState;
    }

    public void moveCharacter(){

        bodydef = new BodyDef();
        bodydef.position.set(moveToX,moveToY);
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
                        NuclearWarGame.ITEM_BIT |
                        NuclearWarGame.DOORS_BIT;

        fixturedef.shape = circle;
        body.createFixture(fixturedef).setUserData(this);

        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-4/NuclearWarGame.PPM, -9 /NuclearWarGame.PPM),new Vector2(4/NuclearWarGame.PPM, -9/NuclearWarGame.PPM));
        fixturedef.shape = feet;
        fixturedef.isSensor = true;
        fixturedef.filter.maskBits = NuclearWarGame.GROUND_BIT;

        body.createFixture(fixturedef).setUserData("feet");
        toMove = false;

    }

    public void tpCharacter(){
        world.destroyBody(body);
        moveCharacter();
    }


    public void setToMove(float x, float y) {
        this.toMove = true;
        this.moveToX = x;
        this.moveToY = y;
    }


    public void hit(){ //new code here
        NuclearWarGame.assetManager.get("audio/music/music1.wav",Music.class).stop();
        NuclearWarGame.assetManager.get("audio/sounds/hit-player.wav",Music.class).play();
        isOliverDead= true;
        Filter filter = new Filter();
        filter.maskBits = NuclearWarGame.NOTHING_BIT;
        for(Fixture fix : body.getFixtureList())
            fix.setUserData(filter);

        body.applyLinearImpulse(new Vector2(0, 4f),body.getWorldCenter(),true);



    }
}
