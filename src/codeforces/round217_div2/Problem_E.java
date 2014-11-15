package codeforces.round217_div2;

import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_E {

    private static int n;
    private static int[] A;
    private static int[] mi;
    private static int[] ma;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();

        A = new int[n];
        mi = new int[n];
        ma = new int[n];

        for (int i = 0 ; i < n ; i++) {
            A[i] = s.nextInt();
        }
        s.close();

        if (hasSolution()) {
            System.out.println(A[n - 2]);

            StringBuilder sb = new StringBuilder();
            for (int i : A) {
                sb.append(i);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        } else {
            System.out.println("-1");
        }
    }

    private static boolean hasSolution() {
        if (A[0] > 0 && A[0] != 1) {
            return false;
        }

        mi[0] = 6 + 1;
        ma[0] = 6 + 1;

        for (int i = 1; i < n; i++) {
            if (mi[i - 1] % 6 == 5) {
                mi[i] = (mi[i - 1] / 6 + 1) * 6 + 1;
            } else {
                mi[i] = mi[i - 1] + 1;
            }

            if (ma[i - 1] % 6 >= 2) {
                ma[i] = (ma[i - 1] / 6 + 1) * 6 + 1;
            } else {
                ma[i] = ma[i - 1] + 1;
            }

            if (A[i] > 0) {
                if (mi[i] / 6 > A[i] || ma[i] / 6 < A[i]) {
                    return false;
                }

                mi[i] = Math.max(mi[i], A[i] * 6 + 1);
                ma[i] = Math.min(ma[i], A[i] * 6 + 5);
            }
        }

        if (ma[n - 1] % 6 == 1) {
            ma[n - 1] -= 2;
            if (ma[n - 1] < mi[n - 1]) {
                return false;
            }
        }

        A[n - 1] = ma[n - 1] / 6;

        for (int i = n - 2; i >= 0; i--) {
            ma[i] = Math.min(ma[i], (ma[i + 1] % 6 == 1) ? (ma[i + 1] - 2) : (ma[i + 1] - 1));
            A[i] = ma[i] / 6;
        }

        return true;
    }
}
