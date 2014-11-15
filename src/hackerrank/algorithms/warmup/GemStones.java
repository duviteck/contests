package hackerrank.algorithms.warmup;

import java.util.Scanner;

/**
 * Created by duviteck. 22 Jun 2014.
 */
public class GemStones {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.nextLine();

        int[][] table = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] line = s.nextLine().toCharArray();
            for (char c : line) {
                table[i][c - 'a']++;
            }
        }

        int ans = 0;
        for (int i = 0; i < 26; i++) {
            boolean allHas = true;
            for (int j = 0; j < n; j++) {
                allHas &= table[j][i] > 0;
            }
            if (allHas) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}
