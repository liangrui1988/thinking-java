package org.rui.io.xml;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

/**
 * ���л���XML�� ʹ��xom��������ת��Ϊxml��Element����� person����
 * 
 * http://www.xom.nu/ XOM��ȻҲ��һ����������XML
 * API��������DOM�ķ�񣬵�������һЩ���ڲ�ͬ�����ԣ������ϸ񱣳��ڴ��ж���Ĳ����ԣ� �Ӷ�ʹXOMʵ�����������л�Ϊ��ȷ��XML�����⣬������Java
 * XML API��ȣ�XOM׷����򵥺͸����档
 * 
 * 
 */
public class Person {

	private String first, last;

	public Person(String first, String last) {
		this.first = first;
		this.last = last;
	}

	// ת����ǰ����Ϊxml
	public Element getXml() {
		Element person = new Element("person");

		Element firstName = new Element("first");
		firstName.appendChild(first);
		person.appendChild(firstName);

		Element lastNaem = new Element("last");
		lastNaem.appendChild(last);
		person.appendChild(lastNaem);

		return person;
	}

	// ////////////////////////////
	public Person(Element person) {
		first = person.getFirstChildElement("first").getValue();
		last = person.getFirstChildElement("last").getValue();
	}

	// ////////////////////////////
	@Override
	public String toString() {
		return "Person [first=" + first + ", last=" + last + "]";
	}

	// ////////////////////////////��doc���л�
	public static void format(OutputStream os, Document doc) throws Exception {
		Serializer serializer = new Serializer(os, "ISO-8859-1");
		serializer.setIndent(4);
		serializer.setMaxLength(60);
		serializer.write(doc);
		serializer.flush();
	}

	// /////main
	public static void main(String[] args) throws Exception {
		// ʵ�ж����б�
		List<Person> list = Arrays.asList(new Person("Dr.Bunsen", "heneydew"),
		// new Person("����","����"),
				new Person("ddd", "ffff"), new Person("kkkk", "jjjjj"));
		System.out.println(list);

		// ����xml
		Element root = new Element("people");
		for (Person p : list)
			root.appendChild(p.getXml());
		Document doc = new Document(root);
		format(System.out, doc);
		// ���л�
		format(new BufferedOutputStream(new FileOutputStream("people.xml")),
				doc);

	}
}

/**
 * [Person [first=Dr.Bunsen, last=heneydew], Person [first=����, last=����], Person
 * [first=kkkk, last=jjjjj]] <?xml version="1.0" encoding="ISO-8859-1"?>
 * <people> <person> <first>Dr.Bunsen</first> <last>heneydew</last> </person>
 * <person> <first>&#x4E1C;&#x65B9;</first> <last>&#x4E0D;&#x8D25;</last>
 * </person> <person> <first>kkkk</first> <last>jjjjj</last> </person> </people>
 */

