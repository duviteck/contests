package codeforces.round173_div2;

import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_A {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        s.nextLine();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            String line = s.nextLine();
            if (line.charAt(0) == '+' || line.charAt(1) == '+' || line.charAt(2) == '+') {
                ans++;
            } else {
                ans--;
            }
        }

        System.out.println(ans);
    }

}
