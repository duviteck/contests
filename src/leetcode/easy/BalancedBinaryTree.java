package leetcode.easy;

/**
 * Created by duviteck. 08 Dec 2014.
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != null;
    }

    private Integer getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        Integer hLeft = getHeight(node.left);
        if (hLeft == null) {
            return null;
        }

        Integer hRight = getHeight(node.right);
        if (hRight == null) {
            return null;
        }

        if (Math.abs(hLeft - hRight) > 1) {
            return null;
        }

        return 1 + Math.max(hLeft, hRight);
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
