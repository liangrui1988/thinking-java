package org.rui.array;

import java.util.Arrays;

/**
 * ��ά����ʾ��
 * 
 * @author lenovo
 * 
 */
public class ThreeDWithNew {

	public static void main(String[] args) {
		int[][][] a = new int[2][2][4];
		System.out.println(Arrays.deepToString(a));
	}
}
/**
 * output: [[[0, 0, 0, 0], [0, 0, 0, 0]], [[0, 0, 0, 0], [0, 0, 0, 0]]]
 * */
