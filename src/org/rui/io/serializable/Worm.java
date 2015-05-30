package org.rui.io.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * ���� ���л� ������Щ������ʹ�������ø��Ӹ��ӣ��Ӵ����Ѷȣ�Ȼ�������������л�����ȴ�Ƿǳ��򵥵�
 * 
 * @author lenovo
 * 
 */

class Data implements Serializable {
	private int n;

	public Data(int n) {
		this.n = n;
	}

	public String toString() {
		return Integer.toString(n);
	}
}

public class Worm implements Serializable {
	private static Random rand = new Random(47);
	private Data[] d = { new Data(rand.nextInt(10)),
			new Data(rand.nextInt(10)), new Data(rand.nextInt(10)) };

	private Worm next;
	private char c;

	// value of i==number of segments
	public Worm(int i, char x) {
		System.out.println("worm constructor;" + i);
		c = x;
		if (--i > 0)
			next = new Worm(i, (char) (x + 1));
	}

	//
	public Worm() {
		System.out.println("defalult constructor");
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(c);
		result.append("(");
		for (Data data : d)
			result.append(data);
		result.append(")");
		if (next != null)
			result.append(next);
		return result.toString();
	}

	// /////////////////////////////////////////
	public static void main(String[] args) throws Exception {
		Worm w = new Worm(6, 'a');
		System.out.println(" w:" + w);
		// ���л���
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				"worm.out"));
		out.writeObject("Worm storage\n");
		out.writeObject(w);
		out.close();
		// ��ȡ
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				"worm.out"));
		String s = (String) in.readObject();
		Worm worm = (Worm) in.readObject();
		System.out.println("s:" + s + " w2:" + worm);
		// ////////////////////////////////////////////////////////
		// �ٴ�д��
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject("Worm storage\n");
		oos.writeObject(w);
		oos.flush();

		// ��ȡ
		ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(
				bos.toByteArray()));
		s = (String) in2.readObject();
		Worm w3 = (Worm) in2.readObject();
		System.out.println("s:" + s + " w3:" + w3);
	}

}
/**
 * output: worm constructor;6 worm constructor;5 worm constructor;4 worm
 * constructor;3 worm constructor;2 worm constructor;1
 * w:a(853)b(119)c(802)d(788)e(199)f(881) s:Worm storage
 * w2:a(853)b(119)c(802)d(788)e(199)f(881) s:Worm storage
 * w3:a(853)b(119)c(802)d(788)e(199)f(881)
 */

