package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;
import com.progra.nuclearwar.Sprites.Player.Character;

public class Puerta1 extends InteractiveTileObject {

    PlayScreen pantalla;

    public Puerta1(PlayScreen screen, MapObject object) {
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
        Gdx.app.log("Puerta", "Entrando");

        Vector2 position = player.body.getPosition();
        Gdx.app.log("Puerta", "Posicion del player: "+ position.toString());
        //player.translate(-5, 2);
        player.setToMove(true,724f/NuclearWarGame.PPM,280f/NuclearWarGame.PPM);

    }

    @Override
    public void setCategoryFilter(short filterBit) {
        super.setCategoryFilter(filterBit);
    }
}
