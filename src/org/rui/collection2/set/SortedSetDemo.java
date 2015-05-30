package org.rui.collection2.set;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * SortedSet 排序状态 Comparator comparator（）返回当前Set使用的Comparator;或者返回null
 * SortedSet是按对象的比较函数对无素排序的
 * 
 * @author lenovo
 * 
 */
public class SortedSetDemo {

	public static void main(String[] args) {
		SortedSet<String> sorted = new TreeSet<String>();
		Collections.addAll(sorted,
				"one two three four five six seven eight".split(" "));
		System.out.println(sorted);
		String low = sorted.first();
		String high = sorted.last();
		System.out.println(low);
		System.out.println(high);
		Iterator<String> it = sorted.iterator();
		for (int i = 0; i <= 6; i++) {
			if (i == 3)
				low = it.next();
			if (i == 6)
				high = it.next();
			else
				it.next();
		}
		System.out.println(low);
		System.out.println(high);
		System.out.println(sorted.subSet(low, high));
		System.out.println(sorted.headSet(high));// 小于 ToElement元素的组成
		System.out.println(sorted.tailSet(low));// 大于等于
	}

}
/**
 * output: [eight, five, four, one, seven, six, three, two] eight two one two
 * [one, seven, six, three] [eight, five, four, one, seven, six, three] [one,
 * seven, six, three, two]
 */
