package tips;

/**
 * @author wenda.zhuang
 * @Date 2020/5/18 8:02 PM
 * @Description 交换数字 【可以用于：翻转数组，不开辟额外空间】
 * @E-mail sis.nonacosa@gmail.com
 */
public class SwapNumber {

	public static void main(String[] args) {

		int a = 5;
		int b = 6;
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println("a :" + a + " b : " + b);
		//合并为一行，意思一样
		a = a ^ b ^ (b = a);
		System.out.println("a :" + a + " b : " + b);
	}
}
