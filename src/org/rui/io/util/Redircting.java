package org.rui.io.util;

import java.io.*;

/**
 * ��׼I/O�ض���
 */
public class Redircting {
	public static void main(String[] args) throws IOException {
		String path = "D:\\Users\\liangrui\\workspace\\thinking\\src\\org\\rui\\io\\util/";
		PrintStream console = System.out;
		// ����
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				path + "Redircting.java"));
		// ���
		PrintStream out = new PrintStream(new BufferedOutputStream(
				new FileOutputStream(path + "testf.out")));
		// �������ǶԱ�׼������� �ʹ���IO�������ض���
		System.setIn(in);
		// System.setOut(out);
		// System.setErr(out);

		// ��ӡ�ö����������
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;

		while ((s = br.readLine()) != null)
			System.out.println(s);
		out.close();// remember this!
		System.setOut(console);
		;
	}

}
/**
 * output: ͬ��Դ��
 */

