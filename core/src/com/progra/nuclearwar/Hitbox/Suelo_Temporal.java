package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;

public class Suelo_Temporal extends InteractiveTileObject {
    PlayScreen pantalla;

    public Suelo_Temporal(PlayScreen screen,MapObject object) {
        super(screen, object);
        pantalla = screen;
        fixture.setUserData(this);
        setCategoryFilter(NuclearWarGame.GROUND_BIT);
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
    public void toggleGround(){
        setCategoryFilter(NuclearWarGame.DESTROYED_BIT);
    }
}
