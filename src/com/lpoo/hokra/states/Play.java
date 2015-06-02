package com.lpoo.hokra.states;

import static com.lpoo.hokra.handlers.B2DVars.PPM;
import static com.lpoo.hokra.main.Game.V_HEIGHT;
import static com.lpoo.hokra.main.Game.V_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoo.hokra.entities.Ball;
import com.lpoo.hokra.entities.Player;
import com.lpoo.hokra.entities.Square;
import com.lpoo.hokra.handlers.B2DVars;
import com.lpoo.hokra.handlers.GameStateManager;
import com.lpoo.hokra.handlers.MyContactListener;
import com.lpoo.hokra.handlers.MyInput;
import com.lpoo.hokra.main.Game;

public class Play extends GameState {

	//private BitmapFont font = new BitmapFont();
	private World world;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dCam;
	//private Body playerBody;
	private MyContactListener cl;
	
	private Player player;
	private Square squareP1, squareP2, squareG1, squareG2;
	private Ball ball;
	
	public Play(GameStateManager gsm){
		super(gsm);
		world = new World(new Vector2(0, 0f), true); // Gravity
		cl = new MyContactListener();
		world.setContactListener(cl);
		b2dr = new Box2DDebugRenderer();
		
		

		//static body - dont move, unaffected by forces (WALL)
		//dynamic body - always get affected by forces (PLAYER)
		//kinematic body - dont get affected by forces 
		
		
		
	
		//create player
		createPlayer();
		

		//create foot sensor
		/*
		shape.setAsBox(2 / PPM,2 / PPM, new Vector2(0,-5/PPM),0);
		fdef.shape = shape;
		fdef.restitution = 0f;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_GROUND;
		fdef.isSensor = true;
		
		playerBdy.createFixture(fdef).setUserData("foot");
	*/
		
		//create ball
		createBall();
		
		//create walls
		createWalls();
		
		//create squares
		createCorners();
		
		
		//set up b2dCam
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false,Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);
	}
	
	@Override
	public void handleInput() {
		if(MyInput.isDown(MyInput.BUTTON1)){
			System.out.println("pressed w");
			player.getBody().applyForceToCenter(0, 2.5f);	
		}
		else if(MyInput.isDown(MyInput.BUTTON2)){
			System.out.println("pressed s");
			player.getBody().applyForceToCenter(0, -2.5f);
		}
		else if(MyInput.isDown(MyInput.BUTTON3)){
			System.out.println("pressed a");
			player.getBody().applyForceToCenter(-2.5f, 0);
		}
		else if(MyInput.isDown(MyInput.BUTTON4)){
			System.out.println("pressed d");
			player.getBody().applyForceToCenter(2.5f, 0);
		}
	
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		world.step(dt, 6, 2);
		player.update(dt);
	}

	@Override
	public void render() {	
	/*	sb.setProjectionMatrix(cam.combined);
		sb.begin();
		font.draw(sb,"play state",100, 100);
		sb.end();*/
		
		//clear screen
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//draw player
		sb.setProjectionMatrix(cam.combined);
		squareP1.render(sb);
		squareP2.render(sb);
		squareG1.render(sb);
		squareG2.render(sb);
		player.render(sb);


		
		//draw screen
		b2dr.render(world, b2dCam.combined);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	public void createPlayer(){
		
		BodyDef bDef = new BodyDef();
		FixtureDef fDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		Body body = world.createBody(bDef);
		
		
			bDef.position.set(160 / PPM,200 / PPM);
			bDef.type = BodyType.DynamicBody;
			body = world.createBody(bDef);
			body.setLinearDamping(1f);
			shape.setAsBox(5 / PPM,5 / PPM);

			fDef.shape = shape;
			fDef.restitution = 0f;

			fDef.filter.categoryBits = B2DVars.BIT_PLAYER;
			fDef.filter.maskBits = B2DVars.BIT_WALL | B2DVars.BIT_BALL;
			body.createFixture(fDef).setUserData("player");
		
		
		player = new Player(body);
		
		body.setUserData("player");
	}
	
	public void createCorners(){
		BodyDef corner1 = new BodyDef();
		BodyDef corner2 = new BodyDef();
		BodyDef corner3 = new BodyDef();
		BodyDef corner4 = new BodyDef();

		corner1.position.set(0,0);
		corner2.position.set(0,V_HEIGHT/PPM);
		corner3.position.set(V_WIDTH/PPM, V_HEIGHT/PPM);
		corner4.position.set(V_WIDTH/PPM,0);

		corner1.type = BodyType.KinematicBody; 
		corner2.type = BodyType.KinematicBody; 
		corner3.type = BodyType.KinematicBody; 
		corner4.type = BodyType.KinematicBody; 

		Body cornerP1 = world.createBody(corner1);
		Body cornerG1 = world.createBody(corner2);
		Body cornerP2 = world.createBody(corner3);
		Body cornerG2= world.createBody(corner4);

		
		
		PolygonShape cShape = new PolygonShape();
		FixtureDef cornerDef = new FixtureDef();
		cShape.setAsBox(50/PPM, 50/PPM);

		cornerDef.shape = cShape;
		
		cornerP1.createFixture(cornerDef).setUserData("cornerP1");
		cornerP2.createFixture(cornerDef).setUserData("cornerP2");
		cornerG1.createFixture(cornerDef).setUserData("cornerG1");
		cornerG2.createFixture(cornerDef).setUserData("cornerG2");
		
		squareP1 = new Square(cornerP1, B2DVars.PURPLE);
		squareP2 = new Square(cornerP2,B2DVars.PURPLE);
		cornerP1.setUserData("square");
		cornerP2.setUserData("square");
		
		squareG1 = new Square(cornerG1, B2DVars.GREEN);
		squareG2 = new Square(cornerG2,B2DVars.GREEN);
		cornerG1.setUserData("square");
		cornerG2.setUserData("square");

	}
	
	public void createWalls(){

		BodyDef wall = new BodyDef();
		wall.type = BodyType.StaticBody; 
		Body wallB = world.createBody(wall);
		EdgeShape wShape = new EdgeShape();
				
		FixtureDef wallDef = new FixtureDef();
		wallDef.restitution = 0.7f;
		wallDef.shape = wShape;
		wallDef.filter.categoryBits = B2DVars.BIT_WALL;
		
		wShape.set(0, 0, 0, V_HEIGHT/PPM);
		wallB.createFixture(wallDef).setUserData("wall"); //left wall
	
		wShape.set(0, 0, V_WIDTH/PPM, 0);
		wallB.createFixture(wallDef).setUserData("wall"); //down wall
		
		wShape.set(0, V_HEIGHT/PPM, V_WIDTH/PPM, V_HEIGHT/PPM); //up wall
		wallB.createFixture(wallDef).setUserData("wall");
		

		wShape.set(V_WIDTH/PPM, V_HEIGHT/PPM, V_WIDTH/PPM, 0); //right wall
		wallB.createFixture(wallDef).setUserData("wall");
	}
	
	public void createBall(){
		BodyDef bdef = new BodyDef();
		Body body = world.createBody(bdef);
		FixtureDef fdef = new FixtureDef();
		
		bdef.position.set(160 / PPM,200 / PPM);
		bdef.type = BodyType.DynamicBody;
		body = world.createBody(bdef);
		
		bdef.position.set(153 /PPM, 220 / PPM);
		body.setLinearDamping(0.1f);
		CircleShape cshape = new CircleShape();
		cshape.setRadius(2 / PPM);
		fdef.shape = cshape;
		fdef.restitution = 0f;
		fdef.filter.categoryBits = B2DVars.BIT_BALL;
		fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;

		body.createFixture(fdef).setUserData("ball");
		
		ball = new Ball(body);
	}

}
