package algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wenda.zhuang
 * @Date 2021/4/26 下午3:25
 * @Description https://leetcode-cn.com/problems/linked-list-cycle/submissions/
 * @E-mail sis.nonacosa@gmail.com
 */
public class HasCycle {

	static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

    private Set<ListNode> set = new HashSet<>();
	private boolean isCycle = false;

	//迭代 + 字典  todo 快慢指针
	public boolean hasCycle(ListNode head) {
		if(head == null) return false;
		if(head.next != null ) {
			if(set.add(head)){
				hasCycle(head.next );
			}else {
				return isCycle = true;
			}
		}
		return isCycle;
	}



	public static void main(String[] args) {
		ListNode listNode1 = new ListNode(3);
		ListNode listNode2 = new ListNode(2);
		ListNode listNode3 = new ListNode(0);
		ListNode listNode4 = new ListNode(-4);
		listNode1.next = listNode2;
		listNode2.next = listNode3;
		listNode3.next = listNode4;
		listNode4.next = listNode2;
		System.out.println(new HasCycle().hasCycle(listNode1));;
	}


}
