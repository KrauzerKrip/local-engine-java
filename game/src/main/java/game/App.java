package game;

import eng_file_io.IResources;
import eng_graphics.IGameLogic;
import eng_graphics.Loop;

public class App {
    public static void main(String[] args) {                                                                                       
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new Game();
            IResources iResources = new Resource();
            Loop loop = Loop.createInstance("Local`", 1280, 720, vSync, gameLogic, iResources);
            loop.run();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}
