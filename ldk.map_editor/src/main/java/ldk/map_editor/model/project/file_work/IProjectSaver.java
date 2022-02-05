package ldk.map_editor.model.project.file_work;

import java.io.File;

import ldk.map_editor.model.project.map.MapProject;

public interface IProjectSaver {
	
	public void save(File file, MapProject mapProject);
}
