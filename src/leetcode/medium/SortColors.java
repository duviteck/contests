package leetcode.medium;

import java.util.Arrays;

/**
 * Created by duviteck. 20 Jan 2015.
 */
public class SortColors {

    public void sortColors(int[] A) {
        int[] sorted = new int[3];
        for (int i : A) {
            sorted[i]++;
        }

        Arrays.fill(A, 0, sorted[0], 0);
        Arrays.fill(A, sorted[0], sorted[0] + sorted[1], 1);
        Arrays.fill(A, sorted[0] + sorted[1], A.length, 2);
    }
}
