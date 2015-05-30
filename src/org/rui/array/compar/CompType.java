package org.rui.array.compar;

import java.util.Arrays;
import java.util.Random;

import org.rui.generics.anonymity.Generator;

/**
 * ������ƵĻ���Ŀ���ǡ������ֲ����������ᷢ���ı����������롱 �����ǣ��������ͨ�õ������㷨���仯���Ǹ��ֶ����໥�Ƚϵķ�ʽ��
 * ��ˣ����ǽ����бȽϵĴ����д�ɲ�ͬ���ӳ��򣬶���ʹ�� �������ģʽ�� ͨ��ʹ�ò��ԣ����Խ� �ᷢ���仯�Ĵ��� ��װ�ڵ��������У����Զ���
 * ����Խ����Զ��󴫵ݸ�������ͬ�Ĵ��룬��Щ���뽫ʹ�ò�����������㷨�� ͨ�����ַ�ʽ�����ܹ��ò�ͬ�Ķ�������ʾ��ͬ�ıȽϷ�ʽ��
 * Ȼ�����Ǵ��ݸ���ͬ�ķ�����롣
 * 
 * java�����з�ʽ�ṩ�ȽϹ��ܡ� ��һ����ʵ��java.lang.Comparable�� �����ǰ����С�ڲ����򷵻ظ�ֵ����ͬ=0������ =����
 * 
 * @author lenovo
 * 
 */
public class CompType implements Comparable<CompType> {
	int i;
	int j;
	private static int count = 1;

	@Override
	public String toString() {
		String result = "CompType [i=" + i + ", j=" + j + "]";
		if (count++ % 3 == 0)
			result += "\n";
		return result;
	}

	public CompType(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public int compareTo(CompType o) {
		return (i < o.i ? -1 : (i == o.i ? 0 : 1));
	}

	// ///////////////////////////////
	private static Random r = new Random(47);

	public static Generator<CompType> generator() {
		return new Generator<CompType>() {
			public CompType next() {
				return new CompType(r.nextInt(100), r.nextInt(100));
			}
		};
	}

	// /////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		CompType[] a = Generated.array(new CompType[12], generator());
		System.out.println("befoe sorting:" + Arrays.toString(a));
		Arrays.sort(a);
		System.out.println("after sorting" + Arrays.toString(a));
	}
}
/**
 * output: befoe sorting:[CompType [i=58, j=55], CompType [i=93, j=61], CompType
 * [i=61, j=29] , CompType [i=68, j=0], CompType [i=22, j=7], CompType [i=88,
 * j=28] , CompType [i=51, j=89], CompType [i=9, j=78], CompType [i=98, j=61] ,
 * CompType [i=20, j=58], CompType [i=16, j=40], CompType [i=11, j=22] ] after
 * sorting[CompType [i=9, j=78], CompType [i=11, j=22], CompType [i=16, j=40] ,
 * CompType [i=20, j=58], CompType [i=22, j=7], CompType [i=51, j=89] , CompType
 * [i=58, j=55], CompType [i=61, j=29], CompType [i=68, j=0] , CompType [i=88,
 * j=28], CompType [i=93, j=61], CompType [i=98, j=61] ]
 */
