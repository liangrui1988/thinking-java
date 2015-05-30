package org.rui.io.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * 文件读写的实用工具
 * 
 * @author lenovo
 * 
 */
public class TextFile extends ArrayList<String> {

	// 读取一个文件，并返回字符串
	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();

		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(
					fileName).getAbsoluteFile())// 返回此抽象路径名的绝对路径名形式。
			);

			try {
				String s;
				while ((s = in.readLine()) != null)
					sb.append(s);
				sb.append("\n");

			} finally {
				in.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

	// write a single
	public static void write(String fileName, String text) {
		try {
			PrintWriter out = new PrintWriter(
					new File(fileName).getAbsoluteFile());
			try {
				out.print(text);

			} finally {
				out.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	// read a file
	public TextFile(String fileName, String splitter) {
		// 放入集合，
		super(Arrays.asList(read(fileName).split(splitter)));
		if (get(0).equals(""))
			remove(0);
	}

	public TextFile(String fileName) {
		// 重载当前构造器
		this(fileName, "\n");
	}

	public void write(String fileName) {
		try {
			PrintWriter out = new PrintWriter(
					new File(fileName).getAbsoluteFile());
			try {
				for (String item : this) {
					// System.out.println(item);
					out.println(item);
				}

			} finally {
				out.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	// ///simple test
	public static void main(String[] args) {
		// 路径根据自已的改
		String path = "D:\\Users\\liangrui\\workspace\\thinking\\src\\org\\rui\\io\\util/";

		String fileText = read(path + "TextFile.java");// 读取当前java文本
		// 写入文件
		write(path + "newText.txt", fileText);
		// 对象 写
		TextFile tf = new TextFile(path + "newText.txt");// 再次读取写入的新文本
		tf.write(path + "newText2.txt");// 再次写入

		// 读取文本并放入TreeSet集合中
		TreeSet<String> words = new TreeSet<String>(new TextFile(path
				+ "TextFile.java", "\\W+")// \W词字符
		);
		// headSet 返回此 set 的部分视图，其元素严格小于 toElement。
		System.out.println(words.headSet("a"));

	}
}
/**
 * output: [0, ArrayList, Arrays, BufferedReader, D, Exception, File,
 * FileReader, PrintWriter, RuntimeException, String, StringBuilder, System,
 * TextFile, TreeSet, Users, W]
 */
