package jvm.stack;

/**
 * @author wenda.zhuang
 * @Date 2021/1/21 下午4:25
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
public class JavaVMStackOOM {

	private void dontStop() {
		while (true) {
		}
	}

	public void stackLeakByThread() {
		while (true) {
			Thread thread = new Thread(() -> dontStop());
			thread.start();
		}
	}

	public static void main(String[] args) {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}

}
