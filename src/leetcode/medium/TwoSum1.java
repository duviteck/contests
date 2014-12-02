package leetcode.medium;

import java.util.Arrays;

/**
 * Created by duviteck. 02 Dec 2014.
 */
public class TwoSum1 {

    // O(n*log(n)) runtime, O(n) space

    public int[] twoSum(int[] numbers, int target) {
        int[] sortedNums = numbers.clone();
        Arrays.sort(sortedNums);

        int i1 = 0;
        int i2 = sortedNums.length - 1;
        int val = 0;

        while (i1 < i2) {
            int sum = sortedNums[i1] + sortedNums[i2];
            if (sum == target) {
                val = sortedNums[i1];
                break;
            } else if (sum < target) {
                i1++;
            } else {
                i2--;
            }
        }

        // look for val and (target - val) in origin array
        int[] res = new int[2];
        res[0] = indexOfValue(numbers, val, 0);
        if (val != target - val) {
            res[1] = indexOfValue(numbers, target - val, 0);
        } else {
            res[1] = indexOfValue(numbers, target - val, res[0]);
        }

        if (res[0] > res[1]) {
            // swap
            int temp = res[0];
            res[0] = res[1];
            res[1] = temp;
        }

        return res;
    }

    private int indexOfValue(int[] nums, int target, int startIndex) {
        for (int i = startIndex; i < nums.length; i++) {
            if (nums[i] == target) {
                return i + 1;
            }
        }
        return 0;
    }
}
