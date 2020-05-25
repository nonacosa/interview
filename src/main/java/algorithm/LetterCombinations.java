package algorithm;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenda.zhuang
 * @Date 2020/5/23 1:40 PM
 * @Description 17. 电话号码的字母组合 : https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * @E-mail sis.nonacosa@gmail.com
 */
public class LetterCombinations {

	Map<Character, String> phone = new HashMap<Character, String>() {{
		put('2', "abc");
		put('3', "def");
		put('4', "ghi");
		put('5', "jkl");
		put('6', "mno");
		put('7', "pqrs");
		put('8', "tuv");
		put('9', "wxyz");
	}};


	private List<String> res = new ArrayList<>();


	public List<String> letterCombinations(String digits) {
		if(digits == null) return res;
		backtrack(digits,0,new StringBuilder(""));
		return res;
	}

	public void backtrack(String origin,int level, StringBuilder sb) {
		if(level == origin.length()) {
			res.add(sb.toString());
			return;
		}

		char[] chars = phone.get(origin.charAt(level)).toCharArray();
		for (int i = 0; i < chars.length; i++) {
			sb.append(chars[i]);
			backtrack(origin,level + 1,sb );
			sb.deleteCharAt(sb.length() -1);
		}
	}


	public static void main(String[] args) {
		System.out.println(new LetterCombinations().letterCombinations("233"));
	}
}
