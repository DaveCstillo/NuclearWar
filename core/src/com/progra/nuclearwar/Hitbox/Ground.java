package com.progra.nuclearwar.Hitbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.progra.nuclearwar.NuclearWarGame;
import com.progra.nuclearwar.Screens.PlayScreen;

public class Ground extends InteractiveTileObject{
    private Music music;

    public Ground(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(NuclearWarGame.GROUND_BIT);
        music =  NuclearWarGame.assetManager.get("audio/music/music4.wav", Music.class);
        music.setLooping(true);
    }

    @Override
    public void onHeadHit() {

    }

    @Override
    public void onFeetHit() {
        Gdx.app.log("Ground","Collision");

        if(music.isPlaying()){
            music.stop();
        }else{
            music.play();
        }

    }

    public void tocando(){
       // if(!music.isPlaying())
        music.play();
    }
    public void notocando(){
     //   if(music.isPlaying())
            music.stop();
    }
}
