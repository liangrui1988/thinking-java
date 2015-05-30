package org.rui.io;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 更近一步创建一个目录工具，它可以在目录中穿行， 这是策略设计模式的另一个示例 Strategy
 * 接口内嵌在processFile中，使得如果你希望实现它，就必须实现ProcessFiles.Strategy 它为读者提供了更多的上下文信息。
 * 
 * 
 * 
 * @author lenovo
 * 
 */
public class ProcessFiles {

	// 策略
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
					if (fileArg.isDirectory())// 如果是目录
						processDirectoryTree(fileArg);
					else if (!arg.endsWith(ext))// 如果后缀不相同，则加上
						arg += "." + ext;// 文件后缀
					strategy.process(new File(arg).getCanonicalFile());// 依教规的

				}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// //////walk=步行
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
