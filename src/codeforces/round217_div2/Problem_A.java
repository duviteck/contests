package codeforces.round217_div2;

import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_A {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int r1 = s.nextInt();
        int c1 = s.nextInt();
        int r2 = s.nextInt();
        int c2 = s.nextInt();

        int ladia = calcLadia(r1, c1, r2, c2);
        int elephant = calcElephant(r1, c1, r2, c2);
        int king = calcKing(r1, c1, r2, c2);

        System.out.println(ladia + " " + elephant + " " + king);
    }

    private static int calcLadia(int r1, int c1, int r2, int c2) {
        int ans = 0;
        if (r1 != r2) {
            ans++;
        }
        if (c1 != c2) {
            ans++;
        }
        return ans;
    }

    private static int calcElephant(int r1, int c1, int r2, int c2) {
        if (r1 == r2 && c1 == c2) {
            return 0;
        }
        if (r1 + c1 == r2 + c2) {
            return 1;
        }
        if (r1 - c1 == r2 - c2) {
            return 1;
        }
        if ((r1 + c1) % 2 != (r2 + c2) % 2) {
            return 0;
        }
        return 2;
    }

    private static int calcKing(int r1, int c1, int r2, int c2) {
        return Math.max(Math.abs(r1 - r2), Math.abs(c1 - c2));
    }
}
