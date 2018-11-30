package com.progra.nuclearwar.Sprites.Items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;
import com.progra.nuclearwar.Sprites.Player.Character;

public abstract class Item extends Sprite {

    protected PlayScreen screen;
    protected World world;
    protected Vector2 velocity;
    protected boolean toDestroy;
    protected boolean destroyed;
    protected Body body;


    public Item(PlayScreen screen, float x, float y) {
        this.screen = screen;
        this.world = screen.getWorld();
        setPosition(x,y);
        setBounds(getX(),getY(),8/ NuclearWarGame.PPM, 8/NuclearWarGame.PPM);
        defineItem();
        toDestroy = false;
        destroyed = false;


    }


    public abstract void defineItem();
    public abstract void use(Character player);

    public void update(float dt){
        if(toDestroy && !destroyed){
            world.destroyBody(body);
            destroyed = true;
        }
    }

    public void destroy(){
        toDestroy = true;
    }

    public void draw(SpriteBatch batch){
        if(!destroyed)
            super.draw(batch);
    }




}
