package com.lpoo.hokra.entities;

import com.lpoo.hokra.handlers.B2DVars;
import com.lpoo.hokra.main.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;


public class Square extends B2DSprite{
	Texture tex;
	
	public Square(Body body, int color){
		super(body);
	
		if(color == B2DVars.GREEN)
			tex = Game.res.getTexture("greenSquare");
		else if(color == B2DVars.PURPLE)
			tex = Game.res.getTexture("purpleSquare");

			
		TextureRegion[] sprites = TextureRegion.split(tex, 100, 100)[0];
		
		setAnimation(sprites, 1/12f);
	}
}
