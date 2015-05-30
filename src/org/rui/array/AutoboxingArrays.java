package org.rui.array;

import java.util.Arrays;

/**
 * 自动包装机制对数组初始化也起作用
 * 
 * @author lenovo
 * 
 */
public class AutoboxingArrays {
	public static void main(String[] args) {
		Integer[][] a = { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
				{ 11, 22, 33, 44, 55, 66, 77, 88, 99, 100 },
				{ 21, 22, 23, 24, 25, 26, 27, 28, 29, 210, 666 },
				{ 31, 32, 33, 34, 35, 36, 37, 38, 39, 310, 888 }, };
		System.out.println(Arrays.deepToString(a));
	}

}
/**
 * output: [ [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], [11, 22, 33, 44, 55, 66, 77, 88,
 * 99, 100], [21, 22, 23, 24, 25, 26, 27, 28, 29, 210, 666], [31, 32, 33, 34,
 * 35, 36, 37, 38, 39, 310, 888] ]
 */
