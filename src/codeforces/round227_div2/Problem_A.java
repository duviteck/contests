package codeforces.round227_div2;

import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2014 year
 */
public class Problem_A {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String s1 = s.nextLine().trim();
        String s2 = s.nextLine().trim();

        int time1 = parseTime(s1);
        int time2 = parseTime(s2);
        int res = time1 - time2;
        if (res < 0) {
            res += 24 * 60;
        }

        System.out.format("%02d:%02d", res / 60, res % 60);
    }

    private static int parseTime(String s) {
        String[] temp = s.split(":");
        int res = Integer.parseInt(temp[0]) * 60;
        res += Integer.parseInt(temp[1]);
        return res;
    }
}
