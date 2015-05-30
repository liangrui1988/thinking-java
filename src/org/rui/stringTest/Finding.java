package org.rui.stringTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finding {
	public static void main(String[] args) {
		// \\w���ַ�����Ϊ����
		Matcher m = Pattern.compile("\\w+").matcher(
				"Evening is full of the linnet's wings");
		// find() ���Ҷ��ƥ��
		while (m.find())
			System.out.println(m.group() + " ");
		// groups a(b(c))d �������� ��0=abcd ��1=bc ��2=c
		System.out.println("============");
		int i = 0;
		while (m.find(i)) {
			System.out.print(m.group() + ">>");
			i++;
		}
		System.out.println(i);
	}

}
