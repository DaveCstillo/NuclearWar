package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.Screens.PlayScreen;

public class Ground extends InteractiveTileObject{
    public Ground(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
    }

    @Override
    public void onHeadHit() {

    }

    @Override
    public void onFeetHit() {

    }
}
