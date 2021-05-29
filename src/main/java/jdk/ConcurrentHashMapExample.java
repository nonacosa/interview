package jdk;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenda.zhuang
 * @Date 2021/5/28 下午5:47
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
public class ConcurrentHashMapExample {

	public static void main(String[] args) {
		Map m = new ConcurrentHashMap();
		m.put("123","123");
		m.get("123");
	}
}
