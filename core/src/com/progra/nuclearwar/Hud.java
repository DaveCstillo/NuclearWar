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


//        timertxt = new Label(String.format("%03d", worldtimer), new Label.LabelStyle(new BitmapFont(), );
//        scorelabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), color.White);
//        time = new Label("Time", new Label.LabelStyle(new BitmapFont, color.White);
//        worldnmb = new Label("1-1", new Label.LabelStyle(new BitmapFont, color.White);
//        world = new Label("World", new Label.LabelStyle(new BitmapFont, color.White);
//        name = new Label("Player", new Label.LabelStyle(new BitmapFont, color.White);



    }
}