/**
 * doc Java 1.1 ������һ����Ȥ�����ԣ���Ϊ���������л�����Object Serialization������������Щʵ���� Serializable
 * �ӿڵĶ��󣬿ɽ�����ת����һϵ���ֽڣ��������Ժ���ȫ�ָ���ԭ�������ӡ���һ�������
 * ͨ��������С�����ζ�����л��������Զ���������ϵͳ��Ĳ��졣���仰˵����������Windows �����ϴ�
 * ��һ�����󣬶������л���Ȼ��ͨ�����緢��һ̨Unix ������Ȼ��������׼ȷ��������¡�װ�䡱�����ع�
 * �������ڲ�ͬ��������α�ʾ��Ҳ���ع����ֽڵ�˳����������κ�ϸ�ڡ�
 * ���䱾����˵����������л��Ƿǳ���Ȥ�ģ���Ϊ����������ʵ�֡����޳־û��������ס���־û�����ζ
 * �Ŷ���ġ�����ʱ�䡱����ȡ���ڳ����Ƿ�����ִ�С��������ڻ����桱�ڳ����ÿһ�ε���֮�䡣ͨ��
 * ���л�һ�����󣬽���д����̣��Ժ��ڳ������µ���ʱ���»ָ��Ǹ����󣬾���Բ��ʵ��һ�֡��־á�Ч
 * ����֮���Գ���Ϊ�����ޡ�������Ϊ������ĳ�֡�persistent�����־ã��ؼ��ּ򵥵صض���һ�����󣬲�
 * ��ϵͳ�Զ��տ���������ϸ�����⣨���ܽ������ܳ�Ϊ��ʵ�����෴���������Լ��ĳ�������ȷ�����л��� ��װ����
 * �����������˶������л��ĸ���󣬿��ṩ��������Ҫ���Ե�֧�֡�Java 1.1 �ġ�Զ�̷������á���RMI��
 * 
 * ʹ�������������������Ķ�����Ա��ֳ�������ڱ��ػ����ϵ���Ϊ������Ϣ����Զ�̶���ʱ����Ҫͨ���� �����л�����������ͷ���ֵ��RMI ���ڵ�15
 * �����������ۡ� ��������л�Ҳ��Java Beans ����ģ�������Java 1.1 ���롣ʹ��һ��Bean ʱ������״̬��Ϣͨ�������
 * �ڼ����úá����������Ժ�����״̬��Ϣ���뱣���������Ա���������Ժ�ָ������幤���ɶ������л� ��ɡ�
 * ��������л�����ǳ��򵥣�ֻ�����ʵ����Serializable �ӿڼ��ɣ��ýӿڽ���һ����ǣ�û�з������� ��Java 1.1
 * �У�����׼���඼�����˸ı䣬�Ա��ܹ����л��������а������ڻ����������͵�ȫ����װ �������м������Լ�������ණ��������Class
 * ����Ҳ�������л�����11 �½����˾���ʵ�ֹ��̣��� Ϊ���л�һ����������Ҫ����ĳЩOutputStream
 * ����Ȼ�����װ��ObjectOutputStream �����ڡ���
 * ʱ��ֻ�����writeObject()������ɶ�������л��������䷢�͸�OutputStream���෴�Ĺ����ǽ�һ�� InputStream
 * ��װ��ObjectInputStream �ڣ�Ȼ�����readObject()��������һ������������õ���ָ�� һ����������Object
 * �ľ�������Ա����������ͣ��Ա��ܹ�ֱ�����á� �������л��ر𡰴�������һ���ط��������������˶���ġ�ȫ��ͼ����������׷�ٶ����ڰ��������о��
 * ��������Щ���󣻽������ܶ�ÿ�������ڰ����ľ������׷�٣��Դ����ơ�������ʱ�����������Ϊ������
 * �����������������֮�������ӡ��������������˶���ľ�������Լ���Ա�������������в���һ�׶���
 * ���л����ƣ���ô�ڴ�����׷��������Щ����ʱ���ܻ��Ե÷ǳ��鷳������һ���棬����Java ��������л�
 * �ƺ��Ҳ���ʲôȱ�㣬�����뾡����Ҫ�Լ����֣��������Ż����㷨�Զ�ά������������������������Ӷ�
 * ���л����ƽ����˲��ԡ���������������Ӷ����һ����Worm������棩��ÿ��������Worm �е���һ����
 * �ӣ�ͬʱ�������ڲ�ͬ�ࣨData���Ķ������������ӣ�
 * 
 * ����Ȥ���ǣ�Worm �ڵ�Data ������������������ֳ�ʼ���ģ������㲻�û��ɱ�����������ĳ��ԭʼ�� Ϣ����ÿ��Worm �ζ���һ��Char
 * ��ǡ����Char �����ظ��������ӵ�Worm �б�ʱ�Զ������ġ�����һ�� Worm
 * ʱ������߹�����ϣ�����ж೤��Ϊ������һ�������next�����������ü�ȥ1 �ĳ���������Worm �� 318 ���������һ��next
 * ����򱣳�Ϊnull���գ�����ʾ�ѵִ�Worm ��β���� ��������в�������Ϊ�˼�������ĸ��ӳ̶ȣ��Ӵ�������л����Ѷȡ�Ȼ�������������л�����ȴ�Ƿǳ�
 * �򵥵ġ�һ��������ĳ�����ﴴ����ObjectOutputStream��writeObject()�ͻ����л�����ע��Ҳ����Ϊ һ��String
 * ����writeObject()�����ʹ����DataOutputStream ��ͬ�ķ���д�����л����������ͣ����� ����ͬ�Ľӿڣ��� ������������try
 * �鿴���������Ƶġ���һ����д�����ļ�������һ����д����һ��ByteArray���ֽ��� �飩�������ö��κ�DataInputStream
 * ����DataOutputStream �����л�����д�ض��Ķ��������ڹ������� ����һ�»ὲ������������Щ����������������
 * 
 * ���Կ�����װ���ԭ״�Ķ���ȷʵ������ԭ���Ǹ�������������������ӡ�
 * ע���ڶ�һ��Serializable�������л��������������װ��Ĺ����У���������κι�����������Ĭ�Ϲ��� ����������������ͨ����InputStream
 * ��ȡ�����ݻָ��ġ� ��ΪJava 1.1 ���Ե�һ�֣�����ע�⵽��������л����������µ�Reader ��Writer ��νṹ��һ���֣���
 * ��������ʽ��InputStream ��OutputStream �ṹ��������һЩ����ĳ����£����ò����ʹ���������͵Ĳ� �νṹ��
 */
