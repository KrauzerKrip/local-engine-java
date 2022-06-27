module local_core {
	requires org.lwjgl.glfw;
	requires org.joml;
	requires java.desktop;

	requires org.lwjgl.opengl;
	requires org.lwjgl.stb;
	
	exports eng_console;
	exports eng_exceptions;
	exports eng_game_objects;
	exports eng_graphics;
	exports eng_graphics.camera;
	exports eng_input;
	exports eng_parameters;
	exports eng_parameters.parameters_groups;
	exports eng_physics;
	exports eng_procedures;
	exports eng_scene;
	exports eng_script;
	exports eng_stuff;
	exports eng_tech_display;
	exports local_engine;
}
