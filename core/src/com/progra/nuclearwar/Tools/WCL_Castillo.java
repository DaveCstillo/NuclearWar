package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.progra.nuclearwar.Hitbox.Cofre;
import com.progra.nuclearwar.Hitbox.Entrada;
import com.progra.nuclearwar.Hitbox.InteractiveTileObject;
import com.progra.nuclearwar.Hitbox.Suelo;
import com.progra.nuclearwar.Hitbox.Suelo_Total;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Sprites.Enemies.Enemy;
import com.progra.nuclearwar.Sprites.Items.Item;
import com.progra.nuclearwar.Sprites.Player.Character;
//igual al world contact listener con objetos del mapa castillo.
public class WCL_Castillo implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef =  fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

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

        switch (cDef){

            case NuclearWarGame.ENEMY_HEAD_BIT | NuclearWarGame.PLAYER_BIT:
                if(fixA.getFilterData().categoryBits == NuclearWarGame.ENEMY_HEAD_BIT)
                    ((Enemy)fixA.getUserData()).onHeadHit();
                else
                    ((Enemy)fixB.getUserData()).onHeadHit();

                break;
            case NuclearWarGame.ENEMY_BIT | NuclearWarGame.ENEMY_BOUNDS_BIT:
                if(fixA.getFilterData().categoryBits == NuclearWarGame.ENEMY_BIT)
                    ((Enemy) fixA.getUserData()).reverseVelocity(true,false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true,false);

                break;
            case NuclearWarGame.ENEMY_BIT | NuclearWarGame.PLAYER_BIT:
                if(fixA.getFilterData().categoryBits == NuclearWarGame.PLAYER_BIT)
                    ((Character)fixA.getUserData()).hit();
                else
                    ((Character)fixB.getUserData()).hit();

                break;
            case NuclearWarGame.PLAYER_BIT | NuclearWarGame.CHEST_BIT:
                if(fixA.getFilterData().categoryBits == NuclearWarGame.CHEST_BIT)
                    ((Cofre) fixA.getUserData()).openChest();
                else
                    ((Cofre) fixB.getUserData()).openChest();

                break;
            case NuclearWarGame.PLAYER_BIT | NuclearWarGame.ITEM_BIT:
                if(fixA.getFilterData().categoryBits == NuclearWarGame.ITEM_BIT)
                    ((Item) fixA.getUserData()).use((Character) fixB.getUserData());
                else
                    ((Item) fixB.getUserData()).use((Character) fixA.getUserData());
                break;
            case NuclearWarGame.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
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
