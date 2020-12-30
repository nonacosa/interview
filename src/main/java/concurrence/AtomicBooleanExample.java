package concurrence;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wenda.zhuang
 * @Date 2020/5/8 23:21
 * @Description 并发中控制某段代码只执行一次
 * @E-mail sis.nonacosa@gmail.com
 */
public class AtomicBooleanExample {

	private static AtomicBoolean isHappened = new AtomicBoolean(false);

	public static int clientTotal = 10000;

	public static int threadTotal = 50;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Semaphore semaphore = new Semaphore(threadTotal);
		CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0 ;i < clientTotal; i++) {
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					if(isHappened.compareAndSet(false,true)) {
						System.out.println("只执行一次");
					}
					semaphore.release();
					countDownLatch.countDown();

				} catch (Exception e) {

				}
			});

		}

		countDownLatch.await();
		executorService.shutdown();
	}
}
