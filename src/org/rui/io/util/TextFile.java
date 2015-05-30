package org.rui.io.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * �ļ���д��ʵ�ù���
 * 
 * @author lenovo
 * 
 */
public class TextFile extends ArrayList<String> {

	// ��ȡһ���ļ����������ַ���
	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();

		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(
					fileName).getAbsoluteFile())// ���ش˳���·�����ľ���·������ʽ��
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
		// ���뼯�ϣ�
		super(Arrays.asList(read(fileName).split(splitter)));
		if (get(0).equals(""))
			remove(0);
	}

	public TextFile(String fileName) {
		// ���ص�ǰ������
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
		// ·���������ѵĸ�
		String path = "D:\\Users\\liangrui\\workspace\\thinking\\src\\org\\rui\\io\\util/";

		String fileText = read(path + "TextFile.java");// ��ȡ��ǰjava�ı�
		// д���ļ�
		write(path + "newText.txt", fileText);
		// ���� д
		TextFile tf = new TextFile(path + "newText.txt");// �ٴζ�ȡд������ı�
		tf.write(path + "newText2.txt");// �ٴ�д��

		// ��ȡ�ı�������TreeSet������
		TreeSet<String> words = new TreeSet<String>(new TextFile(path
				+ "TextFile.java", "\\W+")// \W���ַ�
		);
		// headSet ���ش� set �Ĳ�����ͼ����Ԫ���ϸ�С�� toElement��
		System.out.println(words.headSet("a"));

	}
}
/**
 * output: [0, ArrayList, Arrays, BufferedReader, D, Exception, File,
 * FileReader, PrintWriter, RuntimeException, String, StringBuilder, System,
 * TextFile, TreeSet, Users, W]
 */
