package org.rui.generics.anonymity;

public class PageBean<T> {

	T obj;

	// ���ط��͵����Ͷ���
	T getGenType() {
		return obj;
	}

	public PageBean(T t) {
		obj = t;
	}

	long counter = 1;
	final long id = ++counter;

}
