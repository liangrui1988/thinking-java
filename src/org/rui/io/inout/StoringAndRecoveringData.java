package org.rui.io.inout;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * �洢�ͻָ����� �������ʹ����dataoutputstreamд�����ݣ�java��֤���ǿ���ʹ��datainputstream׼ȷ�ض�ȡ���ݣ�
 * ���۶���д����ƽ̨��ô��ͬ utf-8��һ�ֶ��ֽڸ�ʽ������볤�ȸ���ʵ��ʹ�õ��ַ����������仯��������ǵ�ֻ��ASCIIֻռ7λ
 * �˷ѿռ�ʹ�������utf-8��ASCII�ַ�����ɵ�һ�ֽڵ���ʽ������ASCII�ַ����������������ֽڵ���ʽ
 * 
 * @author lenovo
 * 
 */
public class StoringAndRecoveringData {
	public static void main(String[] args) throws Exception {
		String path = "d:/data.txt";
		// ���
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
				new FileOutputStream(path)));
		out.writeDouble(5.55);
		out.writeUTF("hello world");
		out.writeDouble(6.66);
		out.writeUTF("���  ����");
		out.close();
		// ��ȡ
		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream(path)));

		//
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());

	}
}
/**
 * output: 5.55 hello world 6.66 ��� ����
 */
