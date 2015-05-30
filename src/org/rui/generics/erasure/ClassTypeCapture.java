package org.rui.generics.erasure;

/**
 * 擦除的补偿
 * 
 * 编译器将确保类型标签可以匹配泛型参数
 * 
 * @author lenovo
 * 
 */

class Building {
}

class House extends Building {
}

public class ClassTypeCapture<T> {

	Class<T> kind;

	public ClassTypeCapture(Class<T> kind) {
		this.kind = kind;
	}

	public boolean f(Object obj) {
		System.out.println(kind + "   isInstance    " + obj);
		return kind.isInstance(obj);
	}

	public static void main(String[] args) {
		ClassTypeCapture<Building> ctc = new ClassTypeCapture<Building>(
				Building.class);
		System.out.println(ctc.f(new Building()));
		// 父类 与子对比
		System.out.println(ctc.f(new House()));

		ClassTypeCapture<House> ctc2 = new ClassTypeCapture<House>(House.class);
		// House is building 子对比父=false
		System.out.println(ctc2.f(new Building()));
		System.out.println(ctc2.f(new House()));

	}

}
/**
 * output: true true false true
 */
