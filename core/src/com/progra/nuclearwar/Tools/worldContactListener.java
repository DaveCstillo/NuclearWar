package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.progra.nuclearwar.Hitbox.CastilloEntrada;
import com.progra.nuclearwar.Hitbox.Cofre;
import com.progra.nuclearwar.Hitbox.Ground;
import com.progra.nuclearwar.Hitbox.InteractiveTileObject;
import com.progra.nuclearwar.Hitbox.Puerta1;
import com.progra.nuclearwar.Hitbox.Puerta2;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Sprites.Enemies.Enemy;
import com.progra.nuclearwar.Sprites.Items.Item;
import com.progra.nuclearwar.Sprites.Player.Character;

public class worldContactListener implements ContactListener {
    boolean onLadder;

    @Override
    public void beginContact(Contact contact) {
        //creamos dos figuras que determinaran quien es el jugador y quien es un objeto
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef =  fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        //aqui se detecta cuando los pies tocan contra el suelo
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
            //se detecta cuando el personaje choca contra cualquier cosa
            case NuclearWarGame.ENEMY_HEAD_BIT | NuclearWarGame.PLAYER_BIT:
                if(fixA.getFilterData().categoryBits == NuclearWarGame.ENEMY_HEAD_BIT)
                    ((Enemy)fixA.getUserData()).onHeadHit();
                if(fixB.getFilterData().categoryBits==NuclearWarGame.ENEMY_HEAD_BIT)
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
            case NuclearWarGame.PLAYER_BIT | NuclearWarGame.DOORS_BIT:
                if(fixA.getUserData().getClass() == Puerta1.class)
                    ((Puerta1) fixA.getUserData()).entrar((Character) fixB.getUserData());
                else if(fixB.getUserData().getClass() == Puerta1.class)
                    ((Puerta1) fixB.getUserData()).entrar((Character) fixA.getUserData());
                else


                if(fixA.getUserData().getClass() == Puerta2.class)
                    ((Puerta2) fixA.getUserData()).entrar((Character) fixB.getUserData());
                else if(fixB.getUserData().getClass() == Puerta2.class)
                    ((Puerta2) fixB.getUserData()).entrar((Character) fixA.getUserData());
                else


                if(fixA.getUserData().getClass() == CastilloEntrada.class)
                    ((CastilloEntrada) fixA.getUserData()).changeMap();
                else if(fixB.getUserData().getClass() == CastilloEntrada.class)
                    ((CastilloEntrada) fixB.getUserData()).changeMap();
                else


                    break;

        }

    }

    @Override
    public void endContact(Contact contact) {
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
