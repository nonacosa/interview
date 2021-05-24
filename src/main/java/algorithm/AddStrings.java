package algorithm;

/**
 * @author wenda.zhuang
 * @Date 2021/4/19 下午12:27
 * @Description 字符串相加   https://leetcode-cn.com/problems/add-strings/
 * @E-mail sis.nonacosa@gmail.com
 */
public class AddStrings {

	/**
	 *  1922
	 *   288
	 *  ----
	 *  2210
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String addStrings(String num1, String num2) {
		int x = num1.length() - 1 , y = num2.length() - 1 , add = 0;
		StringBuffer sb = new StringBuffer();
		while (x >= 0 || y >= 0 || add != 0) {
			int xx = x >= 0 ? num1.charAt(x) - '0' : 0;
			int yy = y >= 0 ? num2.charAt(y) - '0' : 0;

			sb.append((xx + yy + add) % 10);
			add = (xx + yy + add) / 10;
			x--;
			y--;
		}
		return sb.reverse().toString();

	}

	public static void main(String[] args) {
		int a = '1' - '0';
		System.out.println('1' - '0');
		System.out.println(new AddStrings().addStrings("1922","288"));
	}

}
