package algorithm;

import sun.jvm.hotspot.memory.Dictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenda.zhuang
 * @Date 2020/5/20 6:40 PM
 * @Description ... A-Z = 1-26 AA = 27 AAA = .... 以此类推
 * @E-mail sis.nonacosa@gmail.com
 */
public class LettersToNumber {

	private static Map<Character,Integer> dictionary = new HashMap();


	public static void main(String[] args) {
		System.out.println(trans("ba"));
	}

	public static int trans(String str) {
		int count = 0;
		for (int i = str.length() - 1, j = 1; i >= 0; i--, j *= 26){
			char c = str.charAt(i);
			if (c < 'a' || c > 'z') return 0;
			count += ((int)c - 'a' + 1)  * j;
		}
		return count;
	}


}
