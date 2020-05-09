package algorithm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wenda.zhuang
 * @Date 2020/4/26 8:29 PM
 * @Description ...
 * @E-mail   sis.nonacosa@gmail.com
 */
public class FooBar {

	static class printFoo implements Runnable{

		FooBar fb ;

		public printFoo(FooBar fb) {
			this.fb = fb;
		}

		@Override
		// Runnable 必须override run()
		public void run() {
			try {
				fb.foo(() -> System.out.print("Foo"));
				// lambda表达式实现override run() 打印"Foo"
				// due to 题目要求foo方法要传入一个Runnable printFoo
				// 而且printFoo.run()是打印"Foo"
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class printBar implements Runnable{
		FooBar fb;

		public printBar(FooBar fb) {
			this.fb = fb;
		}

		@Override
		// 同上
		public void run() {
			try {
				fb.bar(() -> System.out.print("Bar"));
				// 同上
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		FooBar fb = new FooBar(999);
		Thread t1 = new Thread(new printFoo(fb));
		// 一个线程 参数是实现了Runnable的对象
		Thread t2 = new Thread(new printBar(fb));
		// 第二个线程 参数是实现了Runnable的对象
		// 题目要求这个两个线程共享FooBar对象fb

		t2.start();  // 注意不能是run()
		t1.start();  // 注意不能是run()
	}

	private int n;
	private Lock lock=new ReentrantLock();
	private Condition condition=lock.newCondition();
	//模拟生产者消费者模式，用于判断共享变量是否为空
	private boolean isEmpty=true;

	public FooBar(int n) {
		this.n = n;
	}

	//类似生产者offer数据
	public void foo(Runnable printFoo) throws InterruptedException {

		lock.lock();

		for (int i = 0; i < n; i++) {

			while(!isEmpty){
				condition.await();
			}
			// printFoo.run() outputs "foo". Do not change or remove this line.
			printFoo.run();
			isEmpty=false;

			condition.signal();

		}
		lock.unlock();

	}

	//类似消费者take数据
	public void bar(Runnable printBar) throws InterruptedException {
		lock.lock();

		for (int i = 0; i < n; i++) {

			while(isEmpty){
				condition.await();
			}
			// printBar.run() outputs "bar". Do not change or remove this line.
			printBar.run();
			isEmpty=true;

			condition.signal();

		}
		lock.unlock();

	}
}
