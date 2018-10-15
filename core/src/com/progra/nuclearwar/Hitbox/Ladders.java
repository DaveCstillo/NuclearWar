package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;
import com.progra.nuclearwar.Sprites.Character;


public class Ladders extends InteractiveTileObject {
PlayScreen pantalla;


    public Ladders(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        pantalla = screen;
        fixture.setUserData(this);
        setCategoryFilter(NuclearWarGame.LADDERS_BIT);
    }

    @Override
    public void onHeadHit() {

    }

    @Override
    public void onFeetHit() {
        Character player = pantalla.getPlayer();

        Gdx.app.log("Ladder","Collision");
        if(player.body.getLinearVelocity().y!=0 && !isClimbing()){
            Gdx.app.log("Ladder","Climbing False");
            setClimbing(true);
            pantalla.setGravity(0,0);
            player.setClimbing(true);
        }else{
            Gdx.app.log("Ladder","Climbing True");
            setClimbing(false);
            pantalla.setGravity(0,-80);
            player.setClimbing(false);
        }
    }

    //TODO: ver porque no detecta cuando esta sobre la escalera
}
