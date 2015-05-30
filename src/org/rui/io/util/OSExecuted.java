package org.rui.io.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * ���̿���
 * 
 * �㾭������Ҫ��java�ڲ�ִ����������ϵͳ���򣬲���Ҫ������˳��������������java����ṩ��ִ����Щ��������
 * 
 * Ϊ�˲������ִ��ʱ�����ı�׼�����������Ҫ����getInputStream()��������Ϊ�����������ǿ��Դ��ж�ȡ��Ϣ������ �ӳ���
 * �в����Ľ��ÿ�����һ�У����Ҫʹ��readLine
 * 
 */
public class OSExecuted {

	public static void command(String command) {
		boolean err = false;
		try {
			/**
			 * �������ڴ�������ϵͳ���̡� ÿ�� ProcessBuilder ʵ������һ���������Լ���start()
			 * ����������Щ���Դ���һ���µ� Process ʵ���� start()
			 * �������Դ�ͬһʵ���ظ����ã���������ͬ�Ļ���ص����Դ����µ��ӽ��̡�
			 */
			Process process = new ProcessBuilder(command.split(" ")).start();
			// ���ﻹ���Է������ǣ���ֻ��ӡ
			BufferedReader br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String s;
			while ((s = br.readLine()) != null)
				System.out.println(s);

			// ���������
			BufferedReader b2 = new BufferedReader(new InputStreamReader(
					process.getErrorStream()));
			while ((s = b2.readLine()) != null) {
				System.err.println("sssssssss:" + s);
				err = true;
			}

		} catch (Exception e) {
			System.out.println("command Ex ==" + command);
			if (command.startsWith("CMD /C"))
				command("CMD /C" + command);
			else
				throw new RuntimeException(e);
		}

		if (err)
			throw new OSExecuteException(" Errors execting " + command);
	}

}
