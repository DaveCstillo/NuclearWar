package com.progra.nuclearwar;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.progra.nuclearwar.Screens.PlayScreen;

public class NuclearWarGame extends Game {
	public static final int V_WIDTH = 800; //800
	public static final int V_HEIGHT = 416;  //416
	public static final float PPM = 100; //Pixeles Por Metro

	//Bits multiplos de 2 para la colision de objetos(hitbox)
    public static final short NOTHING_BIT =0;
	public static final short GROUND_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short CHEST_BIT = 4;
	public static final short ENEMY_BIT = 8;
	public static final short DOORS_BIT = 16;
	public static final short WALL_BIT = 32;
	public static final short DESTROYED_BIT = 64;
	public static final short ENEMY_HEAD_BIT = 128;
	public static final short ENEMY_BOUNDS_BIT =256;
	public static final short ITEM_BIT =512;


	/*por ejemplo,  si el personaje esta tocando el suelo, la combinacion en numeros binarios
	de esa colision es de 00011 que es actualmente 3
*/

	//la imagen general del juego, se dibuja todo sobre esto
	public SpriteBatch batch;

	//la variable dedicada para los recursos
	public static AssetManager assetManager;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		//cargar musica
		assetManager = new AssetManager();
		assetManager.load("audio/music/music1.wav",Music.class);
		assetManager.load("audio/music/music2.wav",Music.class);
		assetManager.load("audio/music/music3.wav",Music.class);
		assetManager.load("audio/music/music4.wav",Music.class);
		assetManager.load("audio/sounds/jump.wav",Music.class);
		assetManager.load("audio/sounds/ladder.wav",Music.class);
		assetManager.load("audio/sounds/running_in_grass.wav",Music.class);
		assetManager.load("audio/sounds/spike_trap.wav",Music.class);
		assetManager.load("audio/sounds/chest_opening.wav",Music.class);
		assetManager.load("audio/sounds/coin.wav",Music.class);
        assetManager.load( "audio/sounds/pitch_item.wav",Music.class);
		assetManager.load( "audio/sounds/falling-bones.wav",Music.class);
		assetManager.load( "audio/sounds/goblin-death.wav",Music.class);
		assetManager.load( "audio/sounds/hit_player.wav",Music.class);
		assetManager.load( "audio/sounds/slow-zombie-death.mp3",Music.class);
		assetManager.load( "audio/sounds/game-die.mp3",Music.class);
		assetManager.load( "audio/sounds/videogame-dieorlose.flac",Music.class);
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
