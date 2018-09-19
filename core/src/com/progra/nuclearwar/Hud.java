package com.progra.nuclearwar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud {

    Stage stage;
    Viewport vport;
    Integer worldtimer;
    float timer;
    Integer score;
    OrthographicCamera camera;

    Label name, scorelabel, world, worldnmb, timertxt, time;

    public Hud(SpriteBatch image) {
        worldtimer = 300;
        timer = 0;
        score = 0;
        camera = new OrthographicCamera();
        vport = new FitViewport(NuclearWarGame.V_WIDTH,NuclearWarGame.V_HEIGHT,camera);
        stage = new Stage(vport, image);



        Table playerhud = new Table();
        playerhud.top();
        playerhud.setFillParent(true);

        //Cambio desde la ultima clase miercoles19/sept/2018

        /*
        * se hizo el importe de la libreria de badlogic para los colores...   "com.badlogic.gdx.graphics.Color"
        * y con esto se tiene el color, el error que yo tuve, fue que lo estaba escribiendo con minuscula, Por eso es importante
        * tener en cuenta que los nombres de las clases se escriben con mayusculas y los nombres de las variables se escriben con minusculas
        *
        */

        timertxt = new Label(String.format("%03d", worldtimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scorelabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        time = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldnmb = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        world = new Label("World", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        name = new Label("Player", new Label.LabelStyle(new BitmapFont(), Color.WHITE));



    }
}
