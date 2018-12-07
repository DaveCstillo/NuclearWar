package com.progra.nuclearwar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud implements Disposable {
//stage=escenario, viewport= pantalla(vista de la pantalla)
    public Stage stage;
    Viewport vport;
    //Tiempo del mundo
    Integer worldtimer;
    float timer;

    static Integer score;
    OrthographicCamera camera;
//textos del Hud
    Label name, world, worldnmb, timertxt, time;
    static Label scorelabel;

    public Hud(SpriteBatch batch) {
        //se coloca un tiempo de 300 segundos limite
        worldtimer = 300;
        timer = 0;//tiempo a cero
        score = 0;//score a cero
        //inicializamos camera,viewport,stage
        camera = new OrthographicCamera();
        vport = new FitViewport(NuclearWarGame.V_WIDTH, NuclearWarGame.V_HEIGHT,camera);
        stage = new Stage(vport,batch);


//se crea la tabla que muestra datos del nivel (tiempo, score, mapa
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
//se inicializan las variables de texto, se les da un formato, la variable a mostrar y un estilo y color de letra
        timertxt = new Label(String.format("%03d", worldtimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scorelabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        time = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldnmb = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        world = new Label("World", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        name = new Label("Player", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


//agregamos los textos a la tabla
        playerhud.add(name).expandX().padTop(10);
        playerhud.add(world).expandX().padTop(10);
        playerhud.add(time).expandX().padTop(10);

        playerhud.row();
        playerhud.add(scorelabel).expandX();
        playerhud.add(worldnmb).expandX();
        playerhud.add(timertxt).expandX();

//añadimos la tabla al escenario
        stage.addActor(playerhud);

    }
    public void update(float dt){
        timer += dt;
        if(timer>= 1){
            worldtimer--;
            timertxt.setText(String.format("%03d", worldtimer));
            timer=0;
        }
    }
    public static void addScore(int value){
        score += value;
        scorelabel.setText(String.format("%06d", score));
    }

    public void draw(){
        stage.draw();
    }

    @Override
    public void dispose() {

    }

}
