package org.rui.stringTest;

public class IntegerMatch {

	public static void main(String[] args) {
		System.out.println("-123456".matches("-?\\d+"));
		System.out.println("456".matches("-?\\d+"));
		System.out.println("+991".matches("-?\\d+"));
		System.out.println("+991".matches("(-|\\+)?\\d+"));

	}

}
