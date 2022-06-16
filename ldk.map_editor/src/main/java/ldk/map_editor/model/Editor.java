package ldk.map_editor.model;

import java.io.FileNotFoundException;
import java.io.IOException;

import ldk.map_editor.exceptions.ProjectNotLoadedException;
import ldk.map_editor.model.enums.EditorModes;
import ldk.map_editor.model.project.Project;
import ldk.map_editor.model.project.file_work.IProjectCreator;
import ldk.map_editor.model.project.file_work.IProjectExport;
import ldk.map_editor.model.project.file_work.IProjectLoader;
import ldk.map_editor.model.project.file_work.IProjectSaver;
import ldk.map_editor.view.controllers.IDialogTextField;
import ldk.map_editor.view.controllers.IModeUpdateHandler;

public class Editor {
	
	private static final String EXPORT_MAP_PATH = "";
	private static final String EXPORT_MAP_FORMAT = "";
	
	private Project project;
	
	private IProjectCreator projectCreator;
	private IProjectLoader projectLoader;
	private IProjectSaver projectSaver;
	private IProjectExport projectExport;
	
	private EditorModes mode;
	
	private IModeUpdateHandler modeUpdateHandler;
	private IDialogTextField dialogTextField;
	
	public Editor() {
		//projectCreator = new;
		//projectLoader = new;
		//projectSaver = new;
		//projectExport = new;
	}
	
	public void setModeUpdateHandler(IModeUpdateHandler modeUpdateHandler) {
		this.modeUpdateHandler = modeUpdateHandler;
	}
	
	public void setDialogTextField(IDialogTextField dialogTextField) {
		this.dialogTextField = dialogTextField;
	}
	
	public void setMode(EditorModes mode) {
		this.mode = mode;
		modeUpdateHandler.handle(mode);
	}
	
	public void newProject() throws IOException {
		String result = dialogTextField.raise("How do you want to name your map?");
		project = new Project(result, projectCreator, projectLoader, projectSaver, projectExport);
	}
	
	public void loadProject(String mapProjectID) throws FileNotFoundException {
		project = new Project(mapProjectID, projectCreator, projectLoader, projectSaver, projectExport);
		project.load();
	}
	
	public void saveProject() throws ProjectNotLoadedException, IOException {
		if (project == null) {
			throw new ProjectNotLoadedException(this.getClass().getName());
		}
		
		project.save();
	}
	
	public void export(String fileName) throws ProjectNotLoadedException {
		if (project == null) {
			throw new ProjectNotLoadedException(this.getClass().getName());
		}
		
		String path = EXPORT_MAP_PATH + fileName + EXPORT_MAP_FORMAT;
		
		project.export(path);
		
	}
	
	public boolean isThereUnsavedChanges() {
		return true; // TODO isThereUnsavedChanges(). 
	}
	
	public EditorModes getMode() {
		return mode;
	}
}
