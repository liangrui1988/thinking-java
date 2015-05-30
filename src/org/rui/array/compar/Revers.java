package org.rui.array.compar;

import java.util.Arrays;
import java.util.Collections;

/**
 * Collections.reverseOrder 反转自然排序
 * 
 * @author lenovo
 * 
 */
public class Revers {
	public static void main(String[] args) {
		// System.out.println(CompType.generator());
		CompType[] a = Generated.array(new CompType[12], CompType.generator()//
				);
		System.out.println("before:" + Arrays.toString(a));
		Arrays.sort(a, Collections.reverseOrder());
		System.out.println("after:" + Arrays.toString(a));
	}

}
/*
 * before:[CompType [i=58, j=55], CompType [i=93, j=61], CompType [i=61, j=29] ,
 * CompType [i=68, j=0], CompType [i=22, j=7], CompType [i=88, j=28] , CompType
 * [i=51, j=89], CompType [i=9, j=78], CompType [i=98, j=61] , CompType [i=20,
 * j=58], CompType [i=16, j=40], CompType [i=11, j=22] ] after:[CompType [i=98,
 * j=61], CompType [i=93, j=61], CompType [i=88, j=28] , CompType [i=68, j=0],
 * CompType [i=61, j=29], CompType [i=58, j=55] , CompType [i=51, j=89],
 * CompType [i=22, j=7], CompType [i=20, j=58] , CompType [i=16, j=40], CompType
 * [i=11, j=22], CompType [i=9, j=78] ]
 */
