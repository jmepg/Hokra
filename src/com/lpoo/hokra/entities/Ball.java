package com.lpoo.hokra.entities;

import com.lpoo.hokra.handlers.B2DVars;
import com.lpoo.hokra.main.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;


public class Ball extends B2DSprite{
	
	
	public Ball(Body body){
		super(body);
		
		Texture tex = Game.res.getTexture("ball");
		TextureRegion[] sprites = TextureRegion.split(tex, 5, 5)[0];

		setAnimation(sprites, 1/12f);
}

}
