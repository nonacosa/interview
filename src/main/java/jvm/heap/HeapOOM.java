package jvm.heap;

/**
 * @author wenda.zhuang
 * @Date 2021/1/5 01:26
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
public class HeapOOM {
	public static void main(String[] args) {
		int i = 0;
		String a = "hello";
		try {
			while (true) {
				a = a + a;
				i++;
				System.out.println(i);
			}
		} catch (Throwable e){
			System.out.println(e); // java.lang.OutOfMemoryError: Java heap space
			System.out.println(i); // 27
		}
	}
}
