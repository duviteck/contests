package hackerrank.algorithms.warmup;

import java.util.Scanner;

/**
 * Created by duviteck. 21 Jun 2014.
 */
public class HalloweenParty {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tests = s.nextInt();

        for (int i = 0; i < tests; i++) {
            long k = s.nextInt();
            long ans = calcAns(k);
            System.out.println(ans);
        }
    }

    private static long calcAns(long k) {
        long half = k / 2;
        return half * (k - half);
    }
}
