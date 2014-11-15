package codeforces.round227_div2;

import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2014 year
 */
public class Problem_C {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        char[] line = s.nextLine().trim().toCharArray();

        int ans = 0;
        int i = line.length - 1;
        while (i >= 0) {
            int curStart = i;
            while (line[curStart] == '0') {
                curStart--;
            }
            int len = i - curStart + 1;

            ans++;
            if (len > curStart) {
                break;
            } else if (len == curStart) {
                // compare
                if (line[0] >= line[curStart]) {
                    i = curStart - 1;
                } else {
                    break;
                }
            } else {
                i = curStart - 1;
            }
        }

        System.out.println(ans);
    }
}
