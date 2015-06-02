package com.lpoo.hokra.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class hokraDesktop {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = Game.TITLE;
		config.height = Game.V_HEIGHT * Game.SCALE;
		config.width = Game.V_WIDTH * Game.SCALE;
		new LwjglApplication(new Game(), config);
		}
}
