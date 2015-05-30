package org.rui.generics.complex;

import java.util.ArrayList;

import org.rui.generics.Bean1;
import org.rui.generics.Bean2;
import org.rui.generics.FourTuple;
import org.rui.generics.TupleTest;

/**
 * ��������ģ��
 * 
 * @author lenovo
 * 
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 */
public class TupleList<A, B, C, D> extends ArrayList<FourTuple<A, B, C, D>> {
	public static void main(String[] args) {
		// TupleList ӵ�и���ArrayList�Ĺ��� ���Ͷ���ΪFourTuple
		TupleList<Bean2, Bean1, String, Integer> t1 = new TupleList<Bean2, Bean1, String, Integer>();
		t1.add(TupleTest.h());
		t1.add(TupleTest.h());
		for (FourTuple<Bean2, Bean1, String, Integer> tup : t1)
			System.out.println(tup);
	}

}
