package ldk.map_editor.model.project;

import java.io.File;
import java.io.FileNotFoundException;

import ldk.map_editor.model.project.file_work.IProjectExport;
import ldk.map_editor.model.project.file_work.IProjectLoader;
import ldk.map_editor.model.project.file_work.IProjectSaver;
import ldk.map_editor.model.project.map.MapProject;
import ldk.map_editor.stuff.FilePaths;

public class Project {
	
	private static final String FILE_FORMAT = ".format";
	
	private final String name;
	
	private MapProject mapProject;
	
	private IProjectLoader projectLoader;
	private IProjectSaver projectSaver;
	private IProjectExport projectExport;

	
	public Project(String name, IProjectLoader projectLoader, IProjectSaver projectSaver, IProjectExport projectExport) {
		this.name = name;
		this.projectLoader = projectLoader;
		this.projectSaver = projectSaver;
		this.projectExport = projectExport;
	}
	
	public void load() throws FileNotFoundException {
		File projectFile = getProjectFile();
		
		if (projectFile.exists() == false) {
			throw new FileNotFoundException("Project file not found.");
		}
		
		mapProject = projectLoader.load(getProjectFile());
		
	}
	
	public void save() {
		projectSaver.save(getProjectFile(), mapProject);
	}
	
	public void export(String path) {
		projectExport.export(new File(path));
	}
	
	private File getProjectFile() {
		return new File(FilePaths.MAPS_PROJECTS_PATH + name + FILE_FORMAT);
	}
	
	public String getName() {
		return name;
	}
}
