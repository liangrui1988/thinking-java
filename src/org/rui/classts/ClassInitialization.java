package org.rui.classts;

import java.util.Random;

class Initable {
	static final int staticFinal = 47;
	static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);
	static {
		System.out.println("initializing initable");
	}
}

class Initable2 {
	static final int staticFinal = 147;
	static {
		System.out.println("initializing initable2222222222222");
	}
}

class Initable3 {
	static final int staticFinal = 74;
	static {
		System.out.println("initializing initable333333333333");
	}
}

public class ClassInitialization {
	public static Random rand = new Random(47);

	public static void main(String[] args) {

		Class initable = Initable.class;
		System.out.println("1111111111");
		System.out.println(Initable.staticFinal);
		System.out.println(Initable.staticFinal2);

		Class initabl2e = Initable2.class;

		System.out.println("2222222222222222");
		System.out.println(Initable2.staticFinal);

		System.out.println("33333333333333333");
		try {
			Class initable3 = Class.forName("org.rui.classts.Initable3");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(Initable3.staticFinal);

	}

}
