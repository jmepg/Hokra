package com.lpoo.hokra.handlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

public class MyContactListener implements ContactListener{

	private boolean p1HoldingBall;
	private boolean p2HoldingBall;
	private boolean playerInPurple;
	private boolean playerInGreen;
	private boolean playerColliding;
	 
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
				setP1HoldingBall(true);
			}
		
		}
		if(fb.getUserData() != null && fb.getUserData().equals("purplePlayer")){
			if(fa.getUserData().equals("ball")){
				setP1HoldingBall(true);
				
			}
	
		}
		
		if(fa.getUserData() != null && fa.getUserData().equals("greenPlayer")){
			if(fb.getUserData().equals("ball")){
				setP2HoldingBall(true);
			}
			
		}
		if(fb.getUserData() != null && fb.getUserData().equals("greenPlayer")){
			if(fb.getUserData().equals("ball")){
				setP2HoldingBall(true);
			}
		
		}
		if(fa.getUserData() != null && fa.getUserData().equals("playerSensor") 
				&& fb.getUserData() != null && fb.getUserData().equals("playerSensor")){
			playerColliding = true;
		}

		if(fa.getUserData() != null && fa.getUserData().equals("playerSensor")){
			if(fb.getUserData() != null && fb.getUserData().equals("greenCorner")){
				playerInGreen = true;
			}
			if(fb.getUserData() != null && fb.getUserData().equals("purpleCorner")){
				playerInPurple = true;
			}
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("playerSensor")){
			if(fa.getUserData() != null && fa.getUserData().equals("greenCorner")){
				playerInGreen = true;
			}
			if(fa.getUserData() != null && fa.getUserData().equals("purpleCorner")){
				playerInPurple = true;
			}
		}
	}


	public void endContact(Contact c){
		//System.out.println("End Contact");
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();

		if(fa == null || fb == null) return;

		if(fa.getUserData() != null && fa.getUserData().equals("purplePlayer")){
			if(fb.getUserData().equals("ball")){
				setP1HoldingBall(false);
			}
		}
		if(fb.getUserData() != null && fb.getUserData().equals("purplePlayer")){
			if(fa.getUserData().equals("ball")){
				setP1HoldingBall(false);
			}
		}
		if(fa.getUserData() != null && fa.getUserData().equals("greenPlayer")){
			if(fb.getUserData().equals("ball")){
				setP2HoldingBall(false);
			}
		}
		if(fb.getUserData() != null && fb.getUserData().equals("greenPlayer")){
			if(fa.getUserData().equals("ball")){
				setP2HoldingBall(false);
			}
		}
		
		if(fa.getUserData() != null && fa.getUserData().equals("playerSensor") 
				&& fb.getUserData() != null && fb.getUserData().equals("playerSensor")){
			playerColliding = false;
		}
		
		if(fa.getUserData() != null && fa.getUserData().equals("playerSensor")){
			if(fb.getUserData() != null && fb.getUserData().equals("greenCorner")){
				playerInGreen = false;
			}
			if(fb.getUserData() != null && fb.getUserData().equals("purpleCorner")){
				playerInPurple = false;
			}
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("playerSensor")){
			if(fa.getUserData() != null && fa.getUserData().equals("greenCorner")){
				playerInGreen = false;
			}
			if(fa.getUserData() != null && fa.getUserData().equals("purpleCorner")){
				playerInPurple = false;
			}
		}
	}

	
	
	
	
	//collision detection
	public void preSolve(Contact c, Manifold m){
	
	}
	
	//collision handling
	public void postSolve(Contact c, ContactImpulse c1){
	
	}

	public boolean isP2HoldingBall() {
		return p2HoldingBall;
	}

	public void setP2HoldingBall(boolean p2HoldingBall) {
		this.p2HoldingBall = p2HoldingBall;
	}

	public boolean isP1HoldingBall() {
		return p1HoldingBall;
	}

	public void setP1HoldingBall(boolean p1HoldingBall) {
		this.p1HoldingBall = p1HoldingBall;
	}

	public boolean isPlayerColliding() {
		return playerColliding;
	}

	public void setPlayerColliding(boolean playerColliding) {
		this.playerColliding = playerColliding;
	}


	
	


	

}
