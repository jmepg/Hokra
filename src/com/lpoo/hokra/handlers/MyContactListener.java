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

	private boolean playerColliding;
	private boolean pInP;
	private boolean gInG;
	
	
	

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
			if(fa.getUserData().equals("ball")){
				setP2HoldingBall(true);
			}
		}
		
		//player collision
		if((fa.getUserData() != null && fa.getUserData().equals("greenPlayerSensor") 
				&& fb.getUserData() != null && fb.getUserData().equals("purplePlayerSensor"))
				|| (fb.getUserData() != null && fb.getUserData().equals("greenPlayerSensor") 
						&& fa.getUserData() != null && fa.getUserData().equals("purplePlayerSensor"))){
			setPlayerColliding(true);
		}

		if(fa.getUserData() != null && fa.getUserData().equals("greenPlayerSensor")){
			if(fb.getUserData() != null && fb.getUserData().equals("greenCorner")){
				setgInG(true);
			}
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("greenPlayerSensor")){
			if(fa.getUserData() != null && fa.getUserData().equals("greenCorner")){
				setgInG(true);
			}
		}
		
		if(fa.getUserData() != null && fa.getUserData().equals("purplePlayerSensor")){
			if(fb.getUserData() != null && fb.getUserData().equals("purpleCorner")){
				setpInP(true);
			}
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("purplePlayerSensor")){
			if(fa.getUserData() != null && fa.getUserData().equals("purpleCorner")){
				setpInP(true);
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
		
		//player collision
				if((fa.getUserData() != null && fa.getUserData().equals("greenPlayerSensor") 
						&& fb.getUserData() != null && fb.getUserData().equals("purplePlayerSensor"))
						|(fb.getUserData() != null && fb.getUserData().equals("greenPlayerSensor") 
								&& fa.getUserData() != null && fa.getUserData().equals("purplePlayerSensor"))){
					playerColliding = false;
				}

				if(fa.getUserData() != null && fa.getUserData().equals("greenPlayerSensor")){
					if(fb.getUserData() != null && fb.getUserData().equals("greenCorner")){
						setgInG(false);
					}
				}
				
				if(fb.getUserData() != null && fb.getUserData().equals("greenPlayerSensor")){
					if(fa.getUserData() != null && fa.getUserData().equals("greenCorner")){
						setgInG(false);
					}
				}
				if(fa.getUserData() != null && fa.getUserData().equals("purplePlayerSensor")){
					if(fb.getUserData() != null && fb.getUserData().equals("purpleCorner")){
						setpInP(false);
					}
				}
				
				if(fb.getUserData() != null && fb.getUserData().equals("purplePlayerSensor")){
					if(fa.getUserData() != null && fa.getUserData().equals("purpleCorner")){
						setpInP(false);
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

	public boolean ispInP() {
		return pInP;
	}

	public void setpInP(boolean pInP) {
		this.pInP = pInP;
	}

	public boolean isgInG() {
		return gInG;
	}

	public void setgInG(boolean gInG) {
		System.out.println("TRUE");
		this.gInG = gInG;
	}


	
	


	

}
