package leetcode.easy;

/**
 * Created by duviteck. 07 Feb 2015.
 */
public class FactorialTrailingZeroes {

    // in origin problem, input N is (int), not (long)
    public int trailingZeroes(long n) {
        long count2 = 0;
        long base2 = 2;
        long count5 = 0;
        long base5 = 5;

        while (base2 <= n) {
            count2 += (n / base2);
            base2 *= 2L;
        }
        while (base5 <= n) {
            count5 += (n / base5);
            base5 *= 5L;
        }

        return (int)Math.min(count2, count5);
    }
}
