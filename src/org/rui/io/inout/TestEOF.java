package org.rui.io.inout;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * ������ʾ������һ��һ���ֽڵصĶ�ȡ�ļ�
 * 
 * @author lenovo
 * 
 */
public class TestEOF {

	public static void main(String[] args) throws Exception {

		String path = "D:\\Users\\liangrui\\workspace\\thinking\\src\\org\\rui\\io\\inout/";
		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream(path + "TestEOF.java")));
		// ע�� ������������ý�����͵Ĳ�ͬ��������ͬ���������˼���� ��û����������������ܶ�ȡ���ֽ���
		// �����ļ� ����ζ�������ļ������Ƕ��ڲ�ͬ���͵��������ܾͲ��������ģ����Ҫ����ʹ��
		while (in.available() != 0)
			System.out.print((char) in.readByte());

	}
}
