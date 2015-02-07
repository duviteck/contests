package leetcode.medium;

/**
 * Created by duviteck. 07 Feb 2015.
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int add = 0;
        while (l1 != null || l2 != null || add > 0) {
            int i1 = (l1 == null) ? 0 : l1.val;
            int i2 = (l2 == null) ? 0 : l2.val;
            int newVal = i1 + i2 + add;

            ListNode newNode;
            if (newVal >= 10) {
                newNode = new ListNode(newVal - 10);
                add = 1;
            } else {
                newNode = new ListNode(newVal);
                add = 0;
            }

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return head;
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
