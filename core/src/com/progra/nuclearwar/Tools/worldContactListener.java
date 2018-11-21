package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.progra.nuclearwar.Hitbox.Ground;
import com.progra.nuclearwar.Hitbox.GroundTriangles;
import com.progra.nuclearwar.Hitbox.InteractiveTileObject;
import com.progra.nuclearwar.Hitbox.Ladders;
import com.progra.nuclearwar.Hitbox.Puerta1;
import com.progra.nuclearwar.Hitbox.Puerta2;

public class worldContactListener implements ContactListener {
    boolean onLadder;

    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("Contact","Begin Contact");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData()=="feet"||fixB.getUserData()=="feet"){
            Fixture feet = fixA.getUserData() =="feet" ? fixA:fixB;
            Fixture object = feet == fixA ? fixB:fixA;

            if(object.getUserData()!=null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject) object.getUserData()).onFeetHit();
            if(object.getUserData().getClass() == Ground.class){
                Gdx.app.log("Ground","tocando");
                ((Ground)object.getUserData()).tocando();
            }
            }
        }

        if(fixA.getUserData()=="player"||fixB.getUserData()=="player") {
            Fixture feet = fixA.getUserData() == "player" ? fixA : fixB;
            Fixture object = feet == fixA ? fixB : fixA;

            if(object.getUserData().getClass() == Puerta1.class){
                Gdx.app.log("Puerta"," Puerta 1 tocando");
                ((Puerta1)object.getUserData()).entrar();
            }
            if(object.getUserData().getClass() == Puerta2.class){
                Gdx.app.log("Puerta"," Puerta 2 tocando");
                ((Puerta2)object.getUserData()).entrar();
            }
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
