package algorithm;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wenda.zhuang
 * @Date 2020/5/22 6:05 PM
 * @Description 10. 正则表达式匹配：https://leetcode-cn.com/problems/regular-expression-matching/
 * @E-mail sis.nonacosa@gmail.com
 */
public class IsMatch {

	public boolean isMatch(String s, String p) {

		boolean res = false;
		if(StringUtils.isEmpty(s) || StringUtils.isEmpty(p)) return false;

		int pIndex = 0;
		for (int i = 0; i < p.length(); i++) {
			if(s.charAt(pIndex) == p.charAt(i) || p.charAt(i) == '.') {
				res = true;
				pIndex ++;
				continue;
			}


			if(p.charAt(i) == '*') {
				for (int j = 0; j < s.length(); j++) {
					if(s.charAt(j) == p.charAt(i + 1)) {
						pIndex ++;
						res = true;
						break;
					} else {
						res = false;
					}
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(new IsMatch().isMatch("abcde","a*d.1"));
	}
}
