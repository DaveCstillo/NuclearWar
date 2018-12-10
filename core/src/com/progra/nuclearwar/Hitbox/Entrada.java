package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.BaseScreen;
import com.progra.nuclearwar.Screens.PlayScreen;

public class Entrada extends InteractiveTileObject {
    BaseScreen pantalla;
    Suelo_Total total;
    Suelo_Temporal temporal;
    TiledMap castillomapa;

    public Entrada(BaseScreen screen, MapObject object, Suelo_Temporal temporal, Suelo_Total total) {
        super(screen, object);
        pantalla = screen;
        this.temporal = temporal;
        this.total = total;
        fixture.setUserData(this);
        setCategoryFilter(NuclearWarGame.ENTRANCE_BIT);
        castillomapa = screen.getMap();

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
    public void cerrando(){
        castillomapa.getLayers().get("Piso_Nuevo").setVisible(true);
        castillomapa.getLayers().get("Piso_Viejo").setVisible(false);
        temporal.toggleGround();
        total.toggleGround();
        setCategoryFilter(NuclearWarGame.DESTROYED_BIT);
    }
}
