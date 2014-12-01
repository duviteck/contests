package leetcode.hard;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Description:
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Created by duviteck. 01 Dec 2014.
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }

        Queue<Pair> heap = new PriorityQueue<>(lists.size(), pairComparator);
        int emptyListsCount = 0;
        for (int i = 0; i < lists.size(); i++) {
            ListNode root = lists.get(i);
            if (root == null) {
                emptyListsCount++;
            } else {
                heap.add(new Pair(root.val, i));
                lists.set(i, root.next);
            }
        }

        ListNode start = null;
        ListNode end = null;
        while (emptyListsCount < lists.size() || heap.size() > 0) {
            Pair minPair = heap.poll();

            ListNode newNode = new ListNode(minPair.fst);
            if (start == null) {
                start = newNode;
            } else {
                end.next = newNode;
            }
            end = newNode;

            ListNode minList = lists.get(minPair.snd);
            if (minList != null) {
                heap.add(new Pair(minList.val, minPair.snd));
                lists.set(minPair.snd, minList.next);
            } else {
                emptyListsCount++;
            }
        }

        return start;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private static class Pair {
        int fst;
        int snd;

        public Pair(int fst, int snd) {
            this.fst = fst;
            this.snd = snd;
        }
    }

    private static Comparator<Pair> pairComparator = new Comparator<Pair>() {
        @Override
        public int compare(Pair p1, Pair p2) {
            int v1 = p1.fst;
            int v2 = p2.fst;
            return (v1 < v2) ? -1 : (v1 > v2) ? 1 : 0;
        }
    };
}

