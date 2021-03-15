package algorithm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wenda.zhuang
 * @Date 2020/5/23 1:14 PM
 * @Description 三数之和
 * @E-mail sis.nonacosa@gmail.com
 */
public class ThreeSum {

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> ans = new ArrayList();
		if(nums == null || nums.length < 3) return ans;
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 2; i++) {
			if (nums[i] > 0) {
				break;
			}
			if (i > 0 && nums[i - 1] == nums[i]) continue;

			int L = i + 1;
			int R = nums.length - 1;

			while (L < R) {
				int sum = nums[L] + nums[i] + nums[R];
				//L指针右行，R指针左行，相同的跳过
				if (sum == 0) {
					ans.add(Arrays.asList(nums[L], nums[i], nums[R]));
					while (R > L && nums[L] == nums[L + 1]) L++;
					while (R > L && nums[R] == nums[R - 1]) R--;
					L++;
					R--;
				} else if (sum < 0) L++;
				else if (sum > 0) R--;
			}
		}
		return ans;
	}


	public static void main(String[] args) {
		System.out.println(new ThreeSum().threeSum(new int[] {-1,0,1,2,-1,-4}));
	}
}
