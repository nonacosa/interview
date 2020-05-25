import java.util.HashMap;
import java.util.Map;

/**
 * @author wenda.zhuang
 * @Date 2020/5/13 5:59 PM
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
public class Test {

	public static void main(String[] args) {
		int a = 5;
		int b = 6;
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println("a :" + a + " b : " + b);

		System.out.println(3 >>> 16);
	}
}
