package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wenda.zhuang
 * @Date 2020/5/14 3:32 PM
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
public class NLoops {

	private static List<List<Integer>> resultList = new LinkedList<>();



	public static void loopN(int[] loopArr) {
		if(loopArr == null || loopArr.length == 0) {
			return;
		}
		dfs(loopArr,0,new ArrayList<>(loopArr.length));

		System.out.println(resultList);

	}

 	public static void dfs(int[] nums, int level,List<Integer> temp ) {

		if( level == nums.length) {
			resultList.add(new ArrayList<>(temp));
			return;
		}

		System.out.println(level);

		for (int i = 0; i < nums[level]; i++) {
			temp.add(i);
			dfs(nums,level + 1,temp);
			temp.remove(level);
		}
	}

	public static void main(String[] args) {
		int[] la = {2,2,2};
		loopN(la);

	}
}
