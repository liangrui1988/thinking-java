package org.rui.generics.erasure;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 边界处的动作 容器而不是数组 情况不不同了
 * 
 * @author lenovo
 * 
 */
public class ListMaker<T> {
	/*
	 * private Class<T> kind; public ListMaker(Class<T> kind) { this.kind=kind;
	 * }
	 */

	List<T> create(T t, int n) {
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < n; i++)
			list.add(t);
		return list;
	}

	public static void main(String[] args) {
		ListMaker<String> maker = new ListMaker<String>();
		List<String> str = maker.create("hello", 4);
		System.out.println(str);
	}
}
