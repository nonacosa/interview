package algorithm;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
public class Overtime2 {

	private static Map<Integer,String> cache = new HashMap();

	public static void main(String[] args) {
		List<String[]> time = Lists.newArrayList();
		time.add(new String[]{"09:00","11:00"});
		time.add(new String[]{"12:00","15:00"});
		time.add(new String[]{"16:00","18:00"});
		new Overtime2().overTime(time,new String[]{"09:38","18:25"});
		new Overtime2().overTime(time,new String[]{"09:38","15:25"});
		new Overtime2().overTime(time,new String[]{"15:20","18:25"});
	}

	public List<String[]> overTime(List<String[]> time,String[] workTime){
		List<String[]> res = Lists.newArrayList();
		if(time.size() == 0 || time == null || workTime == null) return null;


		List workTimeMinuteArr  = getTimeArr(workTime);
		List realWorkTImeMinuteArr = getTimeArr(time,workTime);

		//公司规定时间
		Set<String> timeSet = Sets.newHashSet(realWorkTImeMinuteArr);
		//实际申请加班时间
		Set<String> workSet = Sets.newHashSet(workTimeMinuteArr);

		Sets.SetView<String> diff = Sets.difference(workSet,timeSet );

		return getResultTime(diff);
	}

	private List<String[]> getResultTime(Sets.SetView<String> diff) {
		List<String> sortDiff = Lists.newArrayList();
		Arrays.sort(new Sets.SetView[]{diff});

		for (String temp : diff) {
			sortDiff.add(temp);
		}
		String[] temp = sortDiff.toArray(new String[diff.size()]);
		Arrays.sort(temp);



		List<String[]> resss = Lists.newArrayList();
		int gIndex = -1;
		String pre = null;
		boolean shuldStart = false;
		for (int i = 0; i < temp.length; i++) {
			if(pre == null) {
				resss.add(new String[2]);
				resss.get(0)[0] = temp[i];
				gIndex ++;
				pre = temp[i];
			} else {

				if(shuldStart) {
					resss.add(new String[2]);
					resss.get(gIndex +1)[0] = pre;
					gIndex ++;
//					pre = temp[i];
					shuldStart = false;
				}
				Integer thisHour = Integer.parseInt(temp[i].split(":")[0]);
				Integer thisMinutx = Integer.parseInt(temp[i].split(":")[1]);

				Integer preHour = Integer.parseInt(pre.split(":")[0]);
				Integer preMinutx = Integer.parseInt(pre.split(":")[1]);

				if(i == temp.length -1) {
					resss.get(gIndex)[1] = temp[i];
					pre = temp[i];
					shuldStart = true;
				}
				//是不是连续的
				if(thisHour == preHour ) {
					if((thisMinutx != 0 && thisMinutx - 1 == preMinutx) ) {
						pre = temp[i];
						continue;
					}
				}

				if(thisHour == preHour + 1){
					if(thisMinutx == 1 && preMinutx == 59) {
						pre = temp[i];
						continue;
					}
				}

				resss.get(gIndex)[1] = pre;
				pre = temp[i];
				shuldStart = true;
			}
		}


		resss.forEach( p -> {
			System.out.println(Arrays.toString(p));
		});

		System.out.println("======");
		return resss;
	}

	private List getTimeArr(List<String[]> time,String[] workTime) {
		List realWorkTImeMinuteArr =  Lists.newArrayList();
		for (int i = 0; i < time.size(); i++) {
			Integer beginHourx = Integer.parseInt(time.get(i)[0].split(":")[0]);
			Integer beginMinutx = Integer.parseInt(time.get(i)[0].split(":")[1]);
			Integer endHourx = Integer.parseInt(time.get(i)[1].split(":")[0]);
			Integer endMinutex = Integer.parseInt(time.get(i)[1].split(":")[1]);
			for(int h = beginHourx; h < endHourx + 1; h++) {
				for (int m = 0; m < 60; m++) {
					if(h == beginHourx && m < beginMinutx) continue;
					if(h == endHourx && m > endMinutex) break;
					realWorkTImeMinuteArr.add(StringUtils.leftPad(String.valueOf(h), 2, "0").concat(":").concat(StringUtils.leftPad(String.valueOf(m), 2, "0")));
				}
			}

		}

		return realWorkTImeMinuteArr;
	}

	private List getTimeArr(String[] workTime) {
		List workTimeMinuteArr = Lists.newArrayList();
		Integer beginHour = Integer.parseInt(workTime[0].split(":")[0]);
		Integer beginMinut = Integer.parseInt(workTime[0].split(":")[1]);
		Integer endHour = Integer.parseInt(workTime[1].split(":")[0]);
		Integer endMinute = Integer.parseInt(workTime[1].split(":")[1]);
		for(int h = beginHour; h < endHour + 1; h++) {
			for (int m = 0; m < 60; m++) {
				if(h == beginHour && m < beginMinut) continue;
				if(h == endHour && m > endMinute) break;
				workTimeMinuteArr.add(StringUtils.leftPad(String.valueOf(h), 2, "0").concat(":").concat(StringUtils.leftPad(String.valueOf(m), 2, "0")));
			}
		}
		return workTimeMinuteArr;
	}


}
