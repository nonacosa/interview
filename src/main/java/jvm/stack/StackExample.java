package jvm.stack;

/**
 * @author wenda.zhuang
 * @Date 2020/12/12 下午12:11
 * @Description 演示虚拟机栈、栈帧
 * @E-mail sis.nonacosa@gmail.com
 */
public class StackExample {

	public static void A() {
		b();
	}
	public static int b() {
		int a = 0;
		a ++;
		return a;
	}

	public static void main(String[] args) {
//		A();
		new Thread(() -> A()).start();
		new Thread(() -> A()).start();
		new Thread(() -> A()).start();
		System.out.println(1);
	}
}
