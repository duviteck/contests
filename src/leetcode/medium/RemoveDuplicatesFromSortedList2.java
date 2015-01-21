package leetcode.medium;

/**
 * Created by duviteck. 22 Jan 2015.
 */
public class RemoveDuplicatesFromSortedList2 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        node1.next = node2;
        node2.next = node3;
        ListNode res = deleteDuplicates(node1);
        System.out.println(res.val);
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode cur = head;
        head = null;
        ListNode tail = null;

        while (cur != null) {
            if (cur.next == null || cur.val != cur.next.val) {
                ListNode newCur = new ListNode(cur.val);
                if (head == null) {
                    head = newCur;
                    tail = newCur;
                } else {
                    tail.next = newCur;
                    tail = newCur;
                }
            } else {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
            }
            cur = cur.next;
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
