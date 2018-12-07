package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.NuclearWarGame;

public class MController {
    //variablees de control que determinan si se presionan los botones
    boolean lpressed, rpressed;

    Viewport btnvport;
    public Stage stage;


    boolean ispaused;


    public MController(Stage escenario, Viewport puerto) {
        btnvport = puerto;
        stage = escenario;

        Table table = new Table();
        table.left().bottom().setBounds(0,0,1,1);

        Image leftbtn = new Image(new Texture("leftbtn.png"));//se carga la imagen del boton izquierdo
        leftbtn.setSize(60/ NuclearWarGame.PPM,60/NuclearWarGame.PPM);
        leftbtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                lpressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                lpressed = false;
            }
        });
        Image rightbtn = new Image(new Texture("rightbtn.png"));//se carga la imagen del boton derecho
        rightbtn.setSize(60/NuclearWarGame.PPM,60/NuclearWarGame.PPM);
        rightbtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {//TouchDown es para detectar si toca la pantalla
                rpressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {//TouchUp es para detectar cuando suelta la pantalla
                rpressed = false;
            }
        });

        //para agregar los botones a la tabla
        table.add(leftbtn).size(leftbtn.getWidth(),leftbtn.getHeight()).pad(1/NuclearWarGame.PPM,0.2f,1/NuclearWarGame.PPM,2/NuclearWarGame.PPM);
        table.add(rightbtn).size(rightbtn.getWidth(),rightbtn.getHeight()).pad(1/NuclearWarGame.PPM,2/NuclearWarGame.PPM,1/NuclearWarGame.PPM,0.2f);

        //se agrega la tabla al escenario
        stage.addActor(table);
    }

    public boolean isLpressed() {
        if(ispaused)
            return false;
        else
        return lpressed;
    }

    public boolean isRpressed() {
        if(ispaused)
            return false;
        else
            return rpressed;
    }

    public boolean isanypressed(){
        if(isLpressed()){
            return true;
        }
        if(isRpressed()){
            return true;
        }else{
            return false;
        }
    }

    public void resize(int width,int height){
        btnvport.update(width,height);
    }

    public void draw(){
        stage.draw();
    }

    public void setPressedButtons(boolean paused){
       ispaused = paused;
    }
}
