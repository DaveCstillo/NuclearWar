package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;
import com.progra.nuclearwar.Sprites.Player.Character;

public class Puerta2 extends InteractiveTileObject {

    PlayScreen pantalla;


    public Puerta2(PlayScreen screen, MapObject object) {
        super(screen, object);
        pantalla = screen;
        fixture.setUserData(this);
        setCategoryFilter(NuclearWarGame.DOORS_BIT);
    }

    @Override
    public void onHeadHit() {

    }

    @Override
    public void onFeetHit() {

    }

    public void entrar(Character player){
        //posicion de la puerta: 32, 22
        Gdx.app.log("Puerta", "Entrando");
        player.setToMove( 744f/NuclearWarGame.PPM,112f/NuclearWarGame.PPM);
    }


    @Override
    public void setCategoryFilter(short filterBit) {
        super.setCategoryFilter(filterBit);
    }
}
