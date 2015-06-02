package com.lpoo.hokra.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;

public class MyContactListener implements ContactListener{

	boolean holdingBall;
	
	public MyContactListener(){
		
	}
	
	//called when 2 pictures overlap each other
	public void beginContact(Contact c){
		//System.out.println("Begin Contact");
		
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();
			
		if(fa == null || fb == null) return;
		
		if(fa.getUserData() != null && fa.getUserData().equals("player") 
				&&  fb.getUserData() != null && fb.getUserData().equals("ball")){
			holdingBall = true;
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("player") 
				&&  fa.getUserData() != null && fa.getUserData().equals("ball")){
			holdingBall = true;
		}
		
			
		//System.out.println(fa.getUserData()+", "+fb.getUserData());
	}
	
	public void endContact(Contact c){
		//System.out.println("End Contact");
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();
		
		
		if(fa.getUserData() != null && fa.getUserData().equals("player") 
				&&  fb.getUserData() != null && fb.getUserData().equals("ball")){
			holdingBall = false;
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("player") 
				&&  fa.getUserData() != null && fa.getUserData().equals("ball")){
			holdingBall = false;
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
	
	public void playerHasBall(){
		if(!isHoldingBall())
			return;
		
	}

	

}
