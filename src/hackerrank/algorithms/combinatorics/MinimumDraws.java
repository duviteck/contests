package hackerrank.algorithms.combinatorics;

import java.util.Scanner;

/**
 * Created by duviteck. 28 Dec 2014.
 */
public class MinimumDraws {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int testsNum = s.nextInt();

        StringBuilder sb = new StringBuilder(testsNum * 8);
        for (int i = 0; i < testsNum; i++) {
            int n = s.nextInt();
            sb.append(n + 1).append("\n");
        }
        System.out.println(sb.toString());
    }
}
