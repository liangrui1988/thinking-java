package org.rui.generics.erasure;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 边界处的动作 即失kind被存储为Class<T> 擦除也意味着它实际将被存储为 Class，没有任何参数
 * 
 * @author lenovo
 * 
 */
public class ArrayMaker<T> {
	private Class<T> kind;

	public ArrayMaker(Class<T> kind) {
		this.kind = kind;
	}

	@SuppressWarnings("unchecked")
	T[] create(int size) {
		return (T[]) Array.newInstance(kind, size);
	}

	public static void main(String[] args) {
		ArrayMaker<String> maker = new ArrayMaker<String>(String.class);
		String[] stringArray = maker.create(9);
		System.out.println(Arrays.toString(stringArray));
	}
}
/**
 * output: [null, null, null, null, null, null, null, null, null]
 */
