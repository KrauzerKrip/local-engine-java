package eng_console;

public class Console {
	public static void write(String msg) {
		System.out.println(msg);
	}
	
	public static void warn(String msg) {
		System.err.println(msg);
	}
}
