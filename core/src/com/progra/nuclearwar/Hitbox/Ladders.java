package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;
import com.progra.nuclearwar.Sprites.Character;


public class Ladders extends InteractiveTileObject {

    public Ladders(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(NuclearWarGame.LADDERS_BIT);
    }

    @Override
    public void onHeadHit() {

    }

    @Override
    public void onFeetHit() {
        Gdx.app.log("Ladder","Collision");
        if(!isClimbing()){
            Gdx.app.log("Ladder","Climbing False");
        }else{
            Gdx.app.log("Ladder","Climbing True");
        }
    }
}
