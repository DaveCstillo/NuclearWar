package com.progra.nuclearwar.Sprites.Items;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.progra.nuclearwar.Hud;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.BaseScreen;
import com.progra.nuclearwar.Screens.PlayScreen;
import com.progra.nuclearwar.Sprites.Player.Character;

public class Heart extends Item {

    private Music oneup;

    public Heart(BaseScreen screen, float x, float y) {
        super(screen, x, y);
        setRegion(screen.getAtlas().findRegion("items"),0,0,8,8);
        velocity = new Vector2(0,0);
        oneup = NuclearWarGame.assetManager.get("audio/sounds/pitch_item.wav",Music.class);
    }

    @Override
    public void defineItem() {

        BodyDef bodydef = new BodyDef();
        bodydef.position.set(getX(),getY());
        bodydef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodydef);

        CircleShape circle = new CircleShape();
        FixtureDef fixturedef = new FixtureDef();

        circle.setRadius(4/ NuclearWarGame.PPM);

        fixturedef.filter.categoryBits = NuclearWarGame.ITEM_BIT;
        fixturedef.filter.maskBits =
                NuclearWarGame.GROUND_BIT |
                        NuclearWarGame.PLAYER_BIT |
                        NuclearWarGame.ITEM_BIT |
                        NuclearWarGame.CHEST_BIT;

        fixturedef.shape = circle;
        body.createFixture(fixturedef).setUserData(this);

    }

    @Override
    public void use(Character player) {
        Hud.addScore(500);
        destroy();
        oneup.play();
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(body.getPosition().x - getWidth()/2,body.getPosition().y-getHeight()/2);
        body.setLinearVelocity(velocity);
    }
}
