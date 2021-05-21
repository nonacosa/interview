package concurrence;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wenda.zhuang
 * @Date 2021/5/18 下午4:11
 * @Description ReentrantLock
 * @E-mail sis.nonacosa@gmail.com
 */
public class ReentrantLockExample {

	/**
	 * new NonfairSync 默认是 new 了一个非公平锁，当然有公平锁
	 * AQS 核心代码：
	 *  	public final void acquire(int arg) {
	 *         //addWaiter 封装成  node ，放入等待列表，尝试获取，acquireQueued，获取多次失败睡眠
	 *         if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
	 *             selfInterrupt();
	 *     }
	 * tryAcquire AQS 本身无法获得锁，塔只负责管理线程排队、环形，具体要给子类实现用于判断是否获取锁成功的回调函数【模板方法模式，父类抽象，子类实现某一个】
	 * 下面是公平锁的实现：
	 * 		protected final boolean tryAcquire(int acquires) {
	 *             final Thread current = Thread.currentThread();
	 *             int c = getState();
	 *             //如果没有锁 state = 0，尝试上锁
	 *             if (c == 0) {
	 *                 //hasQueuedPredecessors 是公平锁的判断，判断当前线程是否是queue首
	 *                 if (!hasQueuedPredecessors() &&
	 *                     compareAndSetState(0, acquires)) {
	 *                     //表明当前线程持有锁
	 *                     setExclusiveOwnerThread(current);
	 *                     return true;
	 *                 }
	 *             }
	 *             //如果！=0 ， 看看当前线程是不是它，如果是，锁 + 1（这行代码代表了他是可重入锁），如果不是 return false
	 *             else if (current == getExclusiveOwnerThread()) {
	 *                 int nextc = c + acquires;
	 *                 if (nextc < 0)
	 *                     throw new Error("Maximum lock count exceeded");
	 *                 setState(nextc);
	 *                 return true;
	 *             }
	 *             return false;
	 *         }
	 *
	 */
	private final ReentrantLock reentrantLock = new ReentrantLock();

	public void m() {
		try {
			//对应的其实是公平锁 FairSync 和 非公平锁 NoFairSync 的 上锁和释放操作
			reentrantLock.lock();
			/**
			 * 如果可以多次上锁就叫【可重入锁】，但是对应加几次锁要解锁几次
			 * 0 无锁 > 0 几个锁
			 */
			reentrantLock.lock();
			//do something
		} finally {
			reentrantLock.unlock();
			reentrantLock.unlock();
		}
	}
}
