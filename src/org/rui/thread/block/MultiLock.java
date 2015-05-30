package org.rui.thread.block;

/**
 * 被互斥阻塞 就像在interrupting.java中看到的，如果你偿试着在一个对象上调用其synchronized方法，
 * 而这个对象的锁已经被其他任务获得，那么调用任务将被挂起（阻塞） ，直至这个锁可获得. 
 * 下面的示例说明了同一个互斥可以如何能被同一个任务多次获得
 * 
 * @author lenovo
 * 
 */
public class MultiLock {
	public synchronized void f1(int count) {
		if (count-- > 0) {
			System.out.println("f1() calling f2() with count " + count);
			f2(count);
		}
	}

	public synchronized void f2(int count){
		if(count-->0){
			System.out.println("f2() calling f1() with count "+count);
			f1(count);
		}
	}
	
	public static void main(String[] args) {
		final MultiLock multiLock=new MultiLock();
		new Thread(){
			public void run(){
				multiLock.f1(10);
			}
		}.start();
	}
}
/**OUTPUT:
f1() calling f2() with count 9
f2() calling f1() with count 8
f1() calling f2() with count 7
f2() calling f1() with count 6
f1() calling f2() with count 5
f2() calling f1() with count 4
f1() calling f2() with count 3
f2() calling f1() with count 2
f1() calling f2() with count 1
f2() calling f1() with count 0
*/