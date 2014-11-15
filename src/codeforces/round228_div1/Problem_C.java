package codeforces.round228_div1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2014 year
 */
public class Problem_C {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        int ans1 = 0;
        int ans2 = 0;
        ArrayList<Integer> common = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            int len = s.nextInt();
            int l1 = len / 2;
            for (int j = 0; j < l1; j++) {
                ans1 += s.nextInt();
            }
            if (len % 2 == 1) {
                common.add(s.nextInt());
            }
            for (int j = len - l1; j < len; j++) {
                ans2 += s.nextInt();
            }
        }

        Collections.sort(common, Collections.reverseOrder());
        int order = 0;
        int[] ansAr = new int[2];
        for (int j : common) {
            ansAr[order] += j;
            order = (order + 1) % 2;
        }

        ans1 += ansAr[0];
        ans2 += ansAr[1];

        System.out.println(ans1 + " " + ans2);
    }
}
