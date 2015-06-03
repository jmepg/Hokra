package com.lpoo.hokra.handlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

public class MyContactListener implements ContactListener{

	private boolean holdingBall;
	private boolean playerInPurple;
	private boolean playerInGreen;
	
	public boolean isPlayerInPurple() {
		return playerInPurple;
	}

	public void setPlayerInPurple(boolean playerInPurple) {
		this.playerInPurple = playerInPurple;
	}

	public boolean isPlayerInGreen() {
		return playerInGreen;
	}

	public void setPlayerinGreen(boolean playerInGreen) {
		this.playerInGreen = playerInGreen;
	}

	public void setHoldingBall(boolean holdingBall) {
		this.holdingBall = holdingBall;
	}

	public MyContactListener(){

	}

	//called when 2 pictures overlap each other
	public void beginContact(Contact c){
		//System.out.println("Begin Contact");

		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();

		if(fa == null || fb == null) return;

		if(fa.getUserData() != null && fa.getUserData().equals("purplePlayer")){
			if(fb.getUserData().equals("ball")){
				holdingBall = true;
			}
			if(fb.getUserData().equals("greenCorner")){
				playerInGreen = true;
			}
			if(fb.getUserData().equals("purpleCorner")){
				playerInPurple = true;
			}
		}
		if(fb.getUserData() != null && fb.getUserData().equals("purplePlayer")){
			if(fa.getUserData().equals("ball")){
				holdingBall = true;
			}
			if(fa.getUserData().equals("greenCorner")){
				playerInGreen = true;
			}
			if(fa.getUserData().equals("purpleCorner")){
				playerInPurple = true;
			}

		}
		
		if(fa.getUserData() != null && fa.getUserData().equals("greenPlayer")){
			if(fb.getUserData().equals("ball")){
				holdingBall = true;
			}
			if(fb.getUserData().equals("greenCorner")){
				playerInGreen = true;
			}
			if(fb.getUserData().equals("purpleCorner")){
				playerInPurple = true;
			}
		}
		if(fb.getUserData() != null && fb.getUserData().equals("greenPlayer")){
			if(fb.getUserData().equals("ball")){
				holdingBall = true;
			}
			if(fa.getUserData().equals("greenCorner")){
				playerInGreen = true;
			}
			if(fa.getUserData().equals("purpleCorner")){
				playerInPurple = true;
			}
		}
	}


	public void endContact(Contact c){
		//System.out.println("End Contact");
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();

		if(fa == null || fb == null) return;

		if(fa.getUserData() != null && fa.getUserData().equals("player")){
			if(fb.getUserData().equals("ball")){
				holdingBall = false;
			}
			if(fb.getUserData().equals("greenCorner")){
				playerInGreen = false;
			}
			if(fb.getUserData().equals("purpleCorner")){
				playerInPurple = false;
			}
		}
		if(fb.getUserData() != null && fb.getUserData().equals("player")){
			if(fa.getUserData().equals("ball")){
				holdingBall = false;
			}
			if(fa.getUserData().equals("greenCorner")){
				playerInGreen = false;
			}
			if(fa.getUserData().equals("purpleCorner")){
				playerInPurple = false;
			}
		}

	}

	
	
	public boolean  isHoldingBall(){
		return holdingBall;
	}
	
	//collision detection
	public void preSolve(Contact c, Manifold m){
	
	}
	
	//collision handling
	public void postSolve(Contact c, ContactImpulse c1){
	
	}
	
	


	

}
