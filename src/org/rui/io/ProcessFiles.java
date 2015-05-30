package org.rui.io;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * ����һ������һ��Ŀ¼���ߣ���������Ŀ¼�д��У� ���ǲ������ģʽ����һ��ʾ�� Strategy
 * �ӿ���Ƕ��processFile�У�ʹ�������ϣ��ʵ�������ͱ���ʵ��ProcessFiles.Strategy ��Ϊ�����ṩ�˸������������Ϣ��
 * 
 * 
 * 
 * @author lenovo
 * 
 */
public class ProcessFiles {

	// ����
	public interface Strategy {
		void process(File file);
	}

	private Strategy strategy;
	private String ext;

	public ProcessFiles(Strategy strategy, String ext) {
		this.strategy = strategy;
		this.ext = ext;
	}

	public void start(String[] args) {
		try {
			if (args.length == 0)
				processDirectoryTree(new File("."));
			else
				for (String arg : args) {
					File fileArg = new File(arg);
					if (fileArg.isDirectory())// �����Ŀ¼
						processDirectoryTree(fileArg);
					else if (!arg.endsWith(ext))// �����׺����ͬ�������
						arg += "." + ext;// �ļ���׺
					strategy.process(new File(arg).getCanonicalFile());// ���̹��

				}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// //////walk=����
	private void processDirectoryTree(File root) throws IOException {
		for (File file : Directory.TreeInfo.walk(root.getAbsoluteFile(),
				".*\\." + ext))
			strategy.process(file.getCanonicalFile());

	}

	// demonstration of how to use
	public static void main(String[] args) {

		new ProcessFiles(new ProcessFiles.Strategy() {
			public void process(File file) {
				System.out.println(file);
			}
		}, "java").start(args);
		;
	}

}
/**
 * output: D:\Users\liangrui\workspace\thinking\src\Concatenation.java
 * D:\Users\liangrui
 * \workspace\thinking\src\org\rui\array\AssemblingMultidimensionalArrays.java
 * D:\Users\liangrui\workspace\thinking\src\org\rui\array\AutoboxingArrays.java
 * D:\Users\liangrui\workspace\thinking\src\org\rui\array\Bean.java
 * ....................... ..............
 */
