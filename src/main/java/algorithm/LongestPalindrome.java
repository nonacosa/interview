package algorithm;

/**
 * @author wenda.zhuang
 * @Date 2020/5/22 4:06 PM
 * @Description 最长回文子串：https://leetcode-cn.com/problems/longest-palindromic-substring/
 * @E-mail sis.nonacosa@gmail.com
 */
public class LongestPalindrome {

	public String longestPalindrome(String s) {

		boolean[][] dp = new boolean[s.length()][s.length()];

		for (int i = 0; i < s.length(); i++) {
			dp[i][i] = true;
		}

		int start = 0;
		int max_len = 1;

		for (int right = 0; right < s.length(); right++) {
			for (int left = 0; left < s.length(); left++) {
				if(left >= right) continue;

				if(left + 1 == right && s.charAt(left) == s.charAt(right)) {
					dp[left][right] = true;
				} else {
					dp[left][right] = dp[left + 1][right - 1] && s.charAt(left) == s.charAt(right);
				}

				if(dp[left][right] && right - left > max_len) {
					max_len  = right - left + 1;
					start = left;
				}

			}
		}


		return new String(s.toCharArray(),start,max_len);
	}


	public static void main(String[] args) {
		System.out.println(new LongestPalindrome().longestPalindrome("aaaaabbccdsadasdf"));
	}

}
