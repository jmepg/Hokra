package com.lpoo.hokra.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoo.hokra.handlers.Content;
import com.lpoo.hokra.handlers.GameStateManager;
import com.lpoo.hokra.handlers.MyInput;
import com.lpoo.hokra.handlers.MyInputProcessor;


public class Game implements ApplicationListener{
	public static final String TITLE = "HOKRA";
	public static final int V_WIDTH = 512;
	public static final int V_HEIGHT = 256;
	public static final int SCALE = 2;
	public static final float STEP = 1/60f;
			
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	private float accum;
	private GameStateManager gsm;

	public static Content res;
	
	
	@Override
	public void create() {
		
		Texture.setEnforcePotImages(false);//Allows to use images other than powers of two
		
		Gdx.input.setInputProcessor(new MyInputProcessor());
		
		res = new Content();
		
		loadTextures(res);
		
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		gsm = new GameStateManager(this);
	}

	public void loadTextures(Content res) {
		res.loadTexture("resources/purple.png", "purpleSquare");
		res.loadTexture("resources/green.png", "greenSquare");
		res.loadTexture("resources/purplePlayer.png", "purplePlayer");
		res.loadTexture("resources/greenPlayer.png", "greenPlayer");
		res.loadTexture("resources/purplePlayerBall.png", "purplePlayerBall");		
		res.loadTexture("resources/ball.png", "ball");		
		res.loadTexture("resources/playButton.png", "playButton");		
		res.loadTexture("resources/backBar.png", "backBar");		
		res.loadTexture("resources/backBar1.png", "backBar1");	
		res.loadTexture("resources/backBar2.png", "backBar2");	
		res.loadTexture("resources/backBar3.png", "backBar3");		
		res.loadTexture("resources/background.png", "background");		



	}

	@Override
	public void render() {
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP){
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
			MyInput.update();
			
		}
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int w, int h) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}


	public SpriteBatch getSb() {
		return sb;
	}

	public void setSb(SpriteBatch sb) {
		this.sb = sb;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public void setCam(OrthographicCamera cam) {
		this.cam = cam;
	}

	public OrthographicCamera getHudCam() {
		return hudCam;
	}

	public void setHudCam(OrthographicCamera hudCam) {
		this.hudCam = hudCam;
	}

}
