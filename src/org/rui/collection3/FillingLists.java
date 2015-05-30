package org.rui.collection3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 填充容器
 * 
 * @author lenovo
 * 
 */
class StringAddress {
	private String s;

	public StringAddress(String s) {
		this.s = s;
	}

	public String toString() {
		return super.toString() + " " + s;
	}
}

public class FillingLists {
	public static void main(String[] args) {
		// nCopies 返回由指定对象的 n 个副本组成的不可变列表。
		List<StringAddress> list = new ArrayList<StringAddress>(
				Collections.nCopies(4, new StringAddress("hello")));
		System.out.println(list);
		// fill 使用指定元素替换指定列表中的所有元素。
		Collections.fill(list, new StringAddress("world"));
		System.out.println(list);
	}

}
/**
 * output: 
 * [org.rui.collection3.StringAddress@39443f hello,
 * org.rui.collection3.StringAddress@39443f hello,
 * org.rui.collection3.StringAddress@39443f hello,
 * org.rui.collection3.StringAddress@39443f hello]
 * 
 * [org.rui.collection3.StringAddress@1afae45 world,
 * org.rui.collection3.StringAddress@1afae45 world,
 * org.rui.collection3.StringAddress@1afae45 world,
 * org.rui.collection3.StringAddress@1afae45 world]
 */
