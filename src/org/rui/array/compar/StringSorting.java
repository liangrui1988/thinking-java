package org.rui.array.compar;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * String ≈≈–Ú ≤È’“
 * 
 * @author lenovo
 */
public class StringSorting {
	public static void main(String[] args) {
		String[] a = { "a", "d", "b", "C", "A", "B", "D", "c", };
		System.out.println("before:" + Arrays.toString(a));
		Arrays.sort(a);
		System.out.println("after:" + Arrays.toString(a));
		Arrays.sort(a, Collections.reverseOrder());
		System.out.println("reverseOrder:" + Arrays.toString(a));
		Arrays.sort(a, String.CASE_INSENSITIVE_ORDER);
		System.out.println("CASE_INSENSITIVE_ORDER:" + Arrays.toString(a));
		// ≤È’“
		int index = Arrays.binarySearch(a, a[3]);
		System.out.println("index::" + index + "  val:" + a[index]);
	}
}
/**
 * before:[a, d, b, C, A, B, D, c] after:[A, B, C, D, a, b, c, d]
 * reverseOrder:[d, c, b, a, D, C, B, A] CASE_INSENSITIVE_ORDER:[a, A, b, B, c,
 * C, d, D] index::3 val:B
 */
