import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author wenda.zhuang
 * @Date 2020/5/8 8:28 PM
 * @Description 线程池、 计数器、 信号量 简单的小例子
 * @E-mail   sis.nonacosa@gmail.com
 */

public class AtomicExample {

	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	//并发数
	public static int threadTotal = 100;
	//总数
	public static int clientTotal = 10000;

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Semaphore semaphore = new Semaphore(threadTotal);
		CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i< clientTotal; i++) {
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					atomicInteger.incrementAndGet();
					semaphore.release();
				} catch (Exception e) {
					System.out.println(e);
				}

				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		System.out.println(atomicInteger.get());
	}
}
