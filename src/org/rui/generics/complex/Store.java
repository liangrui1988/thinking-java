package org.rui.generics.complex;

import java.util.ArrayList;
import java.util.Random;

import org.rui.generics.Bean1;
import org.rui.generics.Bean2;
import org.rui.generics.FourTuple;
import org.rui.generics.TupleTest;
import org.rui.generics.anonymity.Generator;
import org.rui.generics.anonymity.Generators;

/**
 * ����һ�������̵� ���������ɣ����ܺ���Ʒ
 * 
 * @author lenovo
 * 
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 */
// //////////��Ʒ��//////////////////////////////////
class Product {
	private final int id;
	private String description;
	private double price;

	public Product(int id, String description, double price) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id:" + id + ", " + description + ", price:$" + price
				+ "]";
	}

	public void priceChange(double d) {
		price += d;
	}

	//
	public static Generator<Product> generator = new Generator<Product>() {
		private Random ran = new Random(47);

		public Product next() {
			return new Product(ran.nextInt(1000), "test", Math.round(ran
					.nextDouble() * 1000.0 + 0.99));
		}

	};

}

// /////����//////////////////////////////////////////
class Shelf extends ArrayList<Product> {
	public Shelf(int nProducts) {
		// ����ָ����������Ʒ��װ�ص�ArrayList������
		Generators.fill(this, Product.generator, nProducts);
	}

}

// ////�����ϵ���Ʒ///////////////////////////////////////////
class Aisle extends ArrayList<Shelf> {
	public Aisle(int nShelves, int nProduct) {
		// �����з������
		add(new Shelf(nProduct));
	}
}

// ����̨
class CheckoutStand {
}

class Office {
}

// Store.java
public class Store extends ArrayList<Aisle> {
	private ArrayList<CheckoutStand> checkouts = new ArrayList<CheckoutStand>();
	private Office office = new Office();

	public Store(int nAisles, int nShelves, int Nproducts) {
		for (int i = 0; i < nAisles; i++)
			// ָ����Ʒ������ܣ����ܷŷŵ�����
			add(new Aisle(nShelves, Nproducts));
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Aisle a : this)
			for (Shelf s : a)
				for (Product p : s) {
					result.append(p);
					result.append("\n");
				}
		return result.toString();
	}

	/**
	 * main ����
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// args ���ȣ����ܣ���Ʒ
		System.out.println(new Store(2, 5, 10));
	}

}
/**
 * output: Product [id:258, test, price:$401.0] Product [id:861, test,
 * price:$161.0] Product [id:868, test, price:$418.0] Product [id:207, test,
 * price:$269.0] Product [id:551, test, price:$115.0] .................
 */
