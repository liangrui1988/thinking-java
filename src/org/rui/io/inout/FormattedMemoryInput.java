package org.rui.io.inout;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * ���Ի����ڴ�����
 * 
 * @author lenovo
 * 
 */
public class FormattedMemoryInput {

	public static void main(String[] args) {
		try {
			String path = "D:\\Users\\liangrui\\workspace\\thinking\\src\\org\\rui\\io\\inout/";
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(
					BufferedInputFile.read(path + "FormattedMemoryInput.java")
							.getBytes()));

			while (true)
				System.out.print((char) in.readByte());// ����ֵ���ܼ��������Ƿ����

		} catch (Exception e) {
			System.out.println("end of stream");
		}
	}
}
