package org.rui.io.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ��ȡ�������ļ�
 * 
 * @author lenovo
 * 
 */
public class BinaryFile {

	public static byte[] read(File bFile) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				bFile));
		try {
			// ����ǡ������ߴ�
			byte[] data = new byte[bis.available()];
			bis.read(data);
			return data;
		} finally {
			bis.close();
		}
	}

	public static byte[] read(String bF) throws IOException {
		// ����
		return read(new File(bF).getAbsoluteFile());// getAbsoluteFile
													// ���ش˳���·�����ľ���·������ʽ��
	}

}
