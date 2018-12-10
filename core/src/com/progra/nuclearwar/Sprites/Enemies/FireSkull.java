package com.progra.nuclearwar.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.progra.nuclearwar.Hud;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.BaseScreen;
import com.progra.nuclearwar.Screens.PlayScreen;

public class FireSkull extends Enemy {

    BodyDef bodydef;
//variables de control para determinar cuando se va a destruir o si ya esta destruido
    boolean setOnDestroy;
    boolean destroyed;
    boolean runningRight = true;

    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;

    public FireSkull(BaseScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();

        for(int i = 0; i<3;i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("FireSkull"),i*16,0,16,21));
        walkAnimation = new Animation(0.4f,frames);
        stateTime=0;
        setBounds(getX(),getY(),16/NuclearWarGame.PPM,21/NuclearWarGame.PPM);
        setOnDestroy = false;
        destroyed = false;
    }

    public void update(float dt) {
        stateTime += dt;
        if (setOnDestroy && !destroyed) {
            world.destroyBody(body);
            destroyed = true;
            setBounds(getX(),getY(),11/NuclearWarGame.PPM,15/NuclearWarGame.PPM);
            setRegion(screen.getAtlas().findRegion("FireSkull"), 48, 0, 11, 15);
            stateTime = 0;
        } else if (!destroyed) {
            body.setLinearVelocity(velocidad);
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
            setRegion(getFrame(dt));
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
                NuclearWarGame.PLAYER_BIT |
                NuclearWarGame.ENEMY_BOUNDS_BIT |
                NuclearWarGame.ENEMY_BIT |
                NuclearWarGame.WALL_BIT;

        fixturedef.shape = circle;
        body.createFixture(fixturedef).setUserData(this);

        PolygonShape head = new PolygonShape();
        Vector2[] vertices = new Vector2[4];
        vertices[0] = new Vector2(-7,10).scl(1/ NuclearWarGame.PPM);
        vertices[1] = new Vector2(7,10).scl(1/ NuclearWarGame.PPM);
        vertices[2] = new Vector2(-4,4).scl(1/ NuclearWarGame.PPM);
        vertices[3] = new Vector2(4,4).scl(1/ NuclearWarGame.PPM);
        head.set(vertices);

        fixturedef.shape = head;
        fixturedef.restitution = 0.8f;
        fixturedef.filter.categoryBits = NuclearWarGame.ENEMY_HEAD_BIT;
        body.createFixture(fixturedef).setUserData(this);
    }

    public TextureRegion getFrame(float dt){
        TextureRegion region = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);

        if ((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }
        return region;
    }

    @Override
    public void onHeadHit() {
        setOnDestroy = true;
        Hud.addScore(150);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (!destroyed || stateTime < 1) {
            super.draw(batch);
        }
    }
}
