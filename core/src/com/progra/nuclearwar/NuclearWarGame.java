package com.progra.nuclearwar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.progra.nuclearwar.Screens.PlayScreen;

public class NuclearWarGame extends Game {
	public static final int V_WIDTH = 800; //800
	public static final int V_HEIGHT = 416;  //416
	public static final float PPM = 100; //Pixeles Por Metro

	public static final short DEFAULT_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short COINS_BIT = 4;
	public static final short GROUND_BIT = 8;
	public static final short DOORS_BIT = 16;
	//public static final short LADDERS_BIT = 16;
	public static final short SPIKES_BIT = 32;
	//public static final short GROUND_LADDDER_BIT = 64;
	public static final short DESTROYED_BIT = 128;

	public SpriteBatch batch;

	public static AssetManager assetManager;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		assetManager.load("audio/music/music1.wav",Music.class);
		assetManager.load("audio/music/music2.wav",Music.class);
		assetManager.load("audio/music/music3.wav",Music.class);
		assetManager.load("audio/music/music4.wav",Music.class);
		assetManager.load("audio/sounds/jump.wav",Music.class);
		assetManager.load("audio/sounds/ladder.wav",Music.class);
		assetManager.load("audio/sounds/running_in_grass.wav",Music.class);
		assetManager.load("audio/sounds/spike_trap.wav",Music.class);
		assetManager.finishLoading();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
		assetManager.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		assetManager.dispose();
	}
}
