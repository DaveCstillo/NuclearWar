package com.progra.nuclearwar.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.progra.nuclearwar.Screens.PlayScreen;

public abstract class Enemy extends Sprite {

   protected World world;
   PlayScreen screen;
   Vector2 velocidad;
   public Body body; //traspaso de variable desde cada uno de los enemigos a una sola variable dentro de Enemy


    public Enemy(PlayScreen screen, float x, float y) {
        this.screen = screen;
        this.world = screen.getWorld();
        setPosition(x, y);
        defineEnemy();
        velocidad = new Vector2(1,-2);
        body.setActive(false); //con esto ponemos el enemigo inactivo
    }


    protected abstract void defineEnemy();

    public abstract void onHeadHit();

    public abstract void update(float dt);

    public void draw(SpriteBatch batch){
        super.draw(batch);
    };

    public void reverseVelocity(boolean x, boolean y){
            if(x)
                velocidad.x = -velocidad.x;
            if(y)
                velocidad.y = -velocidad.y;
    }
}
