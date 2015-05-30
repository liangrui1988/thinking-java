package org.rui.generics.anonymity;

import org.rui.generics.Bean1;

public class Main<T> {

	public static void main(String[] args) {

		Bean1 b = new Bean1();
		PageBean<Bean1> page = new PageBean<Bean1>(b);

		page.counter = 80;
		System.out.println(page.id);
		page.counter = 10;
		System.out.println(page.counter);
		System.out.println(page.id);

		System.out.println("getGenType:" + page.getGenType());

	}

}
