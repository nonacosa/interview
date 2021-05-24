package algorithm;



/**
 * @author wenda.zhuang
 * @Date 2021/4/19 下午5:22
 * @Description https://leetcode-cn.com/problems/compare-version-numbers/solution/jing-jian-yi-dong-ban-by-legendarygz-lve1/
 * @E-mail sis.nonacosa@gmail.com
 */
public class CompareVersion {

	public int compareVersion(String version1, String version2) {
		int vp1 = 0;
		int vp2 = 0;
		String[] v1s = version1.split("\\.");
		String[] v2s = version2.split("\\.");

		while (vp1 < v1s.length  && vp2 < v2s.length ) {
			int compare = Integer.compare(Integer.parseInt(v1s[vp1]),Integer.parseInt(v2s[vp2]));
			if(compare < 0) return -1;
			if(compare > 0) return 1;
			vp1 ++;
			vp2++;
		}
		if(v1s.length > v2s.length) {
			for (int i = vp1; i < v1s.length; i++) {
				if(Integer.parseInt(v1s[i]) > 0) return 1;
			}
		}
		if(v1s.length < v2s.length) {
			for (int i = vp2; i < v2s.length; i++) {
				if(Integer.parseInt(v2s[i]) > 0) return -1;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
//		System.out.println(new CompareVersion().compareVersion("1.1.1.1","1.1.1.2"));
//		System.out.println(new CompareVersion().compareVersion("1.2.1.1","1.1.1.2"));
		System.out.println(new CompareVersion().compareVersion("7.5.2.4","7.5.3"));
		System.out.println(new CompareVersion().compareVersion("1.0.1","1"));
	}


}
