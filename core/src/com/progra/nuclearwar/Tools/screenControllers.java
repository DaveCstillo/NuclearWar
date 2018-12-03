package com.progra.nuclearwar.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.NuclearWarGame;

public class screenControllers implements Disposable {
    OrthographicCamera camera;
    Stage stage;
    Viewport port;

    AController actionC;
    MController movementC;

    //clase creada para el manejo de los controles
    public screenControllers(SpriteBatch batch) {

        camera = new OrthographicCamera();
        port = new FitViewport(NuclearWarGame.V_WIDTH/NuclearWarGame.PPM, NuclearWarGame.V_HEIGHT/NuclearWarGame.PPM,camera);
        stage = new Stage(port,batch);

        actionC = new AController(stage, port);
        movementC=new MController(stage, port);

        Gdx.input.setInputProcessor(stage);
    }


    public void draw(){
        actionC.draw();
        movementC.draw();
    }

    public AController getActionC() {
        return actionC;
    }

    public MController getMovementC() {
        return movementC;
    }

    @Override
    public void dispose() {

    }
}
