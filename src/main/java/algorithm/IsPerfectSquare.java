package algorithm;

import java.sql.SQLOutput;

/**
 * @author wenda.zhuang
 * @Date 2020/5/21 3:51 PM
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
public class IsPerfectSquare {

	public static boolean isPerfectSquare(int num) {
		int i = 0;
		while(i * i < num) {
			i ++;
			if (i * i == num) { return true;}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isPerfectSquare(17));
	}
}
