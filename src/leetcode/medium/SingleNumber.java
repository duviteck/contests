package leetcode.medium;

/**
 * Created by duviteck. 07 Dec 2014.
 */
public class SingleNumber {
    public int singleNumber(int[] A) {
        int res = 0;
        for (int a : A) {
            res ^= a;
        }
        return res;
    }
}
