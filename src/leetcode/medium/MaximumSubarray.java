package leetcode.medium;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 *
 * Created by duviteck. 02 Dec 2014.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] A) {
        int max = 0;
        int cur = 0;
        for (int i : A) {
            cur = Math.max(0, cur + i);
            if (cur > max) {
                max = cur;
            }
        }

        if (max == 0) {
            // no one positive val
            max = A[0];
            for (int i : A) {
                if (i > max) {
                    max = i;
                }
            }
        }

        return max;
    }
}
