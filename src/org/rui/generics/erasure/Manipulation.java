package org.rui.generics.erasure;

/**
 * 边界 <T extneds Hasf>声明T必须具有类型HasF或者从Hasf导出的类型。 如果情况确实如此，那么就可以安全地在obj上调用f()了
 * T擦除了 HasF
 * 
 * @author lenovo
 * 
 * @param <T>
 */
// class Manipulator<T> Error: 不能调用obj.f()
class Manipulator<T extends HasF> {
	private T obj;

	public Manipulator(T x) {
		obj = x;
	}

	public void manipulate() {
		obj.f();
	}

	// 获取泛型类型
	public T getGenType() {
		return obj;
	}

}

public class Manipulation<T> {
	public static void main(String[] args) {
		HasF h = new HasF();
		Manipulator<HasF> man = new Manipulator<HasF>(h);
		man.manipulate();
		System.out.println("genType:" + man.getGenType().getClass());
	}
}
/**
 * output: hasf.f genType:class org.rui.generics.erasure.HasF
 */
