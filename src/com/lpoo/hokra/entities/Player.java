package com.lpoo.hokra.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.lpoo.hokra.main.Game;

public class Player extends B2DSprite{

	private boolean holdingBall;
	
	public Player(Body body){
		super(body);
		
		Texture tex = Game.res.getTexture("purplePlayer");
		TextureRegion[] sprites = TextureRegion.split(tex, 10, 10)[0];
		
		setAnimation(sprites,1/12f);
	}
	public boolean isHoldingBall() {
		return holdingBall;
	}
	public void setHoldingBall(boolean holdingBall) {
		this.holdingBall = holdingBall;
	}
	
}