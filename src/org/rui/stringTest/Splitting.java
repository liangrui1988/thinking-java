package org.rui.stringTest;

import java.util.Arrays;

public class Splitting {
	public static String knights = "Then,when you have found the shrubbery, you must"
			+ "cut down the mightiest tree in the forest... "
			+ "with... a herring!";

	public static void split(String regex) {
		System.out.println(Arrays.toString(knights.split(regex)));
	}

	public static void main(String[] args) {

		split(" ");
		split("\\+W");
		split("n\\W+");

		String s = knights;
		System.out.println(s);
		System.out.println(s.replaceFirst("f\\w", "==located=="));
		System.out
				.println(s.replaceAll("shrubbery|tree|herring", "==banana=="));

		//
		for (String pattern : new String[] { "HelloWorld", "[hH]elloWorld",
				"[hH][leijh][a-z]loW.*", "H.*" })
			System.out.println("HelloWorld".matches(pattern));

	}

}
