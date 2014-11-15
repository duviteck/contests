package hackerrank.algorithms.warmup;

import java.util.Scanner;

/**
 * Created by duviteck. 21 Jun 2014.
 */
public class TheLoveLetterMystery {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tests = s.nextInt();
        s.nextLine();

        for (int i = 0; i < tests; i++) {
            char[] word = s.nextLine().toCharArray();
            int ans = calcAns(word);
            System.out.println(ans);
        }
    }

    private static int calcAns(char[] buf) {
        int ans = 0;
        int last = buf.length - 1;
        int k = buf.length / 2;
        for (int i = 0; i < k; i++) {
            ans += Math.abs(buf[i] - buf[last - i]);
        }
        return ans;
    }
}
