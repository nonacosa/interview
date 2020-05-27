package algorithm;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author wenda.zhuang
 * @Date 2020/5/25 8:56 PM
 * @Description 加班时间计算
 * 给定加班时间数组左侧代表上班时间，右侧代表下班时间
 * [9,11] [12,15] [16,18]
 * 给定实际上班时间：[9,18]
 *
 * 应返回：[11,12]  [15,16]
 * @E-mail sis.nonacosa@gmail.com
 *
 * https://blog.csdn.net/huanyinghanlang/article/details/78774652
 */
public class Overtime {


	public static void main(String[] args) {
		System.out.println(Integer.parseInt("01"));
		List<String[]> time = Lists.newArrayList();
		time.add(new String[]{"09:00","11:00"});
		time.add(new String[]{"12:00","15:00"});
		time.add(new String[]{"16:00","18:00"});
		new Overtime().overTime(time,new String[]{"09:38","18:00"});
	}

	public List<String[]> overTime(List<String[]> time,String[] workTime){
		List<String[]> res = Lists.newArrayList();
		if(time.size() == 0 || time == null || workTime == null) return null;

		//公司规定时间
		Set<Integer> timeSet = Sets.newHashSet(timeStrToIntArr(time));
		//实际申请加班时间
		Set<Integer> workSet = Sets.newHashSet(timeStrToArr(workTime));

		Sets.SetView<Integer> diff = Sets.difference(workSet,timeSet );
		for (Integer temp : diff) {
			res.add(new String[]{temp + ":00", temp < 23 ? temp + 1 + ":00" :  "00:00"});
		}
		return res;
	}

	public Integer[] timeStrToIntArr(List<String[]> times) {
		List<Integer> res = Lists.newArrayList();
		for (int j = 0; j < times.size(); j++) {
			String[] time = times.get(j);
			Integer[] temp = new Integer[2];
			Preconditions.checkState(time.length == 2,"传入的上班时间数组长度有误！");

			for (int i = 0; i < time.length; i++) {
				Preconditions.checkState(time[i].contains(":"),"传入的上班时间数组格式有误！");
				temp[i] = Integer.parseInt(time[i].split(":")[0]);
			}
			Integer[] arr = IntStream.rangeClosed(temp[0], temp[1] - 1).boxed().toArray(Integer[]::new);
			for (int i = 0; i < arr.length; i++) {
				res.add(arr[i]);
			}
		}

		return res.toArray(new Integer[res.size()]);
	}


	public Integer[] timeStrToArr(String[] workTime) {
		Integer[] temp = new Integer[2];
		for (int i = 0; i < workTime.length; i++) {
			Preconditions.checkState(workTime[i].contains(":"),"传入的上班时间数组格式有误！");
			temp[i ] = Integer.parseInt(workTime[i].split(":")[0]);
		}
		Integer[] ret = IntStream.rangeClosed(temp[0], temp[1] - 1).boxed().toArray(Integer[]::new);

		return ret;
	}



}
