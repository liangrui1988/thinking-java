package org.rui.collection2.hashcode;

/**
 * ����hashcode ���HashCodeʱ����Ҫ������ ���ǣ����ۺ�ʱ����ͬһ���������HashCode��Ӧ�ò���ͬ����ֵ��
 * ������HashCode���������ڶ������ױ�����ݣ��û���Ҫ�����ˣ���Ϊ�����ݷ����仯 ʱ
 * HashCode�ͻ�����һ����ͬ��ɢ���룬�൱�ڲ���һ����ͬ�Ľ� ����
 * Ҳ��Ӧ��ʹHashCode�����ھ���Ψһ�ԵĶ�����Ϣ��������ʹ��this��ֵ����ֻ�ܺ���⣬
 * ��Ϊ�������޷�����һ���µĽ���ʹ����Put��ԭʼ�Ľ�ֵ���еĽ���ͬ������Ĭ�ϵ�HashCodeʹ�õ��Ƕ���ĵ�ַ ���� Ӧ�� ʹ�ö������������ʶ����Ϣ
 * 
 * ������String��Ϊ�� String���� ӳ�䵽ͬһ���ڴ��� ���� new String("hello") ���ɵ�����ʵ�� ����Ȼ���໥�����ģ�
 * ���Ƕ�����ʹ��HashCodeӦ������ͬ���Ľ��������ʾ�����Կ��� ��String���ԣ�HashCode�����ǻ���String�����ݵģ�
 * 
 * ��� Ҫ��ʹHashCodeʵ�ã������� �ٶȿ죬���ұ��������塣Ҳ����˵����������ڶ�����������ɵ�ɢ���룬 �ǵ���ɢ���벻���Ƕ�һ�޶���
 * ��Ӧ�ø���ע�����ٶȣ�������Ψһ�ԣ� ��HashCode��Equals �����ܹ���ȫȷ���������� ����ɢ�������ɵķ�Χ������Ҫ��ֻҪ��int����
 * ���б�һ��Ӱ�����أ��õ�HashCodeӦ�ò����ֲ����ȵ�ɢ����
 * 
 * 
 * @author lenovo
 * 
 */

public class StringHashCode {
	public static void main(String[] args) {
		String[] hello = "Hello Hello".split(" ");
		System.out.println(hello[0].hashCode());
		System.out.println(hello[1].hashCode());
	}
}
/**
 * output: 69609650 69609650
 */
