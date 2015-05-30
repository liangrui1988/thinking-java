package org.rui.collection3;

import java.util.ArrayList;

import org.rui.generics.anonymity.Generator;

/**
 * һ��generator�������
 * 
 * @author lenovo
 * 
 * @param <T>
 */
public class CollectionData<T> extends ArrayList<T> {
	// quantity ����
	public CollectionData(Generator<T> gen, int quantity) {
		for (int i = 0; i < quantity; i++) {
			add(gen.next());
		}
	}

	// һ��ͨ�õı�������
	public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
		return new CollectionData<T>(gen, quantity);
	}
}
