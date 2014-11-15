package codeforces.round228_div1;

import java.util.Arrays;
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

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.nextInt();
        }
        Arrays.sort(a);

        int l = 1;
        int r = 100;
        while (l <= r) {
//            System.out.println(l + " " + r);
            int m = (l + r) / 2;
            boolean res = check(a, m);
            if (!res) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        System.out.println(l);
    }

    private static boolean check(int[] a, int m) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] < i / m) {
                return false;
            }
        }

        return true;
    }
}
