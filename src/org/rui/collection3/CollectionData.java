package org.rui.collection3;

import java.util.ArrayList;

import org.rui.generics.anonymity.Generator;

/**
 * 一种generator解决方案
 * 
 * @author lenovo
 * 
 * @param <T>
 */
public class CollectionData<T> extends ArrayList<T> {
	// quantity 数量
	public CollectionData(Generator<T> gen, int quantity) {
		for (int i = 0; i < quantity; i++) {
			add(gen.next());
		}
	}

	// 一个通用的便利方法
	public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
		return new CollectionData<T>(gen, quantity);
	}
}
