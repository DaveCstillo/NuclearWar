package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.progra.nuclearwar.Hitbox.Ground;
import com.progra.nuclearwar.Hitbox.InteractiveTileObject;
import com.progra.nuclearwar.Hitbox.Puerta1;
import com.progra.nuclearwar.Hitbox.Puerta2;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Sprites.Enemies.Enemy;
import com.progra.nuclearwar.Sprites.Player.Character;

public class worldContactListener implements ContactListener {
    boolean onLadder;

    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("Contact", "Begin Contact");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef =  fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        if (fixA.getUserData() == "feet" || fixB.getUserData() == "feet") {
            Fixture feet = fixA.getUserData() == "feet" ? fixA : fixB;
            Fixture object = feet == fixA ? fixB : fixA;

            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).onFeetHit();
                if (object.getUserData().getClass() == Ground.class) {
                    Gdx.app.log("Ground", "tocando");
                    ((Ground) object.getUserData()).tocando();
                }
            }
        }

        switch (cDef){

            case NuclearWarGame.ENEMY_HEAD_BIT | NuclearWarGame.PLAYER_BIT:
                if(fixA.getFilterData().categoryBits == NuclearWarGame.ENEMY_HEAD_BIT)
                    ((Enemy)fixA.getUserData()).onHeadHit();
                if(fixB.getFilterData().categoryBits==NuclearWarGame.ENEMY_HEAD_BIT)
                    ((Enemy)fixB.getUserData()).onHeadHit();

                break;
            case NuclearWarGame.PLAYER_BIT | NuclearWarGame.DOORS_BIT:
                if(fixA.getUserData().getClass() == Puerta1.class)
                    ((Puerta1) fixA.getUserData()).entrar((Character) fixB.getUserData());
                else
                    ((Puerta1) fixB.getUserData()).entrar((Character) fixA.getUserData());

                if(fixA.getUserData().getClass() == Puerta2.class)
                    ((Puerta2) fixA.getUserData()).entrar((Character) fixB.getUserData());
                else
                    ((Puerta2) fixB.getUserData()).entrar((Character) fixA.getUserData());

                break;


        }

    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("Contact","End Contact");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();


        if(fixA.getUserData()=="feet"||fixB.getUserData()=="feet"){
            Fixture feet = fixA.getUserData() =="feet" ? fixA:fixB;
            Fixture object = feet == fixA ? fixB:fixA;

            if(object.getUserData()!=null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject) object.getUserData()).onFeetHit();

                if(object.getUserData().getClass() == Ground.class){
                    Gdx.app.log("Ground","no tocando");
                    ((Ground)object.getUserData()).notocando();
                }

            }

        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
