package ldk.map_editor.model.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import ldk.map_editor.model.project.file_work.IProjectCreator;
import ldk.map_editor.model.project.file_work.IProjectExport;
import ldk.map_editor.model.project.file_work.IProjectLoader;
import ldk.map_editor.model.project.file_work.IProjectSaver;
import ldk.map_editor.model.project.map.MapProject;
import ldk.map_editor.stuff.FilePaths;

public class Project {
	
	private static final String FILE_FORMAT = ".format";
	
	private final String name;
	
	private MapProject mapProject;
	//private File projectFile;
	
	private IProjectCreator projectCreator;
	private IProjectLoader projectLoader;
	private IProjectSaver projectSaver;
	private IProjectExport projectExport;

	
	public Project(String name, IProjectCreator projectCreator, IProjectLoader projectLoader, IProjectSaver projectSaver, IProjectExport projectExport) {
		this.name = name;
		this.projectCreator = projectCreator;
		this.projectLoader = projectLoader;
		this.projectSaver = projectSaver;
		this.projectExport = projectExport;
	}
	
	private void create() throws IOException {
		if (getProjectFile().exists()) {
			throw new FileAlreadyExistsException("Project file already exists.");
		}
		
		File projectFile = getProjectFile();
		projectCreator.create(projectFile);
	}
	
	public void load() throws FileNotFoundException {
		File projectFile = getProjectFile();
		
		if (projectFile.exists() == false) {
			throw new FileNotFoundException("Project file not found.");
		}
		
		mapProject = projectLoader.load(getProjectFile());
		
	}
	
	public void save() throws IOException {
		File projectFile = getProjectFile();
		
		if (projectFile.exists()) {
			projectSaver.save(getProjectFile(), this);
		} else {
			create();
		}

	}
	
	public void export(String path) {
		projectExport.export(new File(path));
	}
	
	/**
	 * It doesn`t check if file exists.
	 * @return unchecked file. 
	 */
	private File getProjectFile() {
		return new File(FilePaths.MAPS_PROJECTS_PATH + name + FILE_FORMAT);
	}
	
	public String getName() {
		return name;
	}
}
