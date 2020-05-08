import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wenda.zhuang
 * @Date 2020/4/26 5:24 PM
 * @Description ... E-mail   sis.nonacosa@gmail.com
 */
public class MergeKSortList {

	 public static class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) { val = x; }
	 }

	 public static ListNode mergeKLists(ListNode[] lists) {
		 List<Integer> temp = new ArrayList<>();
	 	for (ListNode list : lists) {
	 		temp.add(list.val);


			while ( true ) {
				ListNode nextVal = list.next;

				if(nextVal == null) {
				}

				temp.add(nextVal.val);
				list = nextVal;

			}
		 }

		 Object[] a = temp.toArray();
		 Arrays.sort(a);
		 ListNode node = null;
		 for (int i = 0; i < a.length; i++) {
				ListNode aaa = new ListNode(((int) a[i]));
			 if(i == 0) {
			 	node = aaa;

			 } else {
			 	node.next = aaa;
			 	node = node.next;
			 }


		 }
	 	return node;
	 }

	public static void main(String[] args) {

	 	ListNode node = new ListNode(1);
	 	node.next = new ListNode(4);
	 	node.next.next = new ListNode(8);


		ListNode node2 = new ListNode(3);
		node2.next = new ListNode(2);
		node2.next.next = new ListNode(1);

		ListNode node3 = new ListNode(2);
		node3.next = new ListNode(1);
		node3.next.next = new ListNode(9);

		ListNode[] aa = {node,node2,node3};

		System.out.println(mergeKLists(aa));;
	}
}
