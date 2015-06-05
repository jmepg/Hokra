package com.lpoo.hokra.entities;

import com.lpoo.hokra.handlers.B2DVars;
import com.lpoo.hokra.main.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Ball extends B2DSprite{

	private TextureRegion[] sprites;
	private Texture tex;
	
	public Ball(Body body){
		super(body);

		tex = Game.res.getTexture("ball");
		sprites = TextureRegion.split(tex, 5, 5)[0];

		setAnimation(sprites, 1/12f);
	}
	public TextureRegion[] getSprites() {
		return sprites;
	}
	public void setSprites(TextureRegion[] sprites) {
		this.sprites = sprites;
	}
	public Texture getTex() {
		return tex;
	}
	public void setTex(Texture tex) {
		this.tex = tex;
	}
	

}
