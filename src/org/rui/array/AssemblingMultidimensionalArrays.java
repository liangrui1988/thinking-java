package org.rui.array;

import java.util.Arrays;

/**
 * �������չʾ�˿����������أ����ֵع���һ���ǻ������͵Ķ�������
 * 
 * @author lenovo
 * 
 */
public class AssemblingMultidimensionalArrays {
	public static void main(String[] args) {
		Integer[][] a;
		a = new Integer[3][];
		for (int i = 0; i < a.length; i++) {
			a[i] = new Integer[3];
			for (int j = 0; j < a[i].length; j++)
				a[i][j] = i * j;// autoboxing
		}
		System.out.println(Arrays.deepToString(a));
	}
}
/**
 * output: [[0, 0, 0], [0, 1, 2], [0, 2, 4]]
 */
