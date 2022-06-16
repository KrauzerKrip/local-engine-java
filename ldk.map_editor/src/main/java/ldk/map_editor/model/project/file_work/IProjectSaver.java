package ldk.map_editor.model.project.file_work;

import java.io.File;

import ldk.map_editor.model.project.Project;

public interface IProjectSaver {
	public void save(File file, Project project);
}
