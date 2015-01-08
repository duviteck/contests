package hackerrank.algorithms.combinatorics;

import java.util.Scanner;

/**
 * Created by duviteck. 28 Dec 2014.
 */
public class Handshake {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int testsNum = s.nextInt();

        StringBuilder sb = new StringBuilder(testsNum * 8);
        for (int i = 0; i < testsNum; i++) {
            long n = s.nextInt();
            long ans = calcAns(n);
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static long calcAns(long n) {
        return n * (n - 1) / 2;
    }
}
