package jvm.stack;

/**
 * @author wenda.zhuang
 * @Date 2020/12/31 下午2:10
 * @Description 线程安全演示
 * @E-mail sis.nonacosa@gmail.com
 */
public class StackSafeExample {


	//局部变量
	public static void A() {
		int count = 0;
		for (int i = 0; i < i; i++) {
			count ++;
		}
		System.out.println(count);
	}

	//非局部变量
	public static void B(int count) {
		for (int i = 0; i < i; i++) {
			count ++;
		}
		System.out.println(count);
	}

	//非局部变量
	public static int C() {
		int count = 0;
		for (int i = 0; i < i; i++) {
			count ++;
		}
		//虽然 count 是局部变量，但是该方法吧 局部变量返回了，其他实例可能会拿到 count ，还是不安全
		return count;
	}
}
