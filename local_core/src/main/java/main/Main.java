package main;

import eng_graphics.Loop;
import game.Game;
import eng_graphics.IGameLogic;
 
public class Main {
 
    public static void main(String[] args) {                                                                                       
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new Game();
            Loop loop = Loop.createInstance("Local`", 1280, 720, vSync, gameLogic);
            loop.run();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
    

	// ctrl f11
	// shift alt right left / up down
	// ctrl d delete line
} 