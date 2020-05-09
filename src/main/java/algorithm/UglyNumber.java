package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wenda.zhuang
 * @Date 2020/4/23 8:04 PM
 * @Description ... E-mail   sis.nonacosa@gmail.com
 */
public class UglyNumber {

	//ugly 2  3  5
	public static int nthUglyNumber(int n) {
	return 0;
	}

	public static void main(String[] args) {
		//   011     101
		int a = 3, b = 5;

		a ^= b;    // a = a^b   011 ^ 101 = 110 >>>>>> 6
		b ^= a;    // b = b^a   101 ^ 110 = 011 >>>>>> 3
		a ^= b;	   // a = a^b   110 ^ 011 = 101 >>>>>> 5


		System.out.println(a);
		System.out.println(b);

		List aaa = new ArrayList<>();
		aaa.add(3);
		aaa.add(1);
		aaa.add(2);

		int[] bbb = {3,5,1};
		Arrays.sort(bbb);
		System.out.println(bbb);
	}
}
