package algorithm;

/**
 * @author wenda.zhuang
 * @Date 2020/5/27 3:17 PM
 * @Description 19题，未完成
 * @E-mail sis.nonacosa@gmail.com
 */
public class RemoveNthFromEnd {

	  public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

	public ListNode removeNthFromEnd(ListNode head, int n) {
		if(head == null) return null;
		int total = 1;
		ListNode copy = head;
		while(copy.next != null) {
			copy = copy.next;
			total ++;
		}
		int index = total - n + 1;
		int forIndex = 1;
		while(head.next != null) {
			forIndex ++;
			if(forIndex == index) {
				head.next = head.next.next;
			}
		}

		return head;
	}

	public static void main(String[] args) {
	  	ListNode node = new ListNode(1);
	  	node.next = new ListNode(2);
	  	node.next.next = new ListNode(3);
	  	node.next.next.next = new ListNode(4);
	  	node.next.next.next.next = new ListNode(5);
		new RemoveNthFromEnd().removeNthFromEnd(node,2);
	}
}
