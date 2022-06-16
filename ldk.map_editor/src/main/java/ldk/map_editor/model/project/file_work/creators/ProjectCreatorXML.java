package ldk.map_editor.model.project.file_work.creators;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import ldk.map_editor.model.project.file_work.IProjectCreator;

public class ProjectCreatorXML implements IProjectCreator {

	@Override
	public void create(File file) throws IOException {
		if (file.createNewFile()) {
			
			
		} else {
			throw new FileAlreadyExistsException(file.getAbsolutePath());
		}
		
		
	}

	
}
