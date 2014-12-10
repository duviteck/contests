package leetcode.medium;

/**
 * Created by duviteck. 11 Dec 2014.
 */
public class Cycle2 {

    public static void main(String[] args) {
        System.out.println(detectCycle(null));
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;


        while (fast != null && fast.next != null) {
            if (slow.val == fast.val) {
                break;
            } else {
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }

        // start looking for cycle start
        slow = head;
        while (slow.val != fast.val) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
