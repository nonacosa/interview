package concurrence.count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author wenda.zhuang
 * @Date 2020/5/9 1:23 PM
 * @Description ...
 * @E-mail   sis.nonacosa@gmail.com
 */
public class CountSynchronizedExample {

	private static int count = 0;
	private volatile static int count2 = 0;

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Semaphore semaphore = new Semaphore(200);
		CountDownLatch countDownLatch = new CountDownLatch(100000);
		for (int i = 0; i< 100000; i ++) {
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					add();
					count2++;
					semaphore.release();
					countDownLatch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			});
		}
		countDownLatch.await();
		executorService.shutdown();

		System.out.println(count);
		System.out.println(count2);
	}

	// synchronized JVM 级别锁。
	// http://codefun007.xyz/a/article_detail/1041.htm
	private synchronized static void add() {
		count ++;
	}
}
