package algorithm;


import javax.sound.midi.Soundbank;

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
			return head;
		}

		ListNode first = head;
		head = reverseList(head.next);
		System.out.println("sss");
		return head;
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
