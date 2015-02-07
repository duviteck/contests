package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by duviteck. 07 Feb 2015.
 */
public class BinarySearchTreeIterator {

    private Deque<Pair> stack;

    public BinarySearchTreeIterator(TreeNode root) {
        stack = new LinkedList<>();
        stack.push(new Pair(root));
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        moveToNext();
        return (stack.size() > 0);
    }

    /** @return the next smallest number */
    public int next() {
        if (hasNext()) {
            Pair curPair = stack.peek();
            incFlag(curPair);
            return curPair.node.val;
        } else {
            return -1;
        }
    }

    private void moveToNext() {
        if (stack.size() == 0) {
            return;
        }

        Pair curPair = stack.peek();
        if (curPair.node == null) {
            stack.pop();
            moveToNext();
            return;
        }

        int flag = curPair.flag;
        if(flag == 0) {
            incFlag(curPair);
            stack.push(new Pair(curPair.node.left));
            moveToNext();
        } else if (flag == 2) {     // if flag == 1 do nothing
            incFlag(curPair);
            stack.push(new Pair(curPair.node.right));
            moveToNext();
        } else if (flag == 3) {
            stack.pop();
            moveToNext();
        }
    }

    private void incFlag(Pair pair) {
        pair.flag++;
    }


    private static class Pair {
        TreeNode node;
        int flag;

        public Pair(TreeNode node) {
            this.node = node;
            this.flag = 0;
        }
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
