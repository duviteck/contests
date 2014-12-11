package others.interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Purpose: calculate number of possible variants in Android Key Guard
 * <p/>
 * Created by duviteck. 12 Dec 2014.
 */
public class AndroidKeyGuard {
    /**
     * Assume that Key Guard has next view:
     * <p/>
     * 1 2 3
     * 4 5 6
     * 7 8 9
     */

    public static final int[] NEXT_FIRST = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static final int[] NEXT_1 = new int[]{2, 4, 5, 6, 8};
    public static final int[] NEXT_2 = new int[]{2, 4, 5, 6, 8};
    public static final int[] NEXT_3 = new int[]{2, 4, 5, 6, 8};
    public static final int[] NEXT_4 = new int[]{2, 4, 5, 6, 8};
    // Here should be arrays for all elements (1-9) in ascending order

    public static final int[][] NEXT_STEPS = new int[][]{
            NEXT_FIRST,
            NEXT_1,
            NEXT_2,
            NEXT_3,
            NEXT_4
            // Here should be arrays for rest elements (5-9)
    };


    public long calcCases() {
        long ans = 0;
        boolean[] used = new boolean[9];
        for (int i = 4; i <= 9; i++) {
            Arrays.fill(used, false);
            ans += calcCases(i, 0, 0, used);
        }
        return ans;
    }

    public long calcCases(int total, int numOfPassed, int lastUsed, boolean[] used) {
        if (numOfPassed == total) {
            return 1;
        }

        List<Integer> nextStep = getAllNextSteps(lastUsed, used);
        long ans = 0;
        for (int next : nextStep) {
            used[next - 1] = true;
            ans += calcCases(total, numOfPassed + 1, next, used);
            used[next - 1] = false;
        }
        return ans;
    }

    public List<Integer> getAllNextSteps(int lastUsed, boolean[] used) {
        List<Integer> ans = new ArrayList<>(9);
        int[] nextSteps = NEXT_STEPS[lastUsed];
        for (int i = 1; i <= 9; i++) {
            if (i == lastUsed || used[i - 1]) {
                continue;
            }
            int binaryIndex = Arrays.binarySearch(nextSteps, i);
            if (binaryIndex >= 0) {
                ans.add(i);
            } else {
                boolean check = checkAccessibility(lastUsed, i, used);
                if (check) {
                    ans.add(i);
                }
            }
        }

        return ans;
    }

    public boolean checkAccessibility(int a, int b, boolean[] used) {
        a--;
        b--;

        int[] aCoord = new int[]{a / 3, a % 3};
        int[] bCoord = new int[]{b / 3, b % 3};

        // check horizontal
        if (aCoord[0] == bCoord[0]) {
            int betweenVal = aCoord[0] * 3 + 1;
            return used[betweenVal];
        }

        // check vertical. NOT FINISHED HERE

        // check diagonals. NOT FINISHED HERE

        return false;
    }
}
