package eng_file_io;

import java.io.InputStream;

/**
 * 
 * @author User
 *
 */
public interface IResources {
	public ObjectStreams getObject(String objectName) throws NullPointerException;
	
	public InputStream getParametersFile(String parametersName) throws NullPointerException;
	
	public InputStream getSceneFile(String sceneName) throws NullPointerException;
	
	public InputStream getVertexShaderStream(String shaderFileName) throws NullPointerException;
	
	public InputStream getContentFileStream(String contentFileName) throws NullPointerException;
}
