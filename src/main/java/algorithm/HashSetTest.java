package algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * @author wenda.zhuang
 * @Date 2020/4/27 5:06 PM
 * @Description ... E-mail   sis.nonacosa@gmail.com
 */
public class HashSetTest {
	public static void main(String[] args) {
		Set set = new HashSet();
		Object a = new Object();


		set.add(a);
		set.add(a);
		set.add(a);
		set.add(new Object());
		System.out.println(set);


		Set treeSet  = new TreeSet<>();
		treeSet.add("a");
		treeSet.add("b");
		treeSet.add("c");
		treeSet.add("f");
		treeSet.add("d");
		System.out.println(treeSet);


	}
}
