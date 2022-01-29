package eng_graphics;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_MAXIMIZED;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_SCALE_TO_MONITOR;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class Window {
	
    private final String title;

    protected int width;

    protected int height;

    private long glfwWindow;

    private boolean resized;

    protected boolean vSync;
    
    private boolean constAspectRatio;
    
    private double glfwWheelOffsetX;
    private double glfwWheelOffsetY;
    
    protected Window(String title, int width, int height, boolean vSync) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vSync;
        this.resized = false;
        this.constAspectRatio = true;
    }


	protected void init() {
		GLFWErrorCallback.createPrint(System.err).set();

		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
		glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);
		glfwWindowHint(GLFW_SCALE_TO_MONITOR, GLFW_FALSE);


		// Create the window
		glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
		if ( glfwWindow == NULL ) {
			throw new RuntimeException("Failed to create the GLFW window");
		}
		
		
		
        // Setup resize callback
        glfwSetFramebufferSizeCallback(glfwWindow, (window, width, height) -> {
            this.width = width;
            this.height = this.constAspectRatio ? Math.round(width / 1.7777777777778f) : height;
            glfwSetWindowSize(glfwWindow, this.width, this.height);
            this.setWindowResized(true);
        });


		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(glfwWindow, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});
		
		glfwWheelOffsetX = 0.0f;
		glfwWheelOffsetY = 0.0f;

		
		glfwSetScrollCallback(glfwWindow, (window, offsetX, offsetY) -> {		
				glfwWheelOffsetX = offsetX;
				glfwWheelOffsetY = offsetY;
			});
		
	

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			glfwSetWindowPos(
				glfwWindow,
                (vidmode.width() - width) / 2,
                (vidmode.height() - height) / 2
			);

		glfwMakeContextCurrent(glfwWindow);
			
		//vSync
		if (vSync) {
			glfwSwapInterval(1);
		}
		
		glfwShowWindow(glfwWindow);
		
		GL.createCapabilities();
		
		glEnable(GL_DEPTH_TEST);
		
		// Blend
	    glEnable(GL_BLEND);
	    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		System.out.printf("Window: initialized \nV-Sync: %b \n", vSync);
	}

	protected void update() {
		// Using in GlfwController.render().
			glfwSwapBuffers(glfwWindow); // swap the color buffers
			glfwPollEvents();
		}
	    
	protected boolean windowShouldClose() {
	        return glfwWindowShouldClose(glfwWindow);
	}
	

	public double engGlfwGetWheelOffsetX() {
		return glfwWheelOffsetX;
	}
	
	public double engGlfwGetWheelOffsetY() {
		return glfwWheelOffsetY;
	}
	
	protected void setWindowResized(boolean value) {
		this.resized = value;
	}
	
    public void engGlSetClearColor(float r, float g, float b, float alpha) {
        glClearColor(r, g, b, alpha);
    }
    
    public boolean engGlfwIsKeyPressed(int keyCode) {
        return glfwGetKey(glfwWindow, keyCode) == GLFW_PRESS;
    }
    
    public void engGlfwInputSetMode(int inputMode, int modeValue) {
		glfwSetInputMode(glfwWindow, inputMode, modeValue);
    }
    
    public boolean engGlfwWindowIsResized() {
    	return resized;
    }
    
    public void engGlfwWindowConstAspectRatio(boolean value) {
    	this.constAspectRatio = value;
    }
}