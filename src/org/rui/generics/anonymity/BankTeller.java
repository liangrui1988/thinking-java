//BankTeller.java
package org.rui.generics.anonymity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * �����ڲ��� �ڲ��� �����ڷ��� generator ����������static�ģ����������޷���Ϊ�ӿڵ�һ���֣� ��Ϊ�޷��ýӿ������ض��Ĺ��÷�����������ߡ�
 * ������ˣ�����fill()�����ж������ĺܺ�
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

	// ÿ�λᴴ��һ���µĿͻ�����
	public static Generator<Customer> generator() {
		return new Generator<Customer>() {
			public Customer next() {
				return new Customer();
			}
		};
	}

}

// // ����Ա
class Teller {
	private static long counter = 1;
	private final long id = counter++;

	private Teller() {
	}

	public String toString() {
		return "Teller" + id;
	}

	// Teller��ֻ������һ��public ��generator����
	public static Generator<Teller> generator = new Generator<Teller>() {
		public Teller next() {
			return new Teller();
		}

	};
}

// //////////// ����Ա
public class BankTeller {

	public static void serve(Teller t, Customer c) {
		System.out.println(t + " serves " + c);
	}

	public static void main(String[] args) {
		Random random = new Random(47);
		// ���ɿͻ�����15��
		Queue<Customer> line = new LinkedList<Customer>();
		Generators.fill(line, Customer.generator(), 15);

		// ����Ա����4��
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
