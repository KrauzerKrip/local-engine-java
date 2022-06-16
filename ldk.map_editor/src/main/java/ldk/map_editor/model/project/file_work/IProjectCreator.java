package ldk.map_editor.model.project.file_work;

import java.io.File;
import java.io.IOException;

public interface IProjectCreator {
	public void create(File file) throws IOException;
}
