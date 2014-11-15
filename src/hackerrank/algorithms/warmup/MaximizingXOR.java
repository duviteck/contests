package hackerrank.algorithms.warmup;

import java.util.Scanner;

/**
 * Created by duviteck. 21 Jun 2014.
 */
public class MaximizingXOR {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int a = s.nextInt();
        int b = s.nextInt();
        int ans = calcAns(a, b);
        System.out.println(ans);
    }

    private static int calcAns(int a, int b) {
        int max = Integer.MIN_VALUE;
        for (int i = a; i < b; i++) {
            for (int j = i + 1; j <= b; j++) {
                int xor = i ^ j;
                if (xor > max) {
                    max = xor;
                }
            }
        }
        return max;
    }
}
