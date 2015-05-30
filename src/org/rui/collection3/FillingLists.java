package org.rui.collection3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * �������
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
		// nCopies ������ָ������� n ��������ɵĲ��ɱ��б�
		List<StringAddress> list = new ArrayList<StringAddress>(
				Collections.nCopies(4, new StringAddress("hello")));
		System.out.println(list);
		// fill ʹ��ָ��Ԫ���滻ָ���б��е�����Ԫ�ء�
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
