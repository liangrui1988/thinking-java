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
 * 序列化到XML中 使用xom来产生被转换为xml的Element对象的 person数据
 * 
 * http://www.xom.nu/ XOM虽然也是一种面向对象的XML
 * API，类似于DOM的风格，但是它有一些与众不同的特性，比如严格保持内存中对象的不变性， 从而使XOM实例总是能序列化为正确的XML。此外，与其他Java
 * XML API相比，XOM追求更简单和更正规。
 * 
 * 
 */
public class Person {

	private String first, last;

	public Person(String first, String last) {
		this.first = first;
		this.last = last;
	}

	// 转换当前对像为xml
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

	// ////////////////////////////把doc序列化
	public static void format(OutputStream os, Document doc) throws Exception {
		Serializer serializer = new Serializer(os, "ISO-8859-1");
		serializer.setIndent(4);
		serializer.setMaxLength(60);
		serializer.write(doc);
		serializer.flush();
	}

	// /////main
	public static void main(String[] args) throws Exception {
		// 实列对象列表
		List<Person> list = Arrays.asList(new Person("Dr.Bunsen", "heneydew"),
		// new Person("东方","不败"),
				new Person("ddd", "ffff"), new Person("kkkk", "jjjjj"));
		System.out.println(list);

		// 构见xml
		Element root = new Element("people");
		for (Person p : list)
			root.appendChild(p.getXml());
		Document doc = new Document(root);
		format(System.out, doc);
		// 序列化
		format(new BufferedOutputStream(new FileOutputStream("people.xml")),
				doc);

	}
}

/**
 * [Person [first=Dr.Bunsen, last=heneydew], Person [first=东方, last=不败], Person
 * [first=kkkk, last=jjjjj]] <?xml version="1.0" encoding="ISO-8859-1"?>
 * <people> <person> <first>Dr.Bunsen</first> <last>heneydew</last> </person>
 * <person> <first>&#x4E1C;&#x65B9;</first> <last>&#x4E0D;&#x8D25;</last>
 * </person> <person> <first>kkkk</first> <last>jjjjj</last> </person> </people>
 */

