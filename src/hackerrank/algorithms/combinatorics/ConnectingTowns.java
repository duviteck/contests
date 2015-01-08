package hackerrank.algorithms.combinatorics;

import java.util.Scanner;

/**
 * Created by duviteck. 28 Dec 2014.
 */
public class ConnectingTowns {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tests = s.nextInt();

        for (int i = 0; i < tests; i++) {
            int len = s.nextInt();
            long ans = 1L;
            for (int j = 1; j < len; j++) {
                long next = s.nextLong();
                ans = (ans * next) % 1234567;
            }
            System.out.println(ans);
        }
    }
}