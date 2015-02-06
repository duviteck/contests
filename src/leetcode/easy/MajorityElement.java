package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by duviteck. 07 Feb 2015.
 */
public class MajorityElement {

    public int majorityElement(int[] num) {
        Map<Integer, Integer> map = new HashMap<>(num.length);
        for (int i : num) {
            Integer count = map.get(i);
            if (count == null) {
                map.put(i, 1);
            } else {
                map.put(i, count + 1);
            }
        }

        int target = num.length / 2;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > target) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
