package eng_graphics;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glViewport;

import java.util.ArrayList;

import org.joml.Matrix4f;

import eng_file_io.IResources;
import eng_game_objects.IGraphicObject;
import eng_game_objects.IObject;
import eng_graphics.camera.Camera;

public class Render {

    private ShaderHandler shaderHandler;
    private Transformation transformation;
    private Camera camera;
    private IResources resources;
    
    private static final float GL_FOV = (float) Math.toRadians(60.0f); 
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000.0f;
    
    
    public Render(Camera camera, IResources resources) {
    	transformation = new Transformation();
    	this.camera = camera;
    	this.resources = resources;
    }

    public void init(Window window) throws Exception {
        shaderHandler = new ShaderHandler();
        Shader vertexShader = new Shader("vertexShader.vs", resources);
        Shader fragmentShader = new Shader("fragmentShader.fs", resources);
        shaderHandler.createVertexShader(vertexShader.load());
        shaderHandler.createFragmentShader(fragmentShader.load());
        shaderHandler.shaderLink();
        
        // Create shader uniforms for matrixes.
        shaderHandler.shaderCreateUniforms("projectionMatrix");
        shaderHandler.shaderCreateUniforms("modelViewMatrix");
        
        // Create shader uniform for texture.
        shaderHandler.shaderCreateUniforms("textureSampler");
        
        // Create shader uniform for projection matrix.
        shaderHandler.shaderCreateUniforms("projectionMatrix");
 
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Window window, ArrayList<IObject> iObjects) {
        clear();

        if (window.engGlfwWindowIsResized()) {
            glViewport(0, 0, window.width, window.height);
            window.setWindowResized(false);
        }

         shaderHandler.shaderBind();
        
         
        // TODO Maybe optimize this.
        Matrix4f glProjectionMatrix = transformation.getProjectionMatrix(GL_FOV, window.width, window.height, Z_NEAR, Z_FAR);
        
        shaderHandler.shaderSetUniform("projectionMatrix", glProjectionMatrix);
        
        shaderHandler.shaderSetIntUniform("projectionMatrix", 0);
        
        for (IObject iObject : iObjects) {
        	
        	IGraphicObject graphicObject = (IGraphicObject) iObject;
        	
        	Matrix4f glModelViewMatrix = transformation.getModelViewMatrix(graphicObject, transformation.getViewMatrix(camera));
        	
        	shaderHandler.shaderSetUniform("modelViewMatrix", glModelViewMatrix);
        	
        	graphicObject.render();
        }

        shaderHandler.shaderUnbind();
    }

    public void cleanup() {
        if (shaderHandler != null) {
            shaderHandler.shaderCleanup();
        }
    }
}