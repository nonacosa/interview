package limiting;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wenda.zhuang
 * @Date 2020/5/11 4:56 PM
 * @Description 限流：令牌桶算法  guava 天然实现
 * @E-mail sis.nonacosa@gmail.com
 */
public class RateLimiterExample {

	public static void main(String[] args) {
		// permitsPerSecond 每秒发放的许可数量，当然也可以选择使用 JDK 的 Semaphore
		RateLimiter rateLimiter = RateLimiter.create(3.0);

		//重复使用固定数量的线程，多余的排队
		ExecutorService executorService = Executors.newFixedThreadPool(100);

		for (int i = 0; i < 10; i++) {
			executorService.execute(() -> {
				//获取令牌桶中一个令牌，最多等待10秒
				if(rateLimiter.tryAcquire(1,10, TimeUnit.SECONDS)) {
					System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				}
			});
		}

		executorService.shutdown();
	}
}
