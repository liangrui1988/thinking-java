package org.rui.collection3;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.rui.generics.anonymity.Generator;

/**
 * 下面是初始化linkedHashSet的一个示例
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
		// 使用方便的方法
		//Set<String> set=new LinkedHashSet<String>();
		
		//Government实现了Generator  list里调用CollectionData  --》里有Generator.next  添加到自身ArrayList
		//set.addAll(new ArrayList<String>());
		set.addAll(CollectionData.list(new Government(), 9));
		System.out.println(set);
	}
}
/**output:
 * [hello, world, he, wo, a, b, c]
 */
