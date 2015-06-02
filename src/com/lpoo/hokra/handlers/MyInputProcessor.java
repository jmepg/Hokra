package com.lpoo.hokra.handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class MyInputProcessor extends InputAdapter{

		public boolean keyDown(int k){
			if(k==Keys.W){
				MyInput.setKey(MyInput.BUTTON1, true);
			}
			else if(k == Keys.S){
				MyInput.setKey(MyInput.BUTTON2, true);
			}
			else if(k== Keys.A){
				MyInput.setKey(MyInput.BUTTON3, true);
				}
			else if(k== Keys.D){
				MyInput.setKey(MyInput.BUTTON4, true);
				}
			return true;
		}
		
		public boolean keyUp(int k){
				if(k==Keys.W){
					MyInput.setKey(MyInput.BUTTON1, false);
				}
				else if(k== Keys.S){
					MyInput.setKey(MyInput.BUTTON2, false);
				}
				else if(k== Keys.A){
					MyInput.setKey(MyInput.BUTTON3, false);
					}
				else if(k== Keys.D){
					MyInput.setKey(MyInput.BUTTON4, false);
				}
				return true;
		}
}
