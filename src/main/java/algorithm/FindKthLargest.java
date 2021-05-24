package algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author wenda.zhuang
 * @Date 2021/4/16 下午1:47
 * @Description https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * @E-mail sis.nonacosa@gmail.com
 */
public class FindKthLargest {

	//快排
	public int findKthLargest(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[nums.length - k ];
	}

	//最小堆实现
	public int findKthLargest2(int[] nums, int k) {
		//默认是小顶堆,容量 k
		PriorityQueue<Integer> heap = new PriorityQueue(k);
		for (int num : nums) {
			if(heap.size() < k) {
				heap.add(num);
			} else {
				if(heap.peek() < num) {
					heap.remove();
					heap.add(num);
				}
			}
		}
		return heap.element();
	}

	public static void main(String[] args) {
		System.out.println(new FindKthLargest().findKthLargest2(new int[]{3,2,1,5,6,4},2));;
		System.out.println(new FindKthLargest().findKthLargest2(new int[]{3,2,3,1,2,4,5,5,6},4));;
	}
}
