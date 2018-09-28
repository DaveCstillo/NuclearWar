package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;

public class MController {
    boolean lpressed, rpressed;

    Viewport btnvport;
    Stage stage;

    public MController(PlayScreen playscreen) {
        btnvport = playscreen.getViewport();
        stage = new Stage(btnvport, NuclearWarGame.batch);

        Table table = new Table();
        table.bottom().left();

        Image leftbtn = new Image(new Texture("leftbtn.png"));
        leftbtn.setSize(80,80);
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
        Image rightbtn = new Image(new Texture("rightbtn.png"));
        rightbtn.setSize(80,80);
        rightbtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rpressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rpressed = false;
            }
        });

        table.add(leftbtn).size(leftbtn.getWidth(),leftbtn.getHeight());
        table.add(rightbtn).size(rightbtn.getWidth(),rightbtn.getHeight());

        stage.addActor(table);
    }

    public boolean isLpressed() {
        return lpressed;
    }

    public boolean isRpressed() {
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
}
