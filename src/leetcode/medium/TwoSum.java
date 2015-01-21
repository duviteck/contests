package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by duviteck. 02 Dec 2014.
 */
public class TwoSum {

    // O(n*log(n)) runtime, O(n) space
    public int[] twoSumSolution1(int[] numbers, int target) {
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


    // O(n) runtime, O(n) space
    public int[] twoSumSolution2(int[] numbers, int target) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(numbers.length);
        for (int i = 0; i < numbers.length; i++) {
            int val = numbers[i];
            ArrayList<Integer> list = map.get(val);
            if (list == null) {
                list = new ArrayList<>(2);
                map.put(val, list);
            }
            list.add(i + 1);
        }

        int[] res = new int[2];
        for (int i : map.keySet()) {
            ArrayList<Integer> list = map.get(target - i);
            if (list == null) {
                continue;
            }
            if (i == target - i && list.size() == 1) {
                continue;
            }

            ArrayList<Integer> list1 = map.get(i);
            res[0] = list1.get(0);
            if (i == target - i) {
                res[1] = list1.get(1);
            } else {
                res[1] = map.get(target - i).get(0);
            }
            break;
        }

        if (res[0] > res[1]) {
            // swap
            int temp = res[0];
            res[0] = res[1];
            res[1] = temp;
        }

        return res;
    }
}
