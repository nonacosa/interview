package algorithm;



/**
 * @author wenda.zhuang
 * @Date 2020/5/14 8:57 PM
 * @Description 翻转列表
 * @E-mail sis.nonacosa@gmail.com
 */
public class ReverseList {

	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

	public static ListNode reverseList(ListNode head) {
		 if(head == null) {
		 	return null;
		 }

		 ListNode pre = null;
		/**
		 * 一定要缓存下一个节点，否则下一次找 next 节点又回到 1，缓存 head.next 方便找到真正的 head.next.next
		 * 1 -> 2 -> 3
		 * 1 <- 2 -> 3
		 *
		 */
		 ListNode nextTemp = null;
		 while (head != null) {
			 //缓存
			 nextTemp = head.next;
			 //翻转下个节点指针，并且修改 pre
			 head.next = pre;
			 pre = head;
			 //while 下一个
			 head = nextTemp;
		 }
		 //返回翻转的结果
		 return pre;
	}

	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(4);
		ListNode a = reverseList(node);
		System.out.println(a);
	}
	
}
