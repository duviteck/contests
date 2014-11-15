package hackerrank.algorithms.warmup;

import java.util.Scanner;

/**
 * Created by duviteck. 21 Jun 2014.
 */
public class FillingJars {
    private static long sum = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        long n = s.nextInt();
        int m = s.nextInt();

        for (int i = 0; i < m; i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            long k = s.nextInt();
            sum += (long)(b - a + 1) * k;
        }

        System.out.println(sum / n);
    }
}
