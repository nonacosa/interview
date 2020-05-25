package algorithm;

/**
 * @author wenda.zhuang
 * @Date 2020/5/25 11:49 AM
 * @Description 198.打家劫舍
 * @E-mail sis.nonacosa@gmail.com
 */
public class Rob{

	public int rob(int[] nums) {

		int len = nums.length;
		if(len == 0)
			return 0;
		int[] dp = new int[len + 1];
		dp[0] = 0;
		dp[1] = nums[0];
		for(int i = 2; i <= len; i++) {
			dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
		}
		return dp[len];



	}

	public void dp(int[] nums, int level,int total) {

		if(level < nums.length) return;


	}

	public static void main(String[] args) {
		System.out.println(new Rob().rob(new int[]{1,3,5,7,9}));
	}

}
