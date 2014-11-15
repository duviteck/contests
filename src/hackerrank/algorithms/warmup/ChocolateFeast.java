package hackerrank.algorithms.warmup;

import java.util.Scanner;

/**
 * Created by duviteck. 21 Jun 2014.
 */
public class ChocolateFeast {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int tests = s.nextInt();

        for (int i = 0; i < tests; i++) {
            int n = s.nextInt();
            int c = s.nextInt();
            int m = s.nextInt();
            int ans = calcAns(n, c, m);
            System.out.println(ans);
        }
    }

    private static int calcAns(int n, int c, int m) {
        int bought = n / c;
        int gift = 0;
        int wrappers = bought;
        while (wrappers >= m) {
            int exchange = wrappers / m;
            gift += exchange;
            wrappers = wrappers % m + exchange;
        }
        return bought + gift;
    }
}
