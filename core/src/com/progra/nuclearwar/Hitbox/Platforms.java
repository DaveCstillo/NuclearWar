package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.BaseScreen;
import com.progra.nuclearwar.Screens.PlayScreen;

public class Platforms extends InteractiveTileObject {
    BaseScreen Pantalla;

    public Platforms(BaseScreen screen, MapObject object) {
        super(screen, object);
        Pantalla = screen;
        fixture.setUserData(this);
        setCategoryFilter(NuclearWarGame.GROUND_BIT);
    }

    @Override
    public void onHeadHit() {

    }

    @Override
    public void onFeetHit() {
        Gdx.app.log("Platform","Collision");

        if(Pantalla.isOnGround()){
            notocando();
        }else{
            tocando();
        }
    }
    public void tocando(){
        Pantalla.setOnGround(true);
    }
    public void notocando(){
        Pantalla.setOnGround(false);
    }
}
