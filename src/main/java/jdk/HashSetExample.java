package jdk;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wenda.zhuang
 * @Date 2021/5/24 下午1:40
 * @Description HashSet源码
 * @E-mail sis.nonacosa@gmail.com
 */
public class HashSetExample {

	/**
	 * 		public boolean add(E e) {
	 *         return map.put(e, PRESENT)==null;
	 *      }
	 * 本质是 hashmap key 是它本身,所以不能重复
	 * @param args
	 */
	public static void main(String[] args) {
		Set set = new HashSet<>();
		set.add(new Object());

	}
}
