package algorithm;


/**
 * @author wenda.zhuang
 * @Date 2020/5/22 5:07 PM
 * @Description 斐波那契:1、1、2、3、5、8、13、21、34
 * @E-mail sis.nonacosa@gmail.com
 */
public class Fibonacci {

	public int fibonacci(int index) {
		if(index == 1 || index == 2) return 1;
		if(index < 1) return 0;

		int first = fibonacci(index -2 );
		int seccond = fibonacci(index -1);

		return first + seccond;
	}



	public static void main(String[] args) {
		System.out.println(new Fibonacci().fibonacci(9));
	}
}
