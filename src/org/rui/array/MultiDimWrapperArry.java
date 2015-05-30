package org.rui.array;

import java.util.Arrays;

/**
 * 在Ingeger Double数组中，爪哇SE5的自动包装机制再次为我们创建了包装器对象
 * 
 * @author lenovo
 * 
 */
public class MultiDimWrapperArry {
	public static void main(String[] args) {
		Integer[][] al = { { 1, 2, 3 }, { 4, 5, 6 } };

		Double[][][] a2 = { { { 1.1, 2.2 }, { 3.3, 5.0 } },
				{ { 6.6, 7.7 }, { 8.8, 9.9 } },
				{ { 10.6, 10.7 }, { 1.8, 1.9 } } };

		String[][] a3 = { { "january", "february" },
				{ "march", "april", "may" },
				{ "june", "july", "august", "september", "october" }, };
		System.out.println("al:" + Arrays.deepToString(al));
		System.out.println("a2:" + Arrays.deepToString(a2));
		System.out.println("a3:" + Arrays.deepToString(a3));
	}
	/**
	 * al:[[1, 2, 3], [4, 5, 6]] a2:[[[1.1, 2.2], [3.3, 5.0]], [[6.6, 7.7],
	 * [8.8, 9.9]], [[10.6, 10.7], [1.8, 1.9]]] a3:[[january, february], [march,
	 * april, may], [june, july, august, september, october]]
	 */
}
