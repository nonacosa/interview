package lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wenda.zhuang
 * @Date 2020/5/11 6:44 PM
 * @Description 基于 linkedHashMap 的  LRU 最近最少使用策略
 * @E-mail sis.nonacosa@gmail.com
 */
public class LinkedHashMapExample<K,V> extends LinkedHashMap<K,V> {

	private int size;

	private LinkedHashMapExample(int size) {
		super(size,0.75f,true);
		this.size = size;
	}

	public static void main(String[] args) {
		LinkedHashMapExample<Integer,Integer> example = LinkedHashMapExample.newInstance(3);

		example.put(1, 1);
		example.put(2, 2);
		example.put(3, 3);
		example.put(4, 4);

		System.out.println(example);

		example.get(2);
		System.out.println(example);

		example.put(5,5);
		System.out.println(example);

	}

	/**
	 * 重写此处的 remove 逻辑，否则进行 hash 扩容
	 * @param eldest
	 * @return
	 */
	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > size;
	}

	private static LinkedHashMapExample newInstance(int size) {
		return new LinkedHashMapExample(size);
	}






}
