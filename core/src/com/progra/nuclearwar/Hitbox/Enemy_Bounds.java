package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;

public class Enemy_Bounds extends InteractiveTileObject{

    PlayScreen pantalla;


    public Enemy_Bounds(PlayScreen screen, Rectangle bds) {
        super(screen, bds);
        pantalla = screen;
        fixture.setUserData(this);
        setCategoryFilter(NuclearWarGame.ENEMY_BOUNDS_BIT);
    }

    @Override
    public void onHeadHit() {

    }

    @Override
    public void onFeetHit() {

    }

    @Override
    public void setCategoryFilter(short filterBit) {
        super.setCategoryFilter(filterBit);
    }
}
