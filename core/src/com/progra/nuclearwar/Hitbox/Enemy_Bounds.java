package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.BaseScreen;
import com.progra.nuclearwar.Screens.PlayScreen;

public class Enemy_Bounds extends InteractiveTileObject{

    BaseScreen pantalla;


    public Enemy_Bounds(BaseScreen screen, MapObject object) {
        super(screen, object);
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
