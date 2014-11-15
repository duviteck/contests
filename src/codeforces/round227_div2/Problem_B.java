package codeforces.round227_div2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2014 year
 */
public class Problem_B {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();

        int[] a = new int[n];
        int[] b = new int[m];
        for (int i = 0; i < n; i++) {
            a[i] = s.nextInt();
        }
        for (int i = 0; i < m; i++) {
            b[i] = s.nextInt();
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int pa = n - 1;
        int pb = m - 1;
        int ans = 0;
        while (pa >= 0 && pb >= 0) {
            if (a[pa] > b[pb]) {
                ans++;
                pa--;
            } else {
                pa--;
                pb--;
            }
        }
        if (pb < 0) {
            ans += pa + 1;
        }

        System.out.println(ans);
    }
}
