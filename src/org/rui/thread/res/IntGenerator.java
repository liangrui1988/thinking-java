package org.rui.thread.res;

/**
 * 不正确的访问 资源
 * 
 * @author lenovo
 * 
 */
public abstract class IntGenerator {

	private volatile boolean canceled = false;// 取消的

	public abstract int next();

	// 允许这种被取消
	public void cancel() {
		canceled = true;
	}

	public boolean isCanceled() {
		return canceled;
	}

}
