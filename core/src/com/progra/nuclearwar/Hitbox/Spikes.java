package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;

public class Spikes extends InteractiveTileObject {
    public Spikes(PlayScreen screen, MapObject object) {
        super(screen, object);
        fixture.setUserData(this);
        //setCategoryFilter(NuclearWarGame.SPIKES_BIT);
    }

    @Override
    public void onHeadHit() {

    }

    @Override
    public void onFeetHit() {
        Gdx.app.log("Spike","Collision");
    }
}
