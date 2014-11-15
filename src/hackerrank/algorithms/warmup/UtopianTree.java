package hackerrank.algorithms.warmup;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by duviteck. 21 Jun 2014.
 */
public class UtopianTree {
    private static final BigInteger TWO = new BigInteger("2");

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tests = s.nextInt();

        for (int i = 0; i < tests; i++) {
            int n = s.nextInt();
            BigInteger ans = BigInteger.ONE;
            boolean needMult = true;
            for (int j = 0; j < n; j++) {
                if (needMult) {
                    ans = ans.multiply(TWO);
                } else {
                    ans = ans.add(BigInteger.ONE);
                }
                needMult = !needMult;
            }
            System.out.println(ans.toString());
        }
    }
}
