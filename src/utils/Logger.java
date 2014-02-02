package utils;

public class Logger {
	private static boolean debug = true;
	
	public static void print(String output) {
		if(debug) {
			System.out.println(output);
		}
	}
	
	public static void print(String formatString, Object... args) {
		if(debug) {
			System.out.println(String.format(formatString, args));
		}
	}
}