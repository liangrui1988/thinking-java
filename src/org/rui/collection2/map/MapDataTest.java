package org.rui.collection2.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.rui.generics.anonymity.Generator;

/**
 * 下面是一个使用MapData的示例，LettersGenerator 通过产生一个Iterator还实现了Iterable,通过这种方式，
 * 它可以被用来测试MapData.map()方法，而这些方法都需要用到iterable
 * 
 * @author lenovo
 * 
 */

class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer> {

	private int size = 9;
	private int number = 1;
	private char letter = 'a';

	@Override
	public Pair<Integer, String> next() {
		return new Pair<Integer, String>(number++, "" + letter++);
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			public Integer next() {
				return number++;
			}

			public boolean hasNext() {
				return number < size;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}

public class MapDataTest {
	public static void main(String[] args) {
		Character[] chars = { 'a', 'b', 'c', 'd', 'e' };

		List<Character> list = Arrays.asList(chars);
		System.out.println(MapData.map(new Letters(), 11));
		System.out.println(MapData.map(new Letters(), "pop"));
		System.out.println(MapData.map(new Letters(), new Letters()));
		System.out.println(MapData.map(list, "value"));

	}
}
/**
 * output: {1=a, 2=b, 3=c, 4=d, 5=e, 6=f, 7=g, 8=h, 9=i, 10=j, 11=k} {1=pop,
 * 2=pop, 3=pop, 4=pop, 5=pop, 6=pop, 7=pop, 8=pop}
 * {1=org.rui.collection2.map.Pair@170bea5,
 * 2=org.rui.collection2.map.Pair@f47396, 3=org.rui.collection2.map.Pair@d0af9b,
 * 4=org.rui.collection2.map.Pair@b8f8eb,
 * 5=org.rui.collection2.map.Pair@1de17f4,
 * 6=org.rui.collection2.map.Pair@1f6ba0f,
 * 7=org.rui.collection2.map.Pair@1313906,
 * 8=org.rui.collection2.map.Pair@96cf11} {a=value, b=value, c=value, d=value,
 * e=value}
 */
