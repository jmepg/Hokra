package com.lpoo.hokra.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.lpoo.hokra.handlers.B2DVars;
import com.lpoo.hokra.main.Game;

public class Player extends B2DSprite{

	private boolean holdingBall;
	Texture tex;
	int direction;
	
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public Player(Body body, int color){
		super(body);
		
		if(color == B2DVars.PURPLE)
			tex = Game.res.getTexture("purplePlayer");
		if(color == B2DVars.GREEN)
			tex = Game.res.getTexture("greenPlayer");
 
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