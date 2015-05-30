package org.rui.io.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 进程控制
 * 
 * 你经常会需要在java内部执行其它操作系统程序，并且要控制这此程序的输入和输出，java类库提供了执行这些操作的类
 * 
 * 为了捕获程序执行时产生的标准输出流，你需要调用getInputStream()，这是因为。。。是我们可以从中读取信息的流。 从程序
 * 中产生的结果每次输出一行，因此要使用readLine
 * 
 */
public class OSExecuted {

	public static void command(String command) {
		boolean err = false;
		try {
			/**
			 * 此类用于创建操作系统进程。 每个 ProcessBuilder 实例管理一个进程属性集。start()
			 * 方法利用这些属性创建一个新的 Process 实例。 start()
			 * 方法可以从同一实例重复调用，以利用相同的或相关的属性创建新的子进程。
			 */
			Process process = new ProcessBuilder(command.split(" ")).start();
			// 这里还可以返回它们，暂只打印
			BufferedReader br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String s;
			while ((s = br.readLine()) != null)
				System.out.println(s);

			// 捕获错误流
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
