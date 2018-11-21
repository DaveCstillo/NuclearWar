package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.progra.nuclearwar.Hitbox.Entrada;
import com.progra.nuclearwar.Hitbox.Ground;
import com.progra.nuclearwar.Hitbox.InteractiveTileObject;
import com.progra.nuclearwar.Hitbox.Suelo;
import com.progra.nuclearwar.Hitbox.Suelo_Total;

public class WCL_Castillo implements ContactListener {
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
                if(object.getUserData().getClass() == Suelo.class){
                    Gdx.app.log("Ground","tocando");
                    ((Suelo)object.getUserData()).tocando();
                }
                if(object.getUserData().getClass() == Suelo_Total.class){
                    Gdx.app.log("Ground","tocando");
                    ((Suelo_Total)object.getUserData()).tocando();
                }

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
                if(object.getUserData().getClass() == Suelo.class){
                    Gdx.app.log("Ground","no tocando");
                    ((Suelo)object.getUserData()).notocando();
                }
                if(object.getUserData().getClass() == Suelo_Total.class){
                    Gdx.app.log("Ground","tocando");
                    ((Suelo_Total)object.getUserData()).notocando();
                }
                if(object.getUserData().getClass() == Entrada.class){
                    Gdx.app.log("Entrada","tocando");
                    ((Entrada)object.getUserData()).cerrando();
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