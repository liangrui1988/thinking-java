package org.rui.generics.anonymity;

public class PageBean<T> {

	T obj;

	// 返回泛型的内型对象
	T getGenType() {
		return obj;
	}

	public PageBean(T t) {
		obj = t;
	}

	long counter = 1;
	final long id = ++counter;

}
