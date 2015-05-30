package org.rui.thread.basic;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 从任务中产生返回值 Callable接口应用 也可以用isDone()来查询Future是否已经完成 也可以直接get（）进行检查
 * 
 * @author lenovo
 * 
 */

class TaskWithResult implements Callable<String> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		return "result of TaskWotjResult" + id;
	}
}

public class CallableDemo {
	// ///////////////
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> result = new ArrayList<Future<String>>();
		for (int i = 0; i < 10; i++) {
			result.add(exec.submit(new TaskWithResult(i)));
		}

		for (Future<String> fs : result)
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				exec.shutdown();
			}

	}

}

/**
 * output: result of TaskWotjResult0 result of TaskWotjResult1 result of
 * TaskWotjResult2 result of TaskWotjResult3 result of TaskWotjResult4 result of
 * TaskWotjResult5 result of TaskWotjResult6 result of TaskWotjResult7 result of
 * TaskWotjResult8 result of TaskWotjResult9
 */
