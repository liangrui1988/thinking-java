package org.rui.test1;

import java.util.Arrays;

public class ArraySort {
	public static void main(String[] args) {
		int[] x = { 5, 1, 10, 9, 0, -8, 99 };

		for (int i = 0; i < x.length + 0; i++)// 外层控制循环多少次 要排多少次序
		{
			// 外层每排一次，内层就会确定一位数的正确大小位置
			// j=i表示取数组第i个值和前面排过序的每个值做比较 j>0表示没有元素再比较了
			for (int j = i; j > 0 && x[j - 1] > x[j]; j--) {
				int t = x[j];
				x[j] = x[j - 1];
				x[j - 1] = t;

			}
			System.out.println();
			for (int temp = 0; temp < x.length; temp++) {
				System.out.print(x[temp] + "  ");
			}
		}

		// 222222222222222
		int[] array = { 5, 1, 10, 9, 0, -8, 99 };

		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < array.length - i; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		
		System.out.println("222222222222");
		System.out.println("从小到大排序后的结果：");
		for (int i : array) {
			System.out.print(i + " ");
		}
	
	
	
	int [] arrays={7,8,5,1,10,9};  
        /* Arrays.sort(arrays);  
         System.out.println(); 
        for(int i=0;i<x.length;i++){ 
            System.out.print(x[i]+"  "); 
        }  */
        System.out.println(); 
        for(int i=arrays.length-1;i>=0;i--){ 
            System.out.print(arrays[i]+"  "); 
        }  
    }  

}
