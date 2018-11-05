package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;

public class AController {
    boolean jumppressed, firepressed;

    Viewport btnvport;
    public Stage stage;
    Image jumpbtn;

    public AController(Stage escenario, Viewport puerto) {
        btnvport = puerto;
        stage = escenario;

        Table table = new Table();
        table.setSize(60,60);
        table.bottom().setBounds(7,0,1,1);

        jumpbtn = new Image(new Texture("jumpbtn.png"));
        jumpbtn.setSize(60/NuclearWarGame.PPM,60/NuclearWarGame.PPM);
        jumpbtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                jumppressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                jumppressed = false;
            }

        });

        table.add(jumpbtn).size(jumpbtn.getWidth(),jumpbtn.getHeight());

        this.stage.addActor(table);
    }

    public boolean isJumppressed() {
        return jumppressed;
    }


    public boolean isAnyPressed(){

        if(isJumppressed()){
            return true;
        }else{
            return false;
        }

    }

    public void resize(int width, int height){
        btnvport.update(width,height);
    }

    public void draw(){
        stage.draw();
    }

    public void toggleJumpDown(){

    }
}
