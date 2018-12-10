package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.maps.MapObject;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.BaseScreen;
import com.progra.nuclearwar.Screens.Castle_Screen;

public class CastilloEntrada extends InteractiveTileObject {
    BaseScreen pantalla;
    NuclearWarGame game;

    public CastilloEntrada(BaseScreen screen, MapObject object) {
        super(screen, object);
        pantalla = screen;
        game = screen.getGame();
        fixture.setUserData(this);
        setCategoryFilter(NuclearWarGame.DOORS_BIT);
    }

    public void changeMap(){
       screen.setChangingMap(true);
    }

    @Override
    public void onHeadHit() {

    }

    @Override
    public void onFeetHit() {

    }
}
