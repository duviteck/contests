package hackerrank.contests.weekly_challenges.week_5;

import java.util.Scanner;

/**
 * Created by duviteck. 20 Jun 2014.
 */
public class EvenOddQuery {

    private static int n;
    private static int[] nums;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();

        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = s.nextInt();
        }

        int q = s.nextInt();
        StringBuilder sb = new StringBuilder(5 * q);
        for (int i = 0; i < q; i++) {
            int x = s.nextInt();
            int y = s.nextInt();
            String ans = f(x, y);
            sb.append(ans);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static String f(int x, int y) {
        return (x > y || nums[x - 1] % 2 == 1 || isZero(x + 1, y)) ? "Odd" : "Even";
    }

    private static boolean isZero(int x, int y) {
        return (x <= y) && (nums[x - 1] == 0);
    }
}
