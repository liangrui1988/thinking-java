package org.rui.collection2.hashcode;

import java.util.*;

import org.rui.classts.Pet;
import org.rui.classts.chilnd.*;

/**
 * 
 * �����ʾ��˵��������ι����ģ�
 * 
 * �������еČ��ﶼ�����֣�����������Ȱ����������Ȼ����ͬ����а��� ��������
 * ����������_��hashCode��equals����Ҫ���ɣ�Apache��jakarta commons�Ŀ�����S�๤�߿����ˎ�������ɴ���
 * 
 * @author lenovo
 * 
 */

public class IndvidualTest {

	public static void main(String[] args) {
		// Set<Individual> pets=new TreeSet<Individual>();
		Pet p = new Cat("è");
		Pet p1 = new Dog("��");
		Pet p2 = new EgyptianMan("EgyptianMan");
		Pet p3 = new Manx("�����è");
		Pet p4 = new Pug("�͸�Ȯ");

		// һ�����кܶ����
		Map<Individual, List<? extends Pet>> list2 = new HashMap<Individual, List<? extends Pet>>();

		Individual in = new Individual("Dawn");
		Individual in2 = new Individual("�|������");
		list2.put(in, Arrays.asList(p, p1, p2, p3, p4));
		list2.put(in2, Arrays.asList(p2, p3, p4));

		System.out.println(list2);// ݔ���@���˵Č���

		// ����Dawn�Č���
		List<? extends Pet> l = list2.get(in);
		System.out.println(l);

	}
}
/**
 * output: {Individual �|������=[Pet [name=EgyptianMan], Pet [name=�����è], Pet
 * [name=�͸�Ȯ]], Individual Dawn=[Pet [name=è], Pet [name=��], Pet
 * [name=EgyptianMan], Pet [name=�����è], Pet [name=�͸�Ȯ]]} [Pet [name=è], Pet
 * [name=��], Pet [name=EgyptianMan], Pet [name=�����è], Pet [name=�͸�Ȯ]]
 */
