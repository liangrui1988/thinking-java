package org.rui.array;

import java.util.Arrays;

/**
 * 可以用类似的方式处理非基本类型的对象数组。 下面，你可以看到如何 用花括号把多个new 表达式组织到一起：
 * 
 * @author lenovo
 * 
 */
public class MultidimensionalObjectArrays {
	public static void main(String[] args) {
		Bean[][] b = {
				{ new Bean(), new Bean(), },
				{ new Bean(), new Bean(), new Bean(), new Bean(), },
				{ new Bean(), new Bean(), new Bean(), new Bean(), new Bean(),
						new Bean(), new Bean(), new Bean(), }, };
		System.out.println(Arrays.deepToString(b));
	}
}
/*
 * [[org.rui.array.Bean@1afae45, org.rui.array.Bean@da4b71],
 * [org.rui.array.Bean@d9660d, org.rui.array.Bean@bb0d0d,
 * org.rui.array.Bean@55e55f, org.rui.array.Bean@145c859],
 * [org.rui.array.Bean@2c1e6b, org.rui.array.Bean@811c88,
 * org.rui.array.Bean@785d65, org.rui.array.Bean@3bc257,
 * org.rui.array.Bean@153f67e, org.rui.array.Bean@15bdc50,
 * org.rui.array.Bean@1dd3812, org.rui.array.Bean@8c436b]]
 */