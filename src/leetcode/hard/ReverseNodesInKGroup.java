package leetcode.hard;

/**
 * Created by duviteck. 07 Feb 2015.
 */
public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode newHead = null;
        ListNode lastReverted = null;
        ListNode firstNotReverted = null;

        while (head != null) {
            ListNode curHead = head;
            ListNode curTail = head;
            int count = 1;
            while (curTail.next != null && count < k) {
                curTail = curTail.next;
                count++;
            }

            if (count < k) {
                break;		// case of last part of list
            } else {
                firstNotReverted = curTail.next;
                curTail.next = null;
                ListNode revertResult = revert(curHead);

                if (newHead == null) {
                    newHead = revertResult;
                } else {
                    lastReverted.next = revertResult;
                }
                lastReverted = curHead;
                lastReverted.next = firstNotReverted;
            }

            head = firstNotReverted;
        }

        return (newHead != null) ? newHead : head;
    }

    private ListNode revert(ListNode head) {
        ListNode left = null;
        ListNode right = head;

        while (right != null) {
            ListNode cur = right;
            right = right.next;
            cur.next = left;
            left = cur;
        }

        return left;
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
