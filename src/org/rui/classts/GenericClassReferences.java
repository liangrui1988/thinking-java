package org.rui.classts;

public class GenericClassReferences {

	public static void main(String[] args) {

		Class intClazz = int.class;
		Class<Integer> clzz = int.class;
		clzz = Integer.class;
		intClazz = double.class;
		// clzz=double.class;

		// Class<Number> n1=int.class;
		Class<?> numbs = int.class;
		Class<? extends Number> n2 = int.class;
		n2 = int.class;
		n2 = double.class;
		n2 = Number.class;
		// n2=String.class;
		numbs = String.class;

	}

}
