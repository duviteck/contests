package leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by duviteck. 05 Feb 2015.
 */
public class LargestNumber {

    public String largestNumber(int[] num) {
        Integer[] nums = new Integer[num.length];
        for (int i = 0; i < num.length; i++) {
            nums[i] = num[i];
        }

        Arrays.sort(nums, new Comparator<Integer> () {
            @Override
            public int compare(Integer a, Integer b) {
                long aL = buildLong(a, b);
                long bL = buildLong(b, a);
                return (aL > bL) ? -1 : (aL < bL) ? 1 : 0;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i : nums) {
            sb.append(i);
        }
        return removeLeadingZeroes(sb.toString());
    }

    private long buildLong(int a, int b) {
        long base = 10;
        while (base <= b) {
            base *= 10;
        }
        return (long) a * base + (long) b;
    }

    private String removeLeadingZeroes(String line) {
        final int len = line.length();
        int i = 0;

        while (i < (len - 1) && line.charAt(i) == '0') {
            i++;
        }
        return line.substring(i);
    }
}
