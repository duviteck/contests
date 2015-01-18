package cscenter.year_2014_2015.semester_1.hometask_4_data_structures;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Function
 *
 * Created by duviteck. 12 Jan 2015.
 */
public class Problem_4C_Base {

    private static final long MODULE = 1L << 32;
    private static Map<Long, Long> cache;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long n = s.nextLong();

        cache = new HashMap<>();
        cache.put(0L, 1L);
        cache.put(1L, 1L);
        cache.put(2L, 1L);

        long ans = calcValue(n);
        System.out.println(ans);
    }

    private static long calcValue(long n) {
        Long cachedValue = cache.get(n);
        if (cachedValue != null) {
            return cachedValue;
        }

        long a;
        long b;
        if (n % 2 == 1) {
            a = calcValue(n * 2 / 3);
            b = calcValue(n * 6 / 7);
        } else {
            a = calcValue(n - 3);
            b = calcValue(n - 1);
        }

        long res = (a + b) % MODULE;
        cache.put(n, res);
        return res;
    }
}
