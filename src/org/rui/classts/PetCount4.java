package org.rui.classts;

import org.rui.classts.chilnd.*;

public class PetCount4 {

	public static void main(String[] args) {
		Pet p = new Dog();
		Class c = Pet.class;
		Class c1 = Dog.class;
		Class c2 = Cat.class;
		// ����Ա� ��
		if (p instanceof Dog) {
			System.out.println("true");
		} else
			System.out.println("fales");

		// class �Ա� ����
		if (c.isInstance(p)) {
			System.out.println("true");
		} else
			System.out.println("fales");
		// class�Ա�class
		if (c.isAssignableFrom(c1)) {
			System.out.println("true");
		} else
			System.out.println("fales");

		if (c2.isAssignableFrom(c1)) {
			System.out.println("true");
		} else
			System.out.println("fales");

		System.out.println("c==c1:" + (c == c1));
		System.out.println("c.equals(c1:" + (c.equals(c1)));

		System.out.println("c==Pet.class:" + (c == Pet.class));
		System.out.println("c.equals(Pet.class:" + (c.equals(Pet.class)));

	}

}
