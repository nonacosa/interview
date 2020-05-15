package algorithm;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wenda.zhuang
 * @Date 2020/5/15 6:38 PM
 * @Description 睡眠排序算法
 * @E-mail sis.nonacosa@gmail.com
 */
public class SleepSort {

	public static CopyOnWriteArrayList sortedList = new CopyOnWriteArrayList();



	public static void sort(int [] nums) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		CountDownLatch countDownLatch = new CountDownLatch(nums.length);

		for(int i = 0; i< nums.length; i++) {
			int finalI = i;
			executorService.execute(() -> {
				try {
					Thread.sleep(nums[finalI]);
					sortedList.add(nums[finalI]);
					countDownLatch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		countDownLatch.await();
		System.out.println(sortedList);
	}

	public static void main(String[] args) throws InterruptedException {
		SleepSort.sort(new int[] {5,8,2,3,11,1,8,22,2});
	}
}
