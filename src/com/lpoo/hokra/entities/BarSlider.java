package com.lpoo.hokra.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.lpoo.hokra.main.Game;

public class BarSlider extends B2DSprite{
	

		private Texture tex;
		private TextureRegion[] sprites;
		private int state;

		public BarSlider(Body body){
			super(body);
			
			
			tex = Game.res.getTexture("backBar");
	 
			sprites = new TextureRegion[]{new TextureRegion(tex)};
			
			setAnimation(sprites,1/12f);
		}
		
		public Texture getTex() {
			return tex;
		}

		public void setTex(Texture tex) {
			this.tex = tex;
		}

		public TextureRegion[] getSprites() {
			return sprites;
		}

		public void setSprites(TextureRegion[] sprites) {
			this.sprites = sprites;
		}
		
		public int getState() {
			return state;
		}
		
		public void setState(int state) {
			this.state = state;
		}
	}
}
