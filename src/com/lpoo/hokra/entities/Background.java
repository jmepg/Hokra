package com.lpoo.hokra.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.lpoo.hokra.main.Game;

public class Background extends B2DSprite{
	private Texture tex;
	private TextureRegion[] sprites;
	private int state;

	public Background(Body body){
		super(body);
		
		
		tex = Game.res.getTexture("background");
 
		sprites = new TextureRegion[]{new TextureRegion(tex)};
		
		setAnimation(sprites,1/12f);
	}
}
