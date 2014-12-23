package cscenter.year_2014_2015.semester_1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Catalan numbers
 *
 * Created by duviteck. 24 Dec 2014.
 */
public class Problem_1E_Base {

    private static long mod;
    private static Map<Integer, Long> calculatedMultiplications = new HashMap<>();
    private static long[] catalanNumbers;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        mod = s.nextLong();

        catalanNumbers = new long[n + 1];
        catalanNumbers[0] = 1 % mod;

        for (int i = 1; i <= n; i++) {
            calcCatalan(i);
        }
        System.out.println(catalanNumbers[n]);
    }

    private static void calcCatalan(int n) {
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int j = n - i - 1;
            int cacheNum = getCacheNum(i, j);
            Long cachedMultiplication = calculatedMultiplications.get(cacheNum);
            if (cachedMultiplication == null) {
                cachedMultiplication = (catalanNumbers[i] * catalanNumbers[j]) % mod;
                calculatedMultiplications.put(cacheNum, cachedMultiplication);
            }
            ans += cachedMultiplication;
        }
        catalanNumbers[n] = ans % mod;
    }

    private static int getCacheNum(int i, int j) {
        if (i <= j) {
            return i * 1001 + j;
        } else {
            return j * 1001 + i;
        }
    }
}
