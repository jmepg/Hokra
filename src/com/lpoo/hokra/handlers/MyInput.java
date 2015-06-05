package com.lpoo.hokra.handlers;

public class MyInput {

		public static boolean[] keys;
		public static boolean[] pkeys;
		public static float x;
		public static float y;
		
		public static final int NUM_KEYS = 10;
		public static final int W_KEY = 0;
		public static final int S_KEY = 1;
		public static final int A_KEY = 2;
		public static final int D_KEY = 3;
		public static final int SPACE_KEY = 4;
		
		public static final int UP_KEY = 5;
		public static final int DOWN_KEY = 6;
		public static final int LEFT_KEY = 7;
		public static final int RIGHT_KEY = 8;
		public static final int PLUS_KEY = 9;
		
		public static final int MOUSE_LEFT = 100;

		
		static{
			keys = new boolean[NUM_KEYS] ;
			pkeys = new boolean[NUM_KEYS];
			}
		
		public static void update(){
			for(int i=0; i<NUM_KEYS; i++){
				pkeys[i] = keys[i]; // update previous state
			}
		}
		
		public static void setKey(int i, boolean b){
			keys[i]=b;
		}
		
		public static boolean isDown(int i){
			return keys[i];
		}
		
		public static boolean isPressed(int i){
			return keys[i] && !pkeys[i];
		}
}
