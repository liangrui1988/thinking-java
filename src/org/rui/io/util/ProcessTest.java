package org.rui.io.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ProcessTest {
	public static void main(String[] args) throws IOException {
		// ������һ�������޸Ĺ��Ĺ���Ŀ¼�ͻ����������̵����ӣ�

		ProcessBuilder pb = new ProcessBuilder("myCommand", "myArg1", "myArg2");
		Map<String, String> env = pb.environment();
		env.put("VAR1", "myValue");
		env.remove("OTHERVAR");
		env.put("VAR2", env.get("VAR1") + "suffix");

		System.out.println(env);
		pb.directory(new File("myDir"));
		Process p = pb.start();

	}

}
