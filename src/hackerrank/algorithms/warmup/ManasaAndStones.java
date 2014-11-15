package hackerrank.algorithms.warmup;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by duviteck. 21 Jun 2014.
 */
public class ManasaAndStones {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int tests = s.nextInt();

        for (int i = 0; i < tests; i++) {
            int n = s.nextInt();
            int a = s.nextInt();
            int b = s.nextInt();
            int[] ans = calcAns(n, a, b);
            printArray(ans, n);
        }
    }

    private static int[] calcAns(int n, int a, int b) {
        int[] ar = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = a * i + b * (n - i - 1);
        }
        Arrays.sort(ar);
        return ar;
    }

    private static void printArray(int[] ar, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(ar[0]);
        sb.append(" ");
        for (int i = 1; i < n; i++) {
            if (ar[i] != ar[i - 1]) {
                sb.append(ar[i]);
                sb.append(" ");
            }
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb.toString());
    }
}
