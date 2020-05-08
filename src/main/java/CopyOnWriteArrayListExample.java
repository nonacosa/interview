import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author wenda.zhuang
 * @Date 2020/5/9 00:12
 * @Description CopyOnWriteArrayList 并发，概念：复制容器，add，指针指回去，但是会复制数组，数据量大不建议用，容易 young GC full GC
 * @E-mail sis.nonacosa@gmail.com
 */
public class CopyOnWriteArrayListExample {

	// 请求总数
	public static int clientTotal = 5000;

	// 同时并发执行的线程数
	public static int threadTotal = 200;

	private static List<Integer> list = new CopyOnWriteArrayList<>();

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			final int count = i;
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					update(count);
					semaphore.release();
				} catch (Exception e) {
					System.out.println(e);
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		System.out.println(list.size());
	}

	private static void update(int i) {
		list.add(i);
	}
}
