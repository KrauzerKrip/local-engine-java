package eng_graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import eng_game_objects.IGraphicObject;
import eng_game_objects.Object;
import eng_graphics.camera.Camera;


public class Transformation {

    private final Matrix4f projectionMatrix;
    
    private final Matrix4f viewMatrix;

    private final Matrix4f modelViewMatrix;
    
    private final Matrix4f orthographicMatrix;
    
    public Transformation() {
        projectionMatrix = new Matrix4f();
        viewMatrix = new Matrix4f();
        modelViewMatrix = new Matrix4f();
        orthographicMatrix = new Matrix4f();
    }
    
    public Matrix4f getOrthographicMatrix(float width, float height, float zNear, float zFar) {
    	float aspectRatio = width / height;
    	return orthographicMatrix.setOrtho(-1.0f * aspectRatio, 1.0f * aspectRatio, -1.0f, 1.0f, zNear, zFar);
    }

    public Matrix4f getProjectionMatrix(float fov, float width, float height, float zNear, float zFar) {
        return projectionMatrix.setPerspective(fov, width / height, zNear, zFar);
    }
    
    
    public Matrix4f getViewMatrix(Camera camera) {
    	
        Vector3f cameraPos = camera.getPosition();
        Vector3f rotation = camera.getRotation();

        viewMatrix.identity();
        
        // First do the rotation so camera rotates over it`s position.
        viewMatrix.rotate( (float)Math.toRadians(rotation.x), new Vector3f(1, 0, 0) )
        	.rotate( (float)Math.toRadians(rotation.y), new Vector3f(0, 1, 0) );
        
        // Then do the translation.
        viewMatrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        
        return viewMatrix;
    }
    
    public Matrix4f getModelViewMatrix(IGraphicObject graphicObject, Matrix4f viewMatrix) {
        
    	Vector3f rotation = graphicObject.getRotation();
    	Vector3f position = graphicObject.getPosition();

        modelViewMatrix.set(viewMatrix).translate(position).
            rotateX((float)Math.toRadians(-rotation.x)).
            rotateY((float)Math.toRadians(-rotation.y)).
            rotateZ((float)Math.toRadians(-rotation.z)).
                scale(graphicObject.getScale());
        
        return modelViewMatrix;
    }
}