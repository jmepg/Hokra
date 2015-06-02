package com.lpoo.hokra.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.lpoo.hokra.handlers.GameStateManager;

public class MainFrame  extends GameState{
	//private Table table;
	private BitmapFont font;
	private Texture background;
	private Stage buttonStage;
	private TextButton newGame; 
	private TextButtonStyle newGameStyle;
	private Skin skin;
	private TextureAtlas buttonAtlas;
	
	private  TextButton exit;
	
	public MainFrame(GameStateManager gsm){
		super(gsm);
		
		skin = new Skin();
		background = new Texture(Gdx.files.internal("resources/mainmenu.png"));	
		font = new BitmapFont();
		//newGameStyle.font = font;
		//exit = new TextButton("EXIT", skin);
		
		
		
	} 
	
	
	@Override
	public void handleInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {	
		 Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); //resets screen buffer
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		//sb.draw(background,0,0,Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		sb.draw(background,0,0);
		font.draw(sb,"Main Frame",100, 100);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	


}
