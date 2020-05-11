package limiting;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * @author wenda.zhuang
 * @Date 2020/5/8 23:34
 * @Description 漏斗桶限流的实现
 * @E-mail sis.nonacosa@gmail.com
 */
public class Bucket {
	//定义桶的大小
	private final ConcurrentLinkedQueue<Integer> container=new ConcurrentLinkedQueue<>();

	private final static int  BUCKET_LIMIT=1000;

	//消费者 不论多少个线程，每秒最大的处理能力是1秒中执行10次
	private final RateLimiter consumerRate=RateLimiter.create(10d);

	//往桶里面放数据时，确认没有超过桶的最大的容量
	/**
	 * Monitor类是作为ReentrantLock的一个替代，
	 * 代码中使用 Monitor比使用ReentrantLock更不易出错，可读性也更强，并且也没有显著的性能损失，
	 * 使用Monitor甚至有潜在的性能得到优化。
	 *
	 * Monitor有几个常用的方法
	 * enter()：进入到当前Monitor，无限期阻塞，等待锁。(这里没有Guard)
	 * enter(long time, TimeUnit unit)：进入到当前Monitor，最多阻塞给定的时间，返回是否进入Monitor。
	 * tryEnter()：如果可以的话立即进入Monitor，不阻塞，返回是否进入Monitor。
	 * enterWhen(Guard guard)：进入当前Monitor，等待Guard的isSatisfied()为true后，继续往下执行 ，但可能会被打断; 为false，会阻塞。
	 * enterIf(Guard guard)：如果Guard的isSatisfied()为true，进入当前Monitor。等待获得锁(这里会等待获取锁)，不需要等待Guard satisfied。
	 * tryEnterIf(Guard guard)：如果Guard的isSatisfied()为true并且可以的话立即进入Monitor，不等待获取锁(这里不等待获取锁)，也不等待Guard satisfied。
	 * 感觉  enterWhen enterIf的用的区别就是前者无返回值，后者返回Boolean值。
	 * newGuard（Boolean b）为{@代码}监视器创建一个新的{@链接守护}
	 */
	private Monitor offerMonitor=new Monitor();

	//从桶里消费数据时，桶里必须存在数据
	private Monitor consumerMonitor=new Monitor();


	/**
	 * 往桶里面写数据
	 * @param data
	 */
	public void submit(Integer data){
		if (offerMonitor.enterIf(offerMonitor.newGuard(()->container.size()<BUCKET_LIMIT))){
			try {
				container.offer(data);
				System.out.println(currentThread()+" submit.."+data+" container size is :["+container.size()+"]");
			} finally {
				offerMonitor.leave();
			}
		}else {
			//这里时候采用降级策略了。消费速度跟不上产生速度时，而且桶满了，抛出异常
			//或者存入MQ DB等后续处理
			throw new IllegalStateException(currentThread().getName()+"The bucket is ful..Pls latter can try...");
		}
	}


	/**
	 * 从桶里面消费数据
	 * @param consumer
	 */
	public void takeThenConsumer(Consumer<Integer> consumer){
		//一个标识线程是否等待的布尔条件
		if (consumerMonitor.enterIf(consumerMonitor.newGuard(()->!container.isEmpty()))){
			try {
				//不打印时 写 consumerRate.acquire();
				System.out.println(currentThread()+"  waiting"+consumerRate.acquire());
				Integer data = container.poll();
				//container.peek() 只是去取出来不会删掉
				consumer.accept(data);
			}finally {
				consumerMonitor.leave();
			}
		}else {
			//当木桶的消费完后，可以消费那些降级存入MQ或者DB里面的数据
			System.out.println("will consumer Data from MQ...");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	
	public static void main(String[] args) {
		final Bucket bucket = new Bucket();
		final AtomicInteger DATA_CREATOR = new AtomicInteger(0);

		//生产线程 10个线程 每秒提交 50个数据  1/0.2s*10=50个
		IntStream.range(0, 10).forEach(i -> {
			new Thread(() -> {
				for (; ; ) {
					int data = DATA_CREATOR.incrementAndGet();
					try {
						bucket.submit(data);
						TimeUnit.MILLISECONDS.sleep(200);
					} catch (Exception e) {
						//对submit时，如果桶满了可能会抛出异常
						if (e instanceof IllegalStateException) {
							System.out.println(e.getMessage());
							//当满了后，生产线程就休眠1分钟
							try {
								TimeUnit.SECONDS.sleep(60);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}).start();
		});


		//消费线程  采用RateLimiter每秒处理10个  综合的比率是5:1
		IntStream.range(0, 10).forEach(i -> {
			new Thread(
					() -> {
						for (; ; ) {
							bucket.takeThenConsumer(x -> {
								System.out.println(currentThread()+"C.." + x);
							});
						}
					}
			).start();
		});

	}


}
