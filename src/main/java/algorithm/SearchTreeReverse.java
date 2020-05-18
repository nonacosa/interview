package algorithm;


/**
 * @author wenda.zhuang
 * @Date 2020/5/18 4:51 PM
 * @Description
 * 第三题：
 * 输入一棵二元查找树，将该二元查找树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只调整引用（指针）的指向,只需要写出转换算法即可，请使用java实现其转换，并并给出时间复杂度和空间复杂度。
 *       9
 *     /   \
 *   5       13
 *  / \     /   \
 * 3  7     11  15
 * 转换成双向链表
 * 3=5=7=9=11=13=15。
 * @E-mail sis.nonacosa@gmail.com
 */
public class SearchTreeReverse {

	static class ListNode {
		int val;
		ListNode left;
		ListNode right;

		ListNode(int val) {
			this.val = val;
		}
	}

	private static ListNode first;
	private static ListNode pre;

	public static ListNode BSTToNodeList(ListNode node) {
		if (node == null) return node;
		first = pre = null;

		inorder(node);
		return pre;

	}

	public static void inorder(ListNode node) {
		if(node == null) return;

		inorder(node.left);

		if(first == null) {
			first = node;
		}

		if(pre == null) {
			pre = first;
		} else {
			pre.right = node;
			node.left = pre;
			pre = node;
		}

		inorder(node.right);


	}



	public static void main(String[] args) {
		ListNode node = new ListNode(9);
		node.left = new ListNode(5);
		node.left.left = new ListNode(3);
		node.left.right = new ListNode(7);

		node.right = new ListNode(13);
		node.right.left = new ListNode(11);
		node.right = new ListNode(15);

		ListNode xx = BSTToNodeList(node);
		System.out.println(xx);

	}
}
