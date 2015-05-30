package org.rui.io.inout;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * ��д��������ļ�
 * 
 * ʹ��RandomAccessFileʱ�������֪���ļ��Ű棬����������ȷ�ز����� ӵ�ж�ȡ�������ͺ�utf-8�ַ����ĸ��־��巽��������ʾ��
 * 
 * ����ܻῼ�� ʹ�� �ڴ�ӳ���ļ� ������randomaccessfile
 * 
 * @author lenovo
 * 
 */
public class UsingRandomAccessFile {
	static String file = "rtest.dat";

	static void display() throws Exception {
		RandomAccessFile rf = new RandomAccessFile(file, "r");
		for (int i = 0; i < 7; i++)
			System.out.println("value " + i + " : " + rf.readDouble());
		System.out.println(rf.readUTF());
		rf.close();
	}

	public static void main(String[] args) throws Exception {
		RandomAccessFile rf = new RandomAccessFile(file, "rw");
		// д
		for (int i = 0; i < 7; i++)
			rf.writeDouble(i * 1.414);
		rf.writeUTF(" the end of the file");
		rf.close();

		// ��ӡ
		display();
		// д �򿪲��޸�
		rf = new RandomAccessFile(file, "rw");
		rf.seek(5 * 8);
		rf.writeDouble(47.0001);
		rf.close();
		// ��ӡ
		display();
	}
}
/**
 * output: value 0 : 0.0 value 1 : 1.414 value 2 : 2.828 value 3 : 4.242 value 4
 * : 5.656 value 5 : 7.069999999999999 value 6 : 8.484 the end of the file value
 * 0 : 0.0 value 1 : 1.414 value 2 : 2.828 value 3 : 4.242 value 4 : 5.656 value
 * 5 : 47.0001 value 6 : 8.484 the end of the file
 */
