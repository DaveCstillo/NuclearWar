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

public class AController {
    boolean jumppressed, firepressed;

    Viewport btnvport;
    Stage stage;

    public AController(PlayScreen playscreen) {
        btnvport = playscreen.getViewport();
        stage = new Stage(btnvport, NuclearWarGame.batch);

        Table table = new Table();
        table.bottom().right();

        Image jumpbtn = new Image(new Texture("jumpbtn.png"));
        jumpbtn.setSize(80,80);
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
}
