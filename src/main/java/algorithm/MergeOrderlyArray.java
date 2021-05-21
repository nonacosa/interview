package algorithm;

import sun.java2d.pipe.SpanIterator;

import java.util.Arrays;

/**
 * @author wenda.zhuang
 * @Date 2021/5/13 下午8:02
 * @Description 合并两个有序数组
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xmi2l7/
 * @E-mail sis.nonacosa@gmail.com
 */
public class MergeOrderlyArray {

	public void merge(int[] nums1, int m, int[] nums2, int n) {

		for (int i = 0; i < nums2.length; i++) {
			nums1[nums1.length - i - 1] = nums2[i];
		}

		Arrays.sort(nums1);
		System.out.println(nums1);
	}


	public static void main(String[] args) {
		new MergeOrderlyArray().merge(new int[]{1,2,3,0,0,0},3,new int[]{5,6,7},3);
	}
}
