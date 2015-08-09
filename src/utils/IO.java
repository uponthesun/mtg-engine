package utils;

import java.util.Scanner;

public class IO {
	private static boolean debug = true;

	private static final Scanner SCANNER = new Scanner(System.in);
	
	public static void print(Object o) {
		print(o.toString());
	}
	
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
	
	public static String readLine() {
		return SCANNER.nextLine();
	}
}