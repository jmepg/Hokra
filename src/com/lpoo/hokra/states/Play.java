package com.lpoo.hokra.states;

import static com.lpoo.hokra.handlers.B2DVars.PPM;
import static com.lpoo.hokra.main.Game.V_HEIGHT;
import static com.lpoo.hokra.main.Game.V_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.lpoo.hokra.entities.BackBar;
import com.lpoo.hokra.entities.Background;
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
	
	private Background background;
	private Player player1;
	private Player player2;
	private Square squareP1, squareP2, squareG1, squareG2;
	private Ball ball;
	private BackBar gBar;
	private BackBar pBar;
	
	private boolean debug = true;
	
	public Play(GameStateManager gsm){
		super(gsm);
		world = new World(new Vector2(0, 0f), true); // Gravity
		cl = new MyContactListener();
		world.setContactListener(cl);
		b2dr = new Box2DDebugRenderer();

		//static body - dont move, unaffected by forces (WALL)
		//dynamic body - always get affected by forces (PLAYER)
		//kinematic body - dont get affected by forces 
		
		//Create background
		background = createBackground();
		
		//create player
		player1 = createPlayer(100,100,B2DVars.PURPLE,"purplePlayer");
		player2 = createPlayer(150, 150, B2DVars.GREEN, "greenPlayer");
		
		//create ball
		ball = createBall(10, 10);
		
		//create walls
		createWalls();
		
		//create corners
		squareP1 = createCorner(0,0,B2DVars.PURPLE, "purpleCorner");
		squareP2 = createCorner(V_WIDTH/PPM, V_HEIGHT/PPM, B2DVars.PURPLE, "purpleCorner");
		squareG1 = createCorner(0, V_HEIGHT/PPM, B2DVars.GREEN, "greenCorner");
		squareG2 = createCorner(V_WIDTH/PPM, 0, B2DVars.GREEN, "greenCorner");
		
		//Create Score Bar
		pBar = createBar(140,240, B2DVars.GREEN);
		gBar = createBar(370,240, B2DVars.GREEN);


		
		//set up b2dCam
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false,Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);
	}
	
	@Override
	public void handleInput() {
		float p1Ball, p2Ball;
		
		if(player1.isHoldingBall())
			p1Ball = 1.9f;
		else
			p1Ball = 2.1f;
		
		if(player2.isHoldingBall())
			p2Ball = 1.9f;
		else
			p2Ball = 2.1f;
			
		if(MyInput.isDown(MyInput.W_KEY)){
			player1.getBody().applyForceToCenter(0, p1Ball);
			player1.setDirection(B2DVars.UP);
		}
		if(MyInput.isDown(MyInput.S_KEY)){
			player1.getBody().applyForceToCenter(0, -p1Ball);
			player1.setDirection(B2DVars.DOWN);
		}
		if(MyInput.isDown(MyInput.A_KEY)){
			player1.getBody().applyForceToCenter(-p1Ball, 0);
			player1.setDirection(B2DVars.LEFT);
		}
		if(MyInput.isDown(MyInput.D_KEY)){
			player1.getBody().applyForceToCenter(p1Ball, 0);
			player1.setDirection(B2DVars.RIGHT);
		}

		if(MyInput.isPressed(MyInput.SPACE_KEY)){
			shootBall(player1);
		}
		
		if(MyInput.isDown(MyInput.UP_KEY)){
			player2.getBody().applyForceToCenter(0, p2Ball);
			player2.setDirection(B2DVars.UP);
		}
		if(MyInput.isDown(MyInput.DOWN_KEY)){
			player2.getBody().applyForceToCenter(0, -p2Ball);
			player2.setDirection(B2DVars.DOWN);
		}
		if(MyInput.isDown(MyInput.LEFT_KEY)){
			player2.getBody().applyForceToCenter(-p2Ball, 0);
			player2.setDirection(B2DVars.LEFT);
		}
		if(MyInput.isDown(MyInput.RIGHT_KEY)){
			player2.getBody().applyForceToCenter(p2Ball, 0);
			player2.setDirection(B2DVars.RIGHT);
		}

		if(MyInput.isPressed(MyInput.PLUS_KEY)){
			shootBall(player2);
		}	

	}

	@Override
	public void update(float dt) {
		handleInput();
		world.step(dt, 6, 2);
		updateBall();
		updateballHolder();
		updatePlayerScore();
		updateBar(pBar, player1);
		updateBar(gBar, player2);
		ball.update(dt);
		player1.update(dt);
		player2.update(dt);
		pBar.update(dt);
		gBar.update(dt);
	}


	@Override
	public void render() {	
		/*sb.setProjectionMatrix(cam.combined);
		sb.begin();
		font.draw(sb,"play state",100, 100);
		sb.end();*/
		
		//clear screen
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//draw player
		sb.setProjectionMatrix(cam.combined);
		
		background.render(sb);
		squareP1.render(sb);
		squareP2.render(sb);
		squareG1.render(sb);
		squareG2.render(sb);
		
		pBar.render(sb);
		gBar.render(sb);
		player1.render(sb);
		player2.render(sb);
		if(ball.getBody().isActive())
			ball.render(sb);

		
		//draw screen
		if(debug)
			b2dr.render(world, b2dCam.combined);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	
	
	private void updateballHolder() {

		if(!ball.getBody().isActive()){
			if(cl.isP1HoldingBall())
				player1.setHoldingBall(true);
			if(cl.isP2HoldingBall())
				player2.setHoldingBall(true);
		}
		else{
			cl.setP1HoldingBall(false);
			cl.setP2HoldingBall(false);
		}
		
		
		if(cl.isPlayerColliding()){
			System.out.println("POSICOES IGUAIS");
			if(player1.isHoldingBall()){
				player1.setHoldingBall(false);
				cl.setP1HoldingBall(false);
				player2.setHoldingBall(true);
				cl.setP2HoldingBall(true);

			}
			else if(player2.isHoldingBall()){
				player2.setHoldingBall(false);
				cl.setP2HoldingBall(false);
				player1.setHoldingBall(true);
				cl.setP1HoldingBall(true);
			}
		}
	}

	private void updateBall() {
		if(ball.getBody().isActive()) {
			if(cl.isP1HoldingBall() || cl.isP2HoldingBall()){
				ball.getBody().setUserData(null);
				ball.getBody().destroyFixture(ball.getBody().getFixtureList().get(0));
				/*for(int i =0; i<ball.getSprites().length; i++){
					ball.getSprites()[i].getTexture().dispose();
				}*/
				//ball.getTex().dispose();
				
				world.destroyBody(ball.getBody());

				
			}
		}
	}

	
	public Player createPlayer(float x, float y, int color, String userData){
		
		BodyDef bDef = new BodyDef();
		FixtureDef fDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		Body body = world.createBody(bDef);


		bDef.position.set(x / PPM,y / PPM);
		bDef.type = BodyType.DynamicBody;
		body = world.createBody(bDef);
		body.setLinearDamping(1f);
		shape.setAsBox(5 / PPM,5 / PPM);

		fDef.shape = shape;
		fDef.restitution = 0f;

		fDef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fDef.filter.maskBits = B2DVars.BIT_WALL | B2DVars.BIT_BALL; 
		body.createFixture(fDef).setUserData(userData);
		
		//create sensor
		shape.setAsBox(3/PPM, 3/PPM);
		fDef.shape = shape;
		
		if(color == B2DVars.GREEN){
			System.out.println("Created GPlayer");
			fDef.filter.categoryBits = B2DVars.BIT_GREEN_SENSOR;
			fDef.filter.maskBits = B2DVars.BIT_PURPLE_SENSOR | B2DVars.BIT_CORNER_GREEN; 
		}
		
		if(color == B2DVars.PURPLE){
			fDef.filter.categoryBits = B2DVars.BIT_PURPLE_SENSOR;
			fDef.filter.maskBits = B2DVars.BIT_GREEN_SENSOR | B2DVars.BIT_CORNER_PURPLE;
		}
		
		fDef.isSensor = true;
		body.createFixture(fDef).setUserData(userData+"Sensor");

		Player player = new Player(body, color);
		shape.dispose();
		return player;
	}

	public Square createCorner(float x, float y, int color, String userData){

		BodyDef corner = new BodyDef();
		corner.position.set(x,y);
		corner.type = BodyType.KinematicBody;
		Body cornerB = world.createBody(corner);


		PolygonShape cShape = new PolygonShape();
		FixtureDef cornerDef = new FixtureDef();
		cShape.setAsBox(50/PPM, 50/PPM);

		cornerDef.shape = cShape;

		//	cornerDef.filter.categoryBits = B2DVars.BIT_CORNER;
		//	cornerDef.filter.maskBits = B2DVars.BIT_PLAYER;
		cornerB.createFixture(cornerDef).setUserData("corner");
		
		//create sensor
		cShape.setAsBox(47/PPM, 47/PPM);
		cornerDef.shape = cShape;
		
		if(color == B2DVars.PURPLE){
			cornerDef.filter.categoryBits = B2DVars.BIT_CORNER_PURPLE;
			cornerDef.filter.maskBits = B2DVars.BIT_PURPLE_SENSOR;
		}
		if(color == B2DVars.GREEN){
			System.out.println("Created GCorner");
			cornerDef.filter.categoryBits = B2DVars.BIT_CORNER_GREEN;
			cornerDef.filter.maskBits = B2DVars.BIT_GREEN_SENSOR;
		}
		cornerDef.isSensor = true;
		cornerB.createFixture(cornerDef).setUserData(userData);
		Square sq = new Square(cornerB, color);
		cShape.dispose();
		return sq;
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
		wShape.dispose();
	}
	
	public Ball createBall(float x, float y){
		BodyDef bdef = new BodyDef();
		Body body = world.createBody(bdef);
		FixtureDef fdef = new FixtureDef();
		
		bdef.position.set(x / PPM,y / PPM);
		bdef.type = BodyType.DynamicBody;
		body = world.createBody(bdef);
		
		body.setLinearDamping(0.1f);
		CircleShape cshape = new CircleShape();
		cshape.setRadius(2 / PPM);
		fdef.shape = cshape;
		fdef.restitution = 0f;
		fdef.filter.categoryBits = B2DVars.BIT_BALL;
		fdef.filter.maskBits = B2DVars.BIT_WALL | B2DVars.BIT_PLAYER; 

		body.createFixture(fdef).setUserData("ball");
		
		Ball ball = new Ball(body);
		cshape.dispose();
		return ball;
	}
	
	public void shootBall(Player pl){

		if(pl.isHoldingBall()){
			float x = pl.getBody().getPosition().x*PPM;
			float y = pl.getBody().getPosition().y*PPM;
			
			pl.setHoldingBall(false);
			cl.setP1HoldingBall(false);
			cl.setP2HoldingBall(false);
			
			int direction = pl.getDirection();
			
			/*float angle = player.getBody().getAngle();
			
			if(angle<Math.PI/2 && angle>=0)
				player.setDirection(B2DVars.RIGHT);
			else if(angle<Math.PI && angle>=Math.PI/2)
				player.setDirection(B2DVars.UP);
			else if(angle<3*Math.PI/2 && angle>=Math.PI)
				player.setDirection(B2DVars.LEFT);
			else if(angle>3*Math.PI/2)
				player.setDirection(B2DVars.DOWN);

		*/
			switch(direction){
			case B2DVars.DOWN:
				y-=10;
				ball = createBall(x,y);
				ball.getBody().applyForceToCenter(0, -150f);
				break;
			case B2DVars.UP:
				y+=10;
				ball = createBall(x,y);
				ball.getBody().applyForceToCenter(0, 150f);
				break;
			case B2DVars.LEFT:
				x-=10;
				ball = createBall(x,y);
				ball.getBody().applyForceToCenter(-150f, 0);
				break;
			case B2DVars.RIGHT:
				x+=10;
				ball = createBall(x,y);
				ball.getBody().applyForceToCenter(150f,0);
				break;
			default:
				break;
			}

		}
	}

	public void updatePlayerScore(){
		if(cl.ispInP()){
			if(player1.isHoldingBall()){
				player1.incScore();
				System.out.println("Pl 1: " +player1.getScore());
			}
		}
			if(cl.isgInG()){
				System.out.println("CENAS");
				if(player2.isHoldingBall()){
					player2.incScore();
					System.out.println("Pl 2:" + player2.getScore());
				}
			}
		}
	

	public BackBar createBar(int x, int y, int color){

		BodyDef bdef = new BodyDef();
		Body body = world.createBody(bdef);
		FixtureDef fdef = new FixtureDef();

		bdef.position.set(x / PPM,y / PPM);
		bdef.type = BodyType.StaticBody;
		body = world.createBody(bdef);


		PolygonShape shape = new PolygonShape();
		shape.setAsBox(70/PPM, 10/PPM);
		fdef.shape = shape;


		body.createFixture(fdef).setUserData("backbar");

		BackBar bar = new BackBar(body);
		shape.dispose();
		return bar;

	}
	
	public void updateBar(BackBar bar, Player pl){
		
		Texture tex = bar.getTex();

		int pScore = pl.getScore();
		switch(pScore){
		case 20:
			tex = Game.res.getTexture("backBar1");
			break;
		case 200:
			tex = Game.res.getTexture("backBar2");
			break;
		case 350:
			tex = Game.res.getTexture("backBar3");
			break;
		default: 
			break;	
		
		}
		TextureRegion[] sprites = new TextureRegion[]{new TextureRegion(tex,140,20)};
		
		bar.setTex(tex);
		bar.setSprites(sprites);
		bar.setAnimation(sprites,1/12f);
		
	}
	
	public Background createBackground(){
		BodyDef bdef = new BodyDef();
		Body body = world.createBody(bdef);
		FixtureDef fdef = new FixtureDef();

		bdef.position.set(Game.V_WIDTH / PPM /2,Game.V_HEIGHT / PPM /2);
		bdef.type = BodyType.StaticBody;
		body = world.createBody(bdef);


		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Game.V_WIDTH/PPM, Game.V_HEIGHT/PPM);
		fdef.shape = shape;
		

		body.createFixture(fdef).setUserData("background");
		
		Background screenBackground = new Background(body);
		shape.dispose();
		
		return screenBackground;
	}

}