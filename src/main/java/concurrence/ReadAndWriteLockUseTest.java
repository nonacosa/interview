package concurrence;

/**
 * @author wenda.zhuang
 * @Date 2020/5/25 2:17 PM
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadAndWriteLockUseTest {

	public static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {
		ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

		//定义10个读线程 5个写线程
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				ReadAndWriteLockThread readAndWriteLockThread = new ReadAndWriteLockThread(reentrantReadWriteLock);
				while (true) {
					readAndWriteLockThread.readData();

					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				ReadAndWriteLockThread readAndWriteLockThread = new ReadAndWriteLockThread(reentrantReadWriteLock);
				while (true) {
					readAndWriteLockThread.writeData();
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}


		//监控
		while (true) {
			//获取正在等待锁的线程数量
			int queueLength = reentrantReadWriteLock.getQueueLength();

			//获取锁中的读锁数量,要么为0，要么为1
			int readLockCount = reentrantReadWriteLock.getReadLockCount();

			//判断是否存在等待锁的线程
			boolean hasQueuedThreads = reentrantReadWriteLock.hasQueuedThreads();

			//判断是否是公平锁
			boolean fair = reentrantReadWriteLock.isFair();

			//判断当前是否有写入锁,即判断当前占有锁的是否是写入锁
			boolean writeLocked = reentrantReadWriteLock.isWriteLocked();

			System.out.println(MessageFormat.format("监控：当前正在等待锁的线程数量：{0}，获取读锁的线程数量：{1}，是否存在等待锁的线程：{2}，" +
					"当前锁是否是公平锁：{3}，当前锁中是否是写锁被占用：{4}", queueLength, readLockCount, hasQueuedThreads, fair, writeLocked));

			TimeUnit.SECONDS.sleep(1);
		}

	}

	static class ReadAndWriteLockThread {

		private ReentrantReadWriteLock reentrantReadWriteLock;

		ReadAndWriteLockThread(ReentrantReadWriteLock reentrantReadWriteLock) {
			this.reentrantReadWriteLock = reentrantReadWriteLock;
		}

		/**
		 * 读数据
		 */
		private void readData() {
			try {
				reentrantReadWriteLock.readLock().lock();

				//返回当前对锁的可重入读取的数量
				int readHoldCount = reentrantReadWriteLock.getReadHoldCount();

				System.out.println(MessageFormat.format("线程：{0} 正在读取数据:{1},当前锁lock的可重入数量：{2}",
						Thread.currentThread().getName(), ATOMIC_INTEGER.get(), readHoldCount));
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				reentrantReadWriteLock.readLock().unlock();
			}
		}

		/**
		 * 写数据
		 */
		private void writeData() {
			try {
				reentrantReadWriteLock.writeLock().lock();
				//写入数据
				int incrementAndGet = ATOMIC_INTEGER.incrementAndGet();

				//获取当前写锁的重入数量
				int writeHoldCount = reentrantReadWriteLock.getWriteHoldCount();

				//判断当前线程是否存在写锁
				boolean writeLockedByCurrentThread = reentrantReadWriteLock.isWriteLockedByCurrentThread();
				System.out.println(MessageFormat.format("线程：{0}正在写入数据：{1},当前lock的可重入数：{2},当前线程是否存在写锁：{3}",
						Thread.currentThread().getName(), incrementAndGet, writeHoldCount, writeLockedByCurrentThread));
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				reentrantReadWriteLock.writeLock().unlock();
			}
		}


	}

}
