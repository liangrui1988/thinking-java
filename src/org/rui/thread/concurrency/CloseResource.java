package org.rui.thread.concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 关闭任务在其上发生阻塞的底层资源
 * 
 * @author lenovo
 * 
 */
public class CloseResource {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		InputStream is = new Socket("localhost", 8080).getInputStream();
		exec.execute(new IOBlocked(is));
		exec.execute(new IOBlocked(System.in));
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("shutting down all threads");
		exec.shutdownNow();
		TimeUnit.MILLISECONDS.sleep(1);
		System.out.println("closing " + is.getClass().getName());
		is.close();// releases blocked thread

		TimeUnit.MILLISECONDS.sleep(1);
		System.out.println("closing " + System.in.getClass().getName());
		System.in.close();// releases blocked thread
	}

}
