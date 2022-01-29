package eng_console;

import java.util.ArrayList;
import java.util.EnumSet;

public class CommandHandler {
	
	String command;
	
	public CommandHandler(String command) {
		this.command = command;
	}
	
	public void parseCommand() {
		String[] tokens = command.split(" ");
		
		String type = tokens[0].split(":")[0];
		
		if (type.equals("set")) {
			parseSet(tokens);
		}
		
	} 
	
	private void parseSet(String[] tokens) {
		// set: SceneParameters objectDynamicHandling = true;
		// set: SceneParameters objectDynamicHandling = true [-force, -sth_else];

		if (tokens.length >= 5) {
			
			ArrayList<Command.Flag> flags = new ArrayList<Command.Flag>();
			
			String parametersGroup = tokens[1];
			String parameterName = tokens[2];
			String value = tokens[4];
			
			if (tokens.length >= 6) {
				String flagsRaw = tokens[5];
				
				String[] flagStrings = flagsRaw.split("\\[|\\];|, ");
				
				for (String flagString : flagStrings) {
					flagString = flagString.toLowerCase();
					if ((flagString.equals("-force")) || (flagString.equals("-f"))) {
						flags.add(Command.Flag.FORCE);
					}
				}
				
				try {
					new Command(Command.Type.SET, parametersGroup, parameterName, value, EnumSet.copyOf(flags));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}
