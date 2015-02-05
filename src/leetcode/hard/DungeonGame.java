package leetcode.hard;

import java.util.Arrays;

/**
 * Created by duviteck. 06 Feb 2015.
 */
public class DungeonGame {

    private static int n;	// lines
    private static int m;	// columns
    private static Integer[][] temp;

    public int calculateMinimumHP(int[][] dungeon) {
        n = dungeon.length;
        m = dungeon[0].length;
        temp = new Integer[n + 1][m + 1];

        // binary search by answer
        long min = 1;
        long max = Integer.MAX_VALUE;
        while (min <= max) {
            int med = (int)((min + max) / 2);

            boolean check = checkHP(dungeon, med);
            if (check) {
                max = med - 1;
            } else {
                min = med + 1;
            }
        }
        return (int)max + 1;
    }

    private boolean checkHP(int[][] dungeon, int hp) {
        for (Integer[] ar : temp) {
            Arrays.fill(ar, null);
        }

        temp[0][1] = hp;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                Integer newVal1 = (temp[i - 1][j] == null)
                        ? null
                        : temp[i - 1][j] + dungeon[i - 1][j - 1];
                Integer newVal2 = (temp[i][j - 1] == null)
                        ? null
                        : temp[i][j - 1] + dungeon[i - 1][j - 1];
                Integer newVal =
                        (newVal1 != null && newVal2 != null && max(newVal1, newVal2) > 0) ? max(newVal1, newVal2) :
                                (newVal1 != null && newVal1 > 0) ? newVal1 :
                                        (newVal2 != null && newVal2 > 0) ? newVal2 :
                                                null;
                temp[i][j] = newVal;
            }
        }

        return temp[n][m] != null;
    }

    private Integer max(Integer i1, Integer i2) {
        return (i1 >= i2) ? i1 : i2;
    }
}
