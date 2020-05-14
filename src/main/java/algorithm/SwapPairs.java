package algorithm;

/**
 * @author wenda.zhuang
 * @Date 2020/5/14 8:33 PM
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
public class SwapPairs {
	public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
	public static ListNode swapPairs(ListNode head) {
		if(head == null || head.next == null){
			return head;
		}
		ListNode next = head.next;
		head.next = swapPairs(next.next);
		next.next = head;
		return next;


	}


	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(4);
		ListNode a = swapPairs(node);
		System.out.println(a);
	}
}
