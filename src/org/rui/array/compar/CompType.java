package org.rui.array.compar;

import java.util.Arrays;
import java.util.Random;

import org.rui.generics.anonymity.Generator;

/**
 * 程序设计的基本目标是“将保持不变的事物与会发生改变的事物相分离” 而这是，不变的是通用的排序算法，变化的是各种对象相互比较的方式，
 * 因此，不是将进行比较的代码编写成不同的子程序，而是使用 策略设计模式。 通过使用策略，可以将 会发生变化的代码 封装在单独的类中（策略对象）
 * 你可以将策略对象传递给总是相同的代码，这些代码将使用策略来完成其算法。 通过这种方式，你能够用不同的对象来表示不同的比较方式，
 * 然后将它们传递给相同的非序代码。
 * 
 * java有两中方式提供比较功能。 第一种是实现java.lang.Comparable， 如果当前对象小于参数则返回负值。相同=0，大于 =正数
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
