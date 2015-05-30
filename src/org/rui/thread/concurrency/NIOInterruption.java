package org.rui.thread.concurrency;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 被阻塞的的nio 通道会自动地响应中断
 * 
 * @author lenovo
 * 
 */

class NIOBlocked implements Runnable {
	private final SocketChannel sc;

	public NIOBlocked(SocketChannel sc) {
		this.sc = sc;
	}

	@Override
	public void run() {
		System.out.println("waiting for read:" + this);
		try {
			sc.read(ByteBuffer.allocate(1));
		} catch (ClosedByInterruptException e) {
			System.out.println("ClosedByInterruptException");
		} catch (AsynchronousCloseException e) {
			System.out.println("AsynchronousCloseException");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("exiting nioblocked.run() " + this);
	}

}

public class NIOInterruption {
	public static void main(String[] args) throws IOException,
			InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		InetSocketAddress isa = new InetSocketAddress("localhost", 8080);

		SocketChannel sc1 = SocketChannel.open(isa);
		SocketChannel sc2 = SocketChannel.open(isa);
		Future<?> f = exec.submit(new NIOBlocked(sc1));

		exec.execute(new NIOBlocked(sc2));
		exec.shutdown();
		TimeUnit.MILLISECONDS.sleep(1);
		//通过取消产生一个中断
		f.cancel(true);
		TimeUnit.MILLISECONDS.sleep(1);
		//release the block by closing the channel 释放块通过关闭通道
		sc2.close();

	}
}
/**
 * output:
waiting for read:org.rui.thread.concurrency.NIOBlocked@fced4
waiting for read:org.rui.thread.concurrency.NIOBlocked@1d3cdaa
ClosedByInterruptException
exiting nioblocked.run() org.rui.thread.concurrency.NIOBlocked@fced4
AsynchronousCloseException
exiting nioblocked.run() org.rui.thread.concurrency.NIOBlocked@1d3cdaa

*/
