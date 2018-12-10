package com.progra.nuclearwar.Hitbox;


import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.BaseScreen;
import com.progra.nuclearwar.Screens.PlayScreen;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    protected MapObject object;
    protected BaseScreen screen;

    public InteractiveTileObject(BaseScreen screen, MapObject object) {
        this.object = object;
        this.screen = screen;
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = ((RectangleMapObject) object).getRectangle();
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2)  / NuclearWarGame.PPM, (bounds.getY() + bounds.getHeight() / 2)  / NuclearWarGame.PPM);
        body = world.createBody(bdef);

        shape.setAsBox((bounds.getWidth()/2) / NuclearWarGame.PPM , (bounds.getHeight()/2)  / NuclearWarGame.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }
    public  abstract void onHeadHit();
    public abstract void onFeetHit();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public Filter getCategoryFilter(){
        return fixture.getFilterData();
    }

    public TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Chests");
        return layer.getCell((int) (body.getPosition().x * NuclearWarGame.PPM / 16), (int) (body.getPosition().y * NuclearWarGame.PPM / 16));
            //body.position.x determina la posicion del objeto, * PPM es el escalado, multiplicado para el original, dividido para lo que se ve y 16 es el tamaño de los tiles en el mapa
     }
}