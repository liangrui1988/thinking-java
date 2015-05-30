//Sets.java
package org.rui.generics.set;

import java.util.HashSet;
import java.util.Set;

/**
 * 一个Set实用工具
 * 
 * @author lenovo
 * 
 */
public class Sets {

	public static <T> Set<T> union(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>(a);
		result.addAll(b);
		return result;
	}

	// 保留 相同
	public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>(a);
		result.retainAll(b);
		return result;
	}

	// 差值 //去掉相同
	public static <T> Set<T> difference(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>(a);
		result.removeAll(b);
		return result;
	}

	// 除了交集之外的所有过犹元素
	public static <T> Set<T> complement(Set<T> a, Set<T> b) {
		return difference(union(a, b), intersection(a, b));
	}

	public static void main(String[] args) {
		Set<String> result = new HashSet<String>();
		result.add("a");
		result.add("b");
		Set<String> result2 = new HashSet<String>();
		result2.add("b");

		Set<String> results = complement(result, result2);
		for (String s : results) {
			System.out.println(s);
		}
	}
}
