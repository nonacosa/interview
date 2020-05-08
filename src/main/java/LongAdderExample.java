import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author wenda.zhuang
 * @Date 2020/5/8 8:52 PM
 * @Description ... E-mail   sis.nonacosa@gmail.com
 */
public class LongAdderExample {

	//AtomicLong已经是非常好的解决方案了，涉及并发的地方都是使用CAS操作，在硬件层次上去做 compare and set操作。效率非常高
	//Doug lea 说这个更好， 它不是 CAS 的
	//可以全面替换 AtomicLong。
	private static LongAdder count = new LongAdder();

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
					count.increment();
					semaphore.release();
				} catch (Exception e) {
					System.out.println(e);
				}

				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		System.out.println(count);
	}


}
