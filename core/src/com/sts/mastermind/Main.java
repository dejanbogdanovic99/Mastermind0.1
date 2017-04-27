package com.sts.mastermind;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sts.mastermind.bundelPackage.DataBundle;
import com.sts.mastermind.gamePackage.AboutState;
import com.sts.mastermind.gamePackage.GameState;
import com.sts.mastermind.gamePackage.MainMenuState;
import com.sts.mastermind.gamePackage.PlayState;
import com.sts.mastermind.gamePackage.SettingsState;
import com.sts.mastermind.listenerPackage.ChangeState;

public class Main extends ApplicationAdapter implements InputProcessor, ChangeState{

	/**
	 Konstante za delove igre
	 */

	public static final int MAIN_MENU_STATE = 0;
	public static final int SETTINGS_STATE = 1;
	public static final int PLAY_STATE = 2;
	public static final int ABOUT_STATE = 3;


	/**
	 ostale konstante
	 */

	private final int STANDARD_WIDTH = 1080;
	private final int STANDARD_HEIGHT = 1920;

	private final int AMOUNT_OF_STATES = 4;

	private final float R_BG =0.886f;
	private final float G_BG =0.886f;
	private final float B_BG =0.886f;


	private final float alphaRatio = 1.5f;
	/**
	 za pozadinsku animaciju
	 */

	private Texture lineTexture;
	private Image lineImage;


	/**
	 Niz svih delova
	 */

	private GameState[] stateOfGame;


	/**
	 pokazivac na trenutno stanje
	 */

	private int currentState;

	private int nextState;


	/**
	 vidljivost igre
	 */

	private float alpha;


	/**
	 glavni batch za crtanje
	 */

	private SpriteBatch batch;

	/**
	 bundle sa podacima
	 */

	private DataBundle bundle;

	/**
	 * muzika
	 */

	private Music bgMusic;

	private Sound clickSound;

	/**
	 scale slika
	 */

	private float scaleX;
	private float scaleY;

	private int width;
	private int height;

	private boolean ready;


	public Main(DataBundle bundle){
		this.bundle = bundle;
	}

	@Override
	public void create(){
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		scaleX = (float)width / (float)STANDARD_WIDTH;
		scaleY = (float)height / (float)STANDARD_HEIGHT;

		batch = new SpriteBatch();

		alpha = 1f;

		currentState = MAIN_MENU_STATE;

		nextState = MAIN_MENU_STATE;



		stateOfGame = new GameState[AMOUNT_OF_STATES];

		stateOfGame[MAIN_MENU_STATE] = new MainMenuState(
				bundle,
				scaleX,
				scaleY,
				width,
				height
		);


		stateOfGame[MAIN_MENU_STATE].initTextures();
		stateOfGame[MAIN_MENU_STATE].init();

		stateOfGame[MAIN_MENU_STATE].setChangeListener(this);


		stateOfGame[PLAY_STATE] = new PlayState(
				bundle,
				scaleX,
				scaleY,
				width,
				height
		);

		stateOfGame[PLAY_STATE].initTextures();

		stateOfGame[PLAY_STATE].setChangeListener(this);


		stateOfGame[SETTINGS_STATE] = new SettingsState(
				bundle,
				scaleX,
				scaleY,
				width,
				height
		);

		stateOfGame[SETTINGS_STATE].initTextures();

		stateOfGame[SETTINGS_STATE].setChangeListener(this);


		stateOfGame[ABOUT_STATE] = new AboutState(
				bundle,
				scaleX,
				scaleY,
				width,
				height
		);

		stateOfGame[ABOUT_STATE].initTextures();

		stateOfGame[ABOUT_STATE].setChangeListener(this);

		ready = true;

		initTextures();

		lineImage = new Image(lineTexture);
		lineImage.setScale(scaleX, scaleY);
		lineImage.setX(-1108*scaleX);
		lineImage.setY(-scaleY*lineImage.getHeight());

		bgMusic = Gdx.audio.newMusic(Gdx.files.internal("Background Music.mp3"));
		bgMusic.setLooping(true);
		if(bundle.isSounds()){
			bgMusic.play();
		}

		clickSound = Gdx.audio.newSound(Gdx.files.internal("Button Click.mp3"));

		Gdx.input.setInputProcessor(this);

		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void render(){

		Gdx.gl.glClearColor(R_BG,
				G_BG,
				B_BG,
				1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		float delta = Gdx.graphics.getDeltaTime(); // 0.016

		if(nextState != currentState && alpha > 0){
			alpha -= delta*alphaRatio;
			if(alpha < 0){
				alpha = 0;
			}
			if(alpha == 0 && ready){
				currentState = nextState;
			}
		}

		if(nextState == currentState && alpha < 1){
			alpha += delta*alphaRatio;
			if(alpha > 1){
				alpha = 1f;
			}
		}

		lineImage.moveBy(0, 200*delta);

		if(lineImage.getY() > height){
			lineImage.setY(-scaleY*lineImage.getHeight());
		}

		stateOfGame[currentState].update(delta);

		batch.begin();

		lineImage.draw(batch, 1);

		stateOfGame[currentState].render(batch, alpha);

		batch.end();

	}

	@Override
	public void dispose(){
		disposeTextures();
		stateOfGame[MAIN_MENU_STATE].dispose();
		stateOfGame[PLAY_STATE].dispose();
		stateOfGame[SETTINGS_STATE].dispose();
		stateOfGame[ABOUT_STATE].dispose();

		batch.dispose();
		bgMusic.stop();
		bgMusic.dispose();
		clickSound.stop();
		clickSound.dispose();
	}

	private void initTextures(){
		lineTexture = new Texture("line.png");
	}

	private void disposeTextures(){
		lineTexture.dispose();
	}

	@Override
	public void changeState(int newState) {
		nextState = newState;
		new Thread(new Runnable() {
			@Override
			public void run() {
				ready = false;
				stateOfGame[nextState].init();
				ready = true;
			}
		}).start();
	}

	@Override
	public void playMusic() {
		bgMusic.play();
	}

	@Override
	public void stopMusic() {
		bgMusic.stop();
	}

	@Override
	public void playSound(){
		if(bundle.isSounds()){
			clickSound.play();
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.BACK){
			stateOfGame[currentState].backPressed();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int y = height - screenY;

		stateOfGame[currentState].touchDown(screenX,y);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		int y = height - screenY;

		stateOfGame[currentState].touchUp(screenX,y);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		int y = height - screenY;

		stateOfGame[currentState].touchDown(screenX,y);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
