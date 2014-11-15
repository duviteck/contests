package codeforces.round226_div2;

import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2014 year
 */
public class Problem_A {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int c = s.nextInt();

        int ans = 0;
        int a1 = 0;
        int a2 = 0;

        a1 = s.nextInt();
        for (int i = 0; i < n - 1; i++) {
            a2 = s.nextInt();
            if (a1 - a2 > ans) {
                ans = a1 - a2;
            }
            a1 = a2;
        }

        System.out.println(Math.max(ans - c, 0));
    }
}
