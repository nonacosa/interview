package algorithm;

import java.util.Arrays;

/**
 * @author wenda.zhuang
 * @Date 2020/5/22 00:12
 * @Description 子串问题，滑动窗口
 * @E-mail sis.nonacosa@gmail.com
 */
public class CheckInclusion {

	public static boolean checkInclusion(String s1, String s2) {
		s1 = sort(s1);
		s2 = sort(s2);
		boolean result;

		if(s1 == "" || s1.equals(s2)) return true;
		if(s1.length() == s2.length()) {
			return new StringBuilder(s2).reverse().toString().equals(s1);
		}
		result = verify(s1,s2);

		if(!result) {
			result = verify(s1,new StringBuilder(s2).reverse().toString());
		}

		return result;
	}

	public static boolean verify(String s1, String s2) {
		int len = s1.length();
		for (int i = 0; i < s2.length() - len; i++) {
			String winS2 = s2.substring(i,i + len);
			if(winS2.equals(s1)) return true;
		}
		return false;
	}

	public static String sort(String s) {
		char[] sArr = s.toCharArray();
		Arrays.sort(sArr);
		return new String(sArr);
	}
	public static void main(String[] args) {
		System.out.println(checkInclusion("ab","ba"));;
	}
}
