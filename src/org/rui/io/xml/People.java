package org.rui.io.xml;

import java.io.File;
import java.util.ArrayList;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Elements;

/**
 * �����л�xml xom�ķ����������൱���Խ����ԣ�������xom�ĵ����ҵ����ǣ�
 * 
 * @author lenovo
 * 
 */
public class People extends ArrayList<Person> {
	public People(String fileName) throws Exception {
		Document doc = new Builder().build(new File(fileName));// �򿪶�ȡ�ļ�
		Elements elements = doc.getRootElement().getChildElements();
		for (int i = 0; i < elements.size(); i++) {// ת��xmlΪ����
			add(new Person(elements.get(i)));
		}

	}

	public static void main(String[] args) throws Exception {
		// String path="D:\\Users\\liangrui\\workspace\\thinking/";
		// People people=new People(path+"people.xml");
		People people = new People("people.xml");
		System.out.println(people);
	}

}
/**
 * [Person [first=Dr.Bunsen, last=heneydew], Person [first=ddd, last=ffff],
 * Person [first=kkkk, last=jjjjj]]
 */

