package concurrence;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wenda.zhuang
 * @Date 2020/5/8 23:21
 * @Description long 64位下线程安全？
 * @E-mail sis.nonacosa@gmail.com
 */
public class LongExample implements Runnable {

	private static long test = 0;

	private final long val;

	public LongExample(long val) {
		this.val = val;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			test = val;
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new LongExample(-1));
		Thread t2 = new Thread(new LongExample(0));

		System.out.println(Long.toBinaryString(-1));
		System.out.println(pad(Long.toBinaryString(0), 64));

		t1.start();
		t2.start();

		long val;
		while ((val = test) == -1 || val == 0) {
		}

		System.out.println(pad(Long.toBinaryString(val), 64));
		System.out.println(val);

		t1.interrupt();
		t2.interrupt();
	}

	// prepend 0s to the string to make it the target length
	private static String pad(String s, int targetLength) {
		int n = targetLength - s.length();
		for (int x = 0; x < n; x++) {
			s = "0" + s;
		}
		return s;
	}
}
