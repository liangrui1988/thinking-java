package org.rui.array;

import java.util.Arrays;
import java.util.Random;

/**
 * 粗糙数组 数组中构成的矩阵的每个向量都可以具有任意的长度
 * 
 * @author lenovo
 * 
 */
public class RaggedArray {
	public static void main(String[] args) {
		Random random = new Random(47);
		int[][][] a = new int[random.nextInt(7)][][];
		for (int i = 0; i < a.length; i++) {
			a[i] = new int[random.nextInt(5)][];
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = new int[random.nextInt(5)];
			}
		}
		System.out.println(Arrays.deepToString(a));
	}

}
/**
 * [[], [[0], [0], [0, 0, 0, 0]], [[], [0, 0], [0, 0]], [[0, 0, 0], [0], [0, 0,
 * 0, 0]], [[0, 0, 0], [0, 0, 0], [0], []], [[0], [], [0]]]
 */
