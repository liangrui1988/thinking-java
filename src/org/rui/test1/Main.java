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
		// 2��3�η� 1024 2��10�η� << 2��N�η�
		System.out.println(3 * 2 * 2 * 2);

		Map map = new WeakHashMap();

		int ii = 9;
		Integer i2 = new Integer(500);
		test(i2);// ����
		System.out.println("hash:" + i2.hashCode());

		StringBuilder str = new StringBuilder("bb");
		test(str);// ����
		System.out.println(str);

		String stri = "stri";
		test(stri);// ����
		System.out.println(stri);
	}

}
