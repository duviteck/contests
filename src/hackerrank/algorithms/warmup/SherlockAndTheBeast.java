package hackerrank.algorithms.warmup;

import java.util.Scanner;

/**
 * Created by duviteck. 22 Jun 2014.
 */
public class SherlockAndTheBeast {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int tests = s.nextInt();

        for (int i = 0; i < tests; i++) {
            int n = s.nextInt();
            String ans = calcAns(n);
            System.out.println(ans);
        }
    }

    private static String calcAns(int n) {
        int checks = n / 3;
        for (int i = checks; i >= 0; i--) {
            int rest = n - i * 3;
            if (rest % 5 == 0) {
                return getAnsLine(i * 3, rest);
            }
        }
        return "-1";
    }

    private static String getAnsLine(int fives, int threes) {
        StringBuilder sb = new StringBuilder(fives + threes);
        for (int i = 0; i < fives; i++) {
            sb.append("5");
        }
        for (int i = 0; i < threes; i++) {
            sb.append("3");
        }
        return sb.toString();
    }
}
