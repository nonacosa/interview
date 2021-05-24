package algorithm;

/**
 * @author wenda.zhuang
 * @Date 2021/4/22 下午5:38
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
public class ClimbStairs {

	public int climbStairs(int n) {
		int p = 0, q = 0, r = 1;
		for (int i = 1; i <= n; ++i) {
			p = q;
			q = r;
			r = p + q;
		}
		return r;
	}

}
