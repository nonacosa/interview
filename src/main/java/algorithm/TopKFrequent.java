package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author wenda.zhuang
 * @Date 2020/5/20 5:21 PM
 * @Description https://leetcode-cn.com/problems/top-k-frequent-words
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 *
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 * 示例 1：
 *
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 *     注意，按字母顺序 "i" 在 "love" 之前。
 *
 * 示例 2：
 *
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 *     出现次数依次为 4, 3, 2 和 1 次。
 *
 * 注意：
 *
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 输入的单词均由小写字母组成。
 *
 * 扩展练习：
 *
 * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 *
 * @E-mail sis.nonacosa@gmail.com
 */
public class TopKFrequent {

	public static List<String> solution1(String[] words, int k) {
		Map<String,Integer> count = new HashMap();
		for (String word : words) {
			count.put(word,count.getOrDefault(word,0) + 1);
		}

		List<String> candidates = new ArrayList<>(count.keySet());

		Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
				w1.compareTo(w2) : count.get(w2) - count.get(w1));

		return candidates.subList(0,k);

	}

	public static List<String> solution2(String[] words, int k) {

		Map<String, Integer> count = new HashMap();
		for (String word: words) {
			count.put(word, count.getOrDefault(word, 0) + 1);
		}
		PriorityQueue<String> heap = new PriorityQueue<>((w1, w2) -> count.get(w1)
				.equals(count.get(w2)) ? w2.compareTo(w1) : count.get(w1) - count.get(w2));

		for (String word: count.keySet()) {
			heap.offer(word);
			if (heap.size() > k) heap.poll();
		}

		List<String> ans = new ArrayList();
		while (!heap.isEmpty()) ans.add(heap.poll());
		Collections.reverse(ans);
		return ans;

	}

	public static void main(String[] args) {
		solution1(new String[]{"3","1","2","1","2"},2);
		solution2(new String[]{"3","1","2","1","2"},2);
	}
}
