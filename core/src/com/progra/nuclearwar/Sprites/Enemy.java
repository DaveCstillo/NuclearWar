package com.progra.nuclearwar.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.progra.nuclearwar.Screens.PlayScreen;

public abstract class Enemy extends Sprite {

   protected World world;
   PlayScreen screen;



    public Enemy(PlayScreen screen, float x, float y) {
        this.screen = screen;
        this.world = screen.getWorld();
        setPosition(x, y);
        defineEnemy();

    }


    protected abstract void defineEnemy();

    public abstract void onHeadHit();
}
