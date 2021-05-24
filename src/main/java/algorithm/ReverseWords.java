package algorithm;

/**
 * @author wenda.zhuang
 * @Date 2021/4/26 下午3:01
 * @Description https://leetcode-cn.com/problems/reverse-words-in-a-string/submissions/
 * @E-mail sis.nonacosa@gmail.com
 */
public class ReverseWords {

	public String reverseWords(String s) {
		String[] ss = s.trim().split("\\s+");
		StringBuffer sb = new StringBuffer("");
		for (int i = ss.length - 1; i >= 0; i--) {
			String world = i == 0 ? ss[i].trim() : ss[i].trim() + " ";
			sb.append(world);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public static void main(String[] args) {
		new ReverseWords().reverseWords("  Bob    Loves  Alice   ");
	}

}
