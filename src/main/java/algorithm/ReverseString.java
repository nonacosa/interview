package algorithm;

import sun.applet.Main;

/**
 * @author wenda.zhuang
 * @Date 2020/5/14 7:53 PM
 * @Description  反转字符串 : https://leetcode-cn.com/explore/featured/card/recursion-i/256/principle-of-recursion/1198/
 * @E-mail sis.nonacosa@gmail.com
 */
public class ReverseString {

	public static void main(String[] args) {
		reverse("Hello".toCharArray(),0);
	}

	//最前和最后依次调换位置，记录指针即可,递归法
	public static void reverse(char[] str,int index) {
		if(index == str.length -1) {
			System.out.println(str);
			return;
		}
		char head = str[index];
		char last = str[str.length - index - 1];
		str[index] = last;
		str[str.length - index - 1] = head ;
		reverse(str,index + 1);


	}

	//todo while 循环也可以
}
