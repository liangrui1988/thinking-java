package org.rui.collection2.hashcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ����hashcode
 * 
 * ��Effective java programming language guide ....�Ȿ�� Ϊ����д��һ��������hashcode�����˻���ָ��
 * 1��int����result ����ĳ������ֵ �� ���� 2��Ϊ������ÿ�����������f����ÿ��������equlas�������� ����һ��intɢ����c
 * -----------------------------------------
 * ----------------------------------------- ������ ����
 * -----------------------------------------
 * ----------------------------------------- boolean c=(f?0:1)
 * byte,char,shor��int c=(int)f long c=(int)(f^(f>>>32)) float
 * c=Float.floatToIntBits(f); ���� IEEE 754 ���㡰��һ��ʽ��λ���֣�����ָ������ֵ�ı�ʾ��ʽ double
 * c=Double.doubleToLongBits(f); Object ��equals����������equlas c=f.hashCode(); ����
 * ��ÿ��Ԫ��Ӧ����������
 * 
 * 3,�ϲ�����õ���ɢ���� 4������result 5���hashcode������Ľ����ȷ����ͬ�ĵ�������ͬ��ɢ����
 * -----------------------------------------
 * -----------------------------------------
 * 
 * 
 * CountedString����һ��String��id��ɣ���id���������ͬString��CountedString����ı��
 * ���е�String���洢��ArrayList�У��ڹ�������ͨ������������ArrayList��ɶ�Id�ļ���
 * 
 * hashcode and equals���ǻ���CountedString���� ���������ɵĽ�����������ֻ�ǻ���String��ֻ����Id
 * ��ͬ�Ķ���Ϳ��ܲ�����ͬ��ֵ
 * 
 * ��Main��ʹ������ͬ��String�������˶��CountedString������˵������ȻString��ͬ ��
 * ������Id��ͬ������ʹ�����ǵ�ɢ���벢����ͬ��
 * 
 * @author lenovo
 * 
 */
public class CountedString {
	private static List<String> created = new ArrayList<String>();
	private String s;
	private int id = 0;

	public CountedString(String str) {
		s = str;
		created.add(s);
		// id is the total numbe of instances
		// of this string in by CountedString
		for (String s2 : created) {
			if (s2.equals(s))
				id++;
		}
	}

	public String toString() {
		return "String:" + s + "  id:" + id + " hashCode:" + hashCode();
	}

	public int hashCode() {
		// the very simple approach
		// return s.hashCode()*id;
		// using joshua bloch's recipe//ʹ��joshua bloch���䷽
		int result = 17;
		// �ϲ�����õ�ɢ����
		result = 37 * result + s.hashCode();
		result = 37 * result + id;
		return result;
	}

	public boolean equals(Object o) {
		return o instanceof CountedString && s.equals(((CountedString) o).s)
				&& id == ((CountedString) o).id;

	}

	public static void main(String[] args) {
		Map<CountedString, Integer> map = new HashMap<CountedString, Integer>();
		CountedString[] cs = new CountedString[5];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = new CountedString("hi");
			map.put(cs[i], i);// autobox int->Integer
		}
		System.out.println(map);
		for (CountedString cstring : cs) {
			System.out.println("Looking up" + cstring);
			System.out.println("map.get(cstring):" + map.get(cstring));
		}
	}

}
