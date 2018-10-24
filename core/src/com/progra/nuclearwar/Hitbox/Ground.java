package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;

public class Ground extends InteractiveTileObject{
    PlayScreen pantalla;

    public Ground(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        pantalla = screen;
        fixture.setUserData(this);
        setCategoryFilter(NuclearWarGame.GROUND_BIT);
    }

    @Override
    public void onHeadHit() {

    }

    @Override
    public void onFeetHit() {
        Gdx.app.log("Ground","Collision");
    }

    public void tocando(){
        pantalla.setOnGround(true);
    }
    public void notocando(){
        pantalla.setOnGround(false);
    }
}
