package org.rui.io;

import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		String tager = "dsf.java";
		String reg = "d.*\\.java";
		Pattern p = Pattern.compile(reg);
		boolean b = p.matcher(tager).matches();
		System.out.println(b);
	}
}
