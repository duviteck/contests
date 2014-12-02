package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by duviteck. 02 Dec 2014.
 */
public class TwoSum2 {

    // O(n) runtime, O(n) space

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(numbers.length);
        for (int i = 0; i < numbers.length; i++) {
            int val = numbers[i];
            ArrayList<Integer> list = map.get(val);
            if (list == null) {
                list = new ArrayList<Integer>(2);
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
