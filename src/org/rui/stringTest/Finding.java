package org.rui.stringTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finding {
	public static void main(String[] args) {
		// \\w字字符划分为单词
		Matcher m = Pattern.compile("\\w+").matcher(
				"Evening is full of the linnet's wings");
		// find() 查找多个匹配
		while (m.find())
			System.out.println(m.group() + " ");
		// groups a(b(c))d 中有三组 组0=abcd 组1=bc 组2=c
		System.out.println("============");
		int i = 0;
		while (m.find(i)) {
			System.out.print(m.group() + ">>");
			i++;
		}
		System.out.println(i);
	}

}
