package concurrence;

import com.google.common.collect.Lists;

import com.sun.activation.registries.LogSupport;

import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @author wenda.zhuang
 * @Date 2021/5/21 下午6:35
 * @Description 使用两个线程 12345678 abcdefg 有规律的交替打印 1a2b3c...
 * @E-mail sis.nonacosa@gmail.com
 */
public class ThreadPark {

	static Thread t1, t2;

	public static void main(String[] args) {
		List<String> l1 = Lists.newArrayList("1","2","3","4","5","6","7","8");
		List<String> l2 = Lists.newArrayList("a","b","c","d","e","f","g","h");

		t1 = new Thread(() -> {
			l1.forEach(e -> {
				System.out.println(e);
				LockSupport.unpark(t2);
				LockSupport.park();

			});
		},"t1");

		t2 = new Thread(() -> {
			l2.forEach(e -> {
				LockSupport.park();
				System.out.println(e);
				LockSupport.unpark(t1);
			});
		},"t2");

		t1.start();
		t2.start();

		// 这里有个问题，两个 thread 同时异步跑，如果 1 unpark 2 的时候 2还没有 park 怎么办？
	}
}
