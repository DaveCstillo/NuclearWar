package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;
import com.progra.nuclearwar.Sprites.Character;

public class Puerta1 extends InteractiveTileObject {

    PlayScreen pantalla;

    public Puerta1(PlayScreen screen, Rectangle bds) {
        super(screen, bds);
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

    public void entrar(){
        Gdx.app.log("Puerta", "Entrando");
        Character player = pantalla.getPlayer();

        Vector2 position = player.body.getPosition();
        Gdx.app.log("Puerta", "Posicion del player: "+ position.toString());
        //player.translate(-5, 2);
        player.moveCharacter(724f/NuclearWarGame.PPM,280f/NuclearWarGame.PPM);

    }

    @Override
    public void setCategoryFilter(short filterBit) {
        super.setCategoryFilter(filterBit);
    }
}
