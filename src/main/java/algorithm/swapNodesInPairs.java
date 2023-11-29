package algorithm;


/**
 *  两两交换链表节点
 */
public class swapNodesInPairs {

     public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }


    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
        ListNode root = new ListNode();
        ListNode child1 = new ListNode();
        ListNode child2 = new ListNode();
        ListNode child3 = new ListNode();
        root.val = 1;
        child1.val = 2;
        child2.val = 3;
        child3.val = 4;
        root.next = child1;
        child1.next = child2;
        child2.next = child3;
        ListNode resultNode = swapPairs(root);
        System.out.println(resultNode);
    }
}
