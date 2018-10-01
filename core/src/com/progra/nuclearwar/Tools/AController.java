package com.progra.nuclearwar.Tools;

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
    Stage stage;

    public AController(SpriteBatch image) {
        OrthographicCamera camera = new OrthographicCamera();
        btnvport = new FitViewport(NuclearWarGame.V_WIDTH,NuclearWarGame.V_HEIGHT,camera);
        stage = new Stage(btnvport, image);

        Table table = new Table();
        table.bottom().right();

        Image jumpbtn = new Image(new Texture("jumpbtn.png"));
        jumpbtn.setSize(20/NuclearWarGame.PPM,20/NuclearWarGame.PPM);
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

        stage.addActor(table);
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
}
