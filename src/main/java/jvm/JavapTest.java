package jvm;

/**
 * @author wenda.zhuang
 * @Date 2020/12/31 00:10
 * @Description javap 反汇编器使用 example : javap -verbose JavapTest.class
 * @E-mail sis.nonacosa@gmail.com
 */
public class JavapTest {

	private static int count = 0;

	public static void main(String[] args) {
		count ++;
		System.out.println(count);
	}
}
