package org.rui.stringTest;

public class StringFormat {
	public static void main(String[] args) {
		// char c='a';
		int c = 'a';
		System.out.println(String.format("%s", c));
		System.out.println(String.format("%c", c));
		System.out.println(String.format("%h", c));
		System.out.println(String.format("%x", c));
	}

}
