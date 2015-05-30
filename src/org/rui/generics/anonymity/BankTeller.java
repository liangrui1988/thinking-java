//BankTeller.java
package org.rui.generics.anonymity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * 匿名内部类 内部类 就用于泛型 generator 都生明成了static的，所以它们无法作为接口的一部分， 因为无法用接口这种特定的惯用法来泛化这二者。
 * 尽管如此，它们fill()方法中都工作的很好
 * 
 * @author lenovo
 * 
 */

class Customer {
	private static long counter = 1;
	private final long id = counter++;

	private Customer() {
	}

	public String toString() {
		return "Customer:" + id;
	}

	// 每次会创建一个新的客户对象
	public static Generator<Customer> generator() {
		return new Generator<Customer>() {
			public Customer next() {
				return new Customer();
			}
		};
	}

}

// // 出纳员
class Teller {
	private static long counter = 1;
	private final long id = counter++;

	private Teller() {
	}

	public String toString() {
		return "Teller" + id;
	}

	// Teller就只创建了一个public 的generator对象
	public static Generator<Teller> generator = new Generator<Teller>() {
		public Teller next() {
			return new Teller();
		}

	};
}

// //////////// 出纳员
public class BankTeller {

	public static void serve(Teller t, Customer c) {
		System.out.println(t + " serves " + c);
	}

	public static void main(String[] args) {
		Random random = new Random(47);
		// 生成客户对象15个
		Queue<Customer> line = new LinkedList<Customer>();
		Generators.fill(line, Customer.generator(), 15);

		// 出纳员对象4个
		List<Teller> tellers = new ArrayList<Teller>();
		Generators.fill(tellers, Teller.generator, 4);

		for (Customer c : line)
			serve(tellers.get(random.nextInt(tellers.size())), c);

	}

}
/*
 * output: Teller3 serves Customer1 Teller2 serves Customer2 Teller3 serves
 * Customer3 Teller1 serves Customer4 Teller1 serves Customer5 Teller3 serves
 * Customer6 Teller1 serves Customer7 Teller2 serves Customer8 Teller3 serves
 * Customer9 Teller3 serves Customer10 Teller2 serves Customer11 Teller4 serves
 * Customer12 Teller2 serves Customer13 Teller1 serves Customer14 Teller1 serves
 * Customer15
 */// :~~~
