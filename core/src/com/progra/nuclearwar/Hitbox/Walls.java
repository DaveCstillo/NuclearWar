package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.Screens.PlayScreen;

public class Walls extends InteractiveTileObject{
    public Walls(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
    }

    @Override
    public void onHeadHit() {

    }

    @Override
    public void onFeetHit() {

    }
}
