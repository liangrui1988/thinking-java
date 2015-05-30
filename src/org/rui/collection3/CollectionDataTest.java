package org.rui.collection3;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.rui.generics.anonymity.Generator;

/**
 * �����ǳ�ʼ��linkedHashSet��һ��ʾ��
 * 
 * @author lenovo
 * 
 */
class Government implements Generator<String> {

	String[] foundation = ("hello world he wo he wo a b c").split(" ");

	private int index;

	@Override
	public String next() {
		return foundation[index++];
	}
}

public class CollectionDataTest {
	public static void main(String[] args) {
		Set<String> set = new LinkedHashSet<String>(new CollectionData<String>(
				new Government(), 9));
		// ʹ�÷���ķ���
		//Set<String> set=new LinkedHashSet<String>();
		
		//Governmentʵ����Generator  list�����CollectionData  --������Generator.next  ��ӵ�����ArrayList
		//set.addAll(new ArrayList<String>());
		set.addAll(CollectionData.list(new Government(), 9));
		System.out.println(set);
	}
}
/**output:
 * [hello, world, he, wo, a, b, c]
 */
