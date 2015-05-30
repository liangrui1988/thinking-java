//Generators.java
package org.rui.generics.anonymity;

import java.util.Collection;

/**
 * 利用生成器很方便的填充一个collection
 * 
 * @author lenovo
 * 
 */
public class Generators {
	public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen,
			int n) {
		// System.out.println("gen.next():"+gen.next());
		for (int i = 0; i < n; i++) {
			coll.add(gen.next());
		}
		return coll;

	}
}
