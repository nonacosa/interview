package algorithm;

/**
 * @author wenda.zhuang
 * @Date 2020/5/27 7:39 PM
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
public class StrStr {

	public int strStr(String haystack, String needle) {
		if(needle != "" && haystack == "") return -1;
		if(needle == "" && haystack == "") return 0;
		int len = needle.length();
		char[] haystacks = haystack.toCharArray();

		int pre = 0;
		int end = len;
		for(int i = 0; i< haystacks.length - len; i++) {
			if(haystack.substring(pre,end).equals(needle)) {
				return pre;
			}
			pre ++;
			end ++;
		}

		return -1;
	}

	public static void main(String[] args) {
		System.out.println(new StrStr().strStr("",""));
	}
}
