package algorithm;

import java.util.Arrays;

/**
 * @author wenda.zhuang
 * @Date 2021/4/25 下午3:00
 * @Description https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * @E-mail sis.nonacosa@gmail.com
 */
public class MaxProfit {


	//超出时间限制
	public int maxProfit(int[] prices) {
		int maxProfit = 0 , p = 0;
		if(prices.length <= 1) return 0;
		while (p < prices.length) {
			for (int i = p; i < prices.length; i++) {
				if(prices[i] - prices[p] > maxProfit) maxProfit = prices[i] - prices[p];
			}
			p++;
		}
		return maxProfit;
	}

	//先找历史最低点  todo DP
	public int maxProfit2(int[] prices){
		int minPrice = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minPrice) {
				minPrice = prices[i];
			} else if (prices[i] - minPrice > maxProfit) {
				maxProfit = prices[i] - minPrice;
			}
		}
		return maxProfit;
	}

	public static void main(String[] args) {
		System.out.println(new MaxProfit().maxProfit(new int[]{7,1,5,3,6,4}));
		System.out.println(new MaxProfit().maxProfit2(new int[]{7,1,5,3,6,4}));
	}
}
