package eng_graphics;

import eng_file_io.IResources;
import eng_procedures.parameters_init.ConsoleParametersParametersInit;
import eng_procedures.parameters_init.GraphicsParametersInit;
import eng_procedures.parameters_init.ObjectsParametersInit;
import eng_procedures.parameters_init.SceneParametersInit;
import eng_stuff.TimeController;

public class Loop implements Runnable {
	
	private static Loop instance;

    private int targetFPS = 75;

    private int targetUPS = 30;
    
    private int FPS = 0;
    private int UPS = 0;

    private final Window window;

    private final TimeController timecontroller;

    private final IGameLogic gameLogic;
    
    private float elapsedTime;
    
    private IResources iResources;
    
    // TODO Move Loop class in another place (because it isn`t only for graphics).
    private Loop(String windowTitle, int width, int height, boolean vSync, IGameLogic gameLogic, IResources iResources) throws Exception {
        window = new Window(windowTitle, width, height, vSync);
        this.gameLogic = gameLogic;
        timecontroller = new TimeController();
        this.iResources = iResources;
    }
    
    public static Loop createInstance(String windowTitle, int width, int height, boolean vSync, IGameLogic gameLogic, IResources iResources) throws Exception {
    	if (instance == null) {
    		instance = new Loop(windowTitle, width, height, vSync, gameLogic, iResources);
    		return instance;
    	}
    	else {
    		throw new Exception("Loop: Instance of GlfwController has already been created.");
    	}
    }
    
    public static Loop getInstance() throws Exception{
    	if (instance != null) {
    		return instance;
    	}
    	else {
    		throw new Exception("Loop: Instance of GlfwController hasn`t been created.");
    	}
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception excp) {  
            excp.printStackTrace();
        } finally {
        	cleanup();
        }
    }

    protected void init() throws Exception {
        window.init();
        timecontroller.init();
        
		new SceneParametersInit(iResources).initParameters(); // вот сюда вот тянуть
		new ObjectsParametersInit(iResources).initParameters();
		new GraphicsParametersInit(iResources).initParameters();
		new ConsoleParametersParametersInit(iResources).initParameters();
		
        gameLogic.init(window);
    }

    protected void gameLoop() {
        float accumulator = 0f;
        float interval = 1f / targetUPS; 

        boolean running = true;
        while (running && !window.windowShouldClose()) {
            elapsedTime = timecontroller.getElapsedTime();
            accumulator += elapsedTime;

            input();

            while (accumulator >= interval) {
                update(interval); // Maybe delete argument "interval".
                accumulator -= interval;
            }

            render();
            
            FPS = (int) (1000 / elapsedTime);
            //System.out.println(FPS);

            if (!window.vSync) {
                sync();
            }
        }
    }

    private void sync() {
        float loopSlot = 1f / targetFPS;
        double endTime = timecontroller.getLastLoopTime() + loopSlot;
        while (timecontroller.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException IntrptdExcp) {
            }
        }
    }

    protected void input() {
        gameLogic.input(window);
    } 
    
    // Graphics update method. Connected to the IGraphicsLogic interface.
    protected void update(float interval) {
        gameLogic.update(interval);
    }

    protected void render() {
        gameLogic.render(window);
        window.update();
    }
    
    protected void cleanup() {
    	gameLogic.cleanup();
    }
    
    public float engGetElapsedTime() {
    	return elapsedTime;
    }
    
    public int getFPS() {
    	return FPS;
    }

	/**
	 * @return the targetFPS
	 */
	public int getTargetFPS() {
		return targetFPS;
	}

	/**
	 * @param targetFPS the targetFPS to set
	 */
	public void setTargetFPS(int targetFPS) {
		this.targetFPS = targetFPS;
	}

	/**
	 * @return the targetUPS
	 */
	public int getTargetUPS() {
		return targetUPS;
	}

	/**
	 * @param targetUPS the targetUPS to set
	 */
	public void setTargetUPS(int targetUPS) {
		this.targetUPS = targetUPS;
	}
    
}