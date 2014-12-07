package leetcode.hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by duviteck. 08 Dec 2014.
 */
public class CopyListWithRandomPointer {

    Queue<RandomListNode> queue = new LinkedList<>();
    Map<RandomListNode, RandomListNode> map = new HashMap<>();

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        RandomListNode copyNode = new RandomListNode(head.label);
        map.put(head, copyNode);
        queue.add(head);

        while (queue.size() > 0) {
            copyLinks();
        }

        return map.get(head);
    }

    private void copyLinks() {
        RandomListNode originNode = queue.poll();
        RandomListNode copyNode = map.get(originNode);

        copyNode.next = copy(originNode.next);
        copyNode.random = copy(originNode.random);
    }

    private RandomListNode copy(RandomListNode origin) {
        if (origin == null) {
            return null;
        }

        RandomListNode existingCopy = map.get(origin);
        if (existingCopy != null) {
            return existingCopy;
        }

        RandomListNode newCopy = new RandomListNode(origin.label);
        map.put(origin, newCopy);
        queue.add(origin);
        return newCopy;
    }


    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }
}
