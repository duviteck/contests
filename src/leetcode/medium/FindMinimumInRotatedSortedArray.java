package leetcode.medium;

/**
 * Created by duviteck. 04 Dec 2014.
 */
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] num) {
        if (num.length == 1) {
            return num[0];
        }

        int l = 0;
        int r = num.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            int mid = num[m];
            if (mid > num[r]) { // min between m and r
                l = m + 1;
            } else {
                r = m;
            }
        }
        return num[l];
    }
}
