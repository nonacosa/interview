

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author wenda.zhuang
 * @Date 2020/5/8 23:34
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
public class Test {

	private static AtomicIntegerFieldUpdater<Test> updater =
			AtomicIntegerFieldUpdater.newUpdater(Test.class, "count");


	public volatile int count = 100;

	public int getCount() {
		return this.count;
	}
	public static void main(String[] args) {

		Test example5 = new Test();

		if (updater.compareAndSet(example5, 100, 120)) {
			System.out.println("111111");
			System.out.println(example5.getCount());
		}

		if (updater.compareAndSet(example5, 100, 120)) {
			System.out.println("2222222");
			System.out.println(example5.getCount());
		} else {
			System.out.println("failed");
		}
	}

}
