package org.rui.io.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * ��System.out ת����PrintWriter System.out��һ��printStream
 * ��printStream��һ��outputstream PrintWriter ��һ�����Խ���outputstream��Ϊ�����Ĺ�����
 */
public class ChangeSystemOut {
	public static void main(String[] args) throws IOException {
		// true �Զ���չ���
		PrintWriter out = new PrintWriter(System.out, true);
		out.println("Hello, wrold");
	}
}
/**
 * output: Hello, wrold
 */
