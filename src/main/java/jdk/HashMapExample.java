package jdk;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenda.zhuang
 * @Date 2021/5/24 下午1:40
 * @Description HashSet源码
 * @E-mail sis.nonacosa@gmail.com
 */
public class HashMapExample {

	public static void f() {
		String[] a = new String[2];
		Object[] b = a;
		a[0] = "hi";
		b[1] = Integer.valueOf(42);
		System.out.println(a);
		System.out.println(b);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map map = new HashMap<>();
		map.put("","");
		f();
	}
}
