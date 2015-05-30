package org.rui.ExceptionTest;

/**
 * �����׳��쳣
 * 
 * ��ĳЩ����£����������������ղŲ�������Υ�����ر�������Exception �������п��ܵ�Υ��ʱ��������
 * ����ӵ�е�ǰΥ���ľ��������ֻ��򵥵����������Ǹ�������ɡ�������һ�����ӣ� catch(Exception e) {
 * System.out.println("һ��Υ���Ѿ�����"); throw e; }
 * ���¡�������һ��Υ������Υ���������һ��������Υ���������С�����ͬһ��try ����κθ���һ���� catch
 * �Ӿ���Ȼ�ᱻ���ԡ����⣬��Υ�������йص����ж�������õ��������������ڲ����ض�Υ�����͵� ����һ���Ŀ��������Դ��Ǹ���������ȡ��������Ϣ��
 * ��ֻ�Ǽ򵥵�����������ǰΥ�������Ǵ�ӡ�����ġ���printStackTrace()�ڵ��Ǹ�Υ���йص���Ϣ����Υ
 * ������Դ�ض�Ӧ���������������������ĵص��Ӧ�����밲װ�µĶ�ջ������Ϣ���ɵ���
 * fillInStackTrace()�����᷵��һ�������Υ���������Υ���Ĵ����������£�����ǰ��ջ����Ϣ��䵽 ԭ����Υ������������г�������ʽ��
 * 
 * @author lenovo
 * 
 */
public class Rethrowing {
	public static void f() throws Exception {
		System.out.println("f() ����ִ��");
		throw new Exception("thrown form f()");
	}

	public static void g() throws Exception {
		try {
			f();
		} catch (Exception e) {
			System.out.println("inside g() ,e...");
			e.printStackTrace(System.out);
			throw e;// �Ѳ�����쳣 �����׳��쳣
		}
	}

	public static void main(String[] args) {
		try {
			g();
		} catch (Exception e) {
			System.out.println("main �����쳣 ");
			e.printStackTrace(System.out);
		}

	}

}
/**
 * f() ����ִ�� inside g() ,e... java.lang.Exception: thrown form f() at
 * org.rui.ExceptionTest.Rethrowing.f(Rethrowing.java:7) at
 * org.rui.ExceptionTest.Rethrowing.g(Rethrowing.java:13) at
 * org.rui.ExceptionTest.Rethrowing.main(Rethrowing.java:25) main �����쳣
 * java.lang.Exception: thrown form f() at
 * org.rui.ExceptionTest.Rethrowing.f(Rethrowing.java:7) at
 * org.rui.ExceptionTest.Rethrowing.g(Rethrowing.java:13) at
 * org.rui.ExceptionTest.Rethrowing.main(Rethrowing.java:25)
 */
