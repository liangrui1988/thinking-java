package org.rui.io.inout;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * 格试化的内存输入
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
				System.out.print((char) in.readByte());// 返回值不能检测输入的是否结束

		} catch (Exception e) {
			System.out.println("end of stream");
		}
	}
}
