package algorithm;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * https://leetcode-cn.com/problems/add-two-numbers/
 *
 */
public class AddTowNumbers {


     public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
         StringBuffer sb1 = new StringBuffer();
         StringBuffer sb2 = new StringBuffer();

         sb1.append(l1.val);
         while (l1.next != null) {
             sb1.append(l1.next.val);
             l1 = l1.next;
         }

        sb2.append(l2.val);
        while (l2.next != null) {
            sb2.append(l2.next.val);
            l2 = l2.next;
        }

        String calculateResult = new BigDecimal(sb1.reverse().toString()).add(new BigDecimal(sb2.reverse().toString())).toString();
        char[] calculateResults =new StringBuffer(calculateResult).reverse().toString().toCharArray();
        ListNode result = new ListNode(calculateResults[0]);
        for (int i = 1; i < calculateResults.length; i++) {
            result.next = new ListNode(calculateResults[i]);
        }
        System.out.println(result);
        return result;
    }

    public void test() {
        // 1 -> 2 -> 3
        ListNode l1 = new ListNode(2,new ListNode(4,new ListNode(3)));
        // 3 -> 2 -> 1
        ListNode l2 = new ListNode(5,new ListNode(6,new ListNode(4)));

        addTwoNumbers(l1,l2);
    }

    public static void main(String[] args) {
        new AddTowNumbers().test();
    }


}
