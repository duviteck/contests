package codeforces.round226_div2;

import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2014 year
 */
public class Problem_B {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String line = s.nextLine();

        long ans = 0;
        int start;
        int prevStart = -1;
        while ((start = line.indexOf("bear", prevStart + 1)) != -1) {
            if (start == 0) {
                ans += line.length() - start - 3;
            } else {
                ans += (start - prevStart) * (line.length() - start - 3);
            }

            prevStart = start;
        }

        System.out.println(ans);
    }
}
