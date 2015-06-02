package com.lpoo.hokra.handlers;

import java.util.Stack;

import com.lpoo.hokra.main.Game;
import com.lpoo.hokra.states.GameState;
import com.lpoo.hokra.states.MainFrame;
import com.lpoo.hokra.states.Play;

public class GameStateManager {

	private Game game;
	private Stack<GameState> gameStates;
	
	public static final int PLAY = 10000;
	public static final int MAINFRAME = 10032;
	
	public GameStateManager(Game game){
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(PLAY);
	}
	
	public Game game(){
		return game;
	}
	
	public void update(float dt){
		gameStates.peek().update(dt);
	}
	
	public void render(){
		gameStates.peek().render();
	}
	
	private GameState getState(int state){
		if(state == PLAY)
			return new Play(this);
		if(state == MAINFRAME)
			return new MainFrame(this);
		return null;
	}
	public void setState(int state){
		popState();
		pushState(state);
	}
	
	public void pushState(int state){
		gameStates.push(getState(state));
	}
	
	public void popState(){
		GameState g = gameStates.pop();
		g.dispose();
	}
	
}
