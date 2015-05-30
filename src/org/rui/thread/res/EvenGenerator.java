package org.rui.thread.res;

/**
 * 这个程序最终会失败，因为evenChecker任务在evenGenerator处于 不恰当的 状态时 仍能够访问其中信息
 * 如果你希望更快地发现失败，可以尝试着将yield() 的调用放置到第一个和第二个递增操作之间。 这只是并发程序的部分部题
 * 
 * @author lenovo
 * 
 */
public class EvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;

	@Override
	public int next() {
		++currentEvenValue;// 危险项目》的出版物! danger point here
		++currentEvenValue;
		return currentEvenValue;

	}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}

}
/**
 * output: press control-c to exit 13103419 not even!
 */
