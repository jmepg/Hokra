package com.lpoo.hokra.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class MyInputProcessor extends InputAdapter{

		public boolean keyDown(int k){
			if(k==Keys.W){
				MyInput.setKey(MyInput.W_KEY, true);
			}
			else if(k == Keys.S){
				MyInput.setKey(MyInput.S_KEY, true);
			}
			else if(k== Keys.A){
				MyInput.setKey(MyInput.A_KEY, true);
			}
			else if(k== Keys.D){
				MyInput.setKey(MyInput.D_KEY, true);
			}
			else if(k==Keys.SPACE){
				MyInput.setKey(MyInput.SPACE_KEY, true);
			}
			else if(k==Keys.UP){
				MyInput.setKey(MyInput.UP_KEY, true);
			}
			else if(k==Keys.DOWN){
				MyInput.setKey(MyInput.DOWN_KEY, true);
			}
			else if(k==Keys.LEFT){
				MyInput.setKey(MyInput.LEFT_KEY, true);
			}
			else if(k==Keys.RIGHT){
				MyInput.setKey(MyInput.RIGHT_KEY, true);
			}
			else if(k==Keys.PLUS){
				MyInput.setKey(MyInput.PLUS_KEY, true);
			}
			return true;
		}

		public boolean keyUp(int k){
			if(k==Keys.W){
				MyInput.setKey(MyInput.W_KEY, false);
			}
			else if(k== Keys.S){
				MyInput.setKey(MyInput.S_KEY, false);
			}
			else if(k== Keys.A){
				MyInput.setKey(MyInput.A_KEY, false);
			}
			else if(k== Keys.D){
				MyInput.setKey(MyInput.D_KEY, false);
			}
			else if(k==Keys.SPACE){
				MyInput.setKey(MyInput.SPACE_KEY, false);
			}
			else if(k==Keys.UP){
				MyInput.setKey(MyInput.UP_KEY, false);
			}
			else if(k==Keys.DOWN){
				MyInput.setKey(MyInput.DOWN_KEY, false);
			}
			else if(k==Keys.LEFT){
				MyInput.setKey(MyInput.LEFT_KEY, false);
			}
			else if(k==Keys.RIGHT){
				MyInput.setKey(MyInput.RIGHT_KEY, false);
			}
			else if(k==Keys.PLUS){
				MyInput.setKey(MyInput.PLUS_KEY, false);
			}
				return true;
		}
		
		  public boolean TouchDown(int x, int y, int pointer, int button) {
              if (button == Input.Buttons.LEFT) {
                  onMouseDown();
                  return true;
              }
              return false;
          }

		private void onMouseDown() {
			
		}

      
}
