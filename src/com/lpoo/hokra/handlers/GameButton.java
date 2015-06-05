package com.lpoo.hokra.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.lpoo.hokra.entities.B2DSprite;
import com.lpoo.hokra.main.Game;

/**
 * Simple image button.
 */
public class GameButton  {
	
	// center at x, y
	private float x;
	private float y;
	private float width;
	private float height;
	
	private TextureRegion reg;
	
	Vector3 vec;
	private OrthographicCamera cam;
	
	private boolean clicked;
	private String text;
	private TextureRegion[] font;
	
	public GameButton(Texture tex, float x, float y, OrthographicCamera cam) {
		
		//this.tex = tex;
		this.x = x;
		this.y = y;
		this.cam = cam;
		
		width = reg.getRegionWidth();
		height = reg.getRegionHeight();
		vec = new Vector3();
		
		//Texture tex = Game.res.getTexture("hud");
		font = new TextureRegion[11];
		for(int i = 0; i < 6; i++) {
			font[i] = new TextureRegion(tex, 32 + i * 9, 16, 9, 9);
		}
		for(int i = 0; i < 5; i++) {
			font[i + 6] = new TextureRegion(tex, 32 + i * 9, 25, 9, 9);
		}
		
	}
	
	public boolean isClicked() { return clicked; }
	public void setText(String s) { text = s; }
	
	public void update(float dt) {
		
		
		
	}
	
	public void render(SpriteBatch sb) {
		/*
		sb.begin();
		sb.end();
		*/
	}
	
	
}
