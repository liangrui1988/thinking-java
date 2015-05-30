package org.rui.test1;

import java.util.Map;
import java.util.WeakHashMap;

public class Main {
	int j = 5;

	public static void test(Integer i2) {
		// i2=10;
		System.out.println(i2);
	}

	public static void test(StringBuilder i2) {
		// i2=10;
		i2.append("cc");

	}

	public static void test(String i2) {
		// i2=10;
		i2 = "sss";

	}

	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(3));
		System.out.println(3 << 3);
		// 2的3次方 1024 2的10次方 << 2的N次方
		System.out.println(3 * 2 * 2 * 2);

		Map map = new WeakHashMap();

		int ii = 9;
		Integer i2 = new Integer(500);
		test(i2);// 对象
		System.out.println("hash:" + i2.hashCode());

		StringBuilder str = new StringBuilder("bb");
		test(str);// 对象
		System.out.println(str);

		String stri = "stri";
		test(stri);// 对象
		System.out.println(stri);
	}

}
