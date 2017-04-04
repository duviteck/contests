package codeforces.vk_cup_2015.qualification;

import java.util.Scanner;

/**
 * http://codeforces.ru/contest/523/problem/C
 *
 * Created by duviteck on 14/03/15.
 */
public class Problem_C {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[] s = scanner.nextLine().toCharArray();
        char[] t = scanner.nextLine().toCharArray();
        scanner.close();

        int bestFirstEnd = findFromStart(s, t);
        int bestSecondStart = findFromEnd(s, t);
        int ans = Math.max(0, bestSecondStart - bestFirstEnd);
        System.out.println(ans);
    }

    private static int findFromStart(char[] s, char[] t) {
        int len = t.length;

        int checkIndex = 0;
        for (int i = 0; i < len; i++) {
            if (s[checkIndex] == t[i]) {
                checkIndex++;
                if (checkIndex == s.length) {
                    return i;
                }
            }
        }

        return t.length;
    }

    private static int findFromEnd(char[] s, char[] t) {
        int checkIndex = s.length - 1;
        for (int i = t.length - 1; i >= 0; i--) {
            if (s[checkIndex] == t[i]) {
                checkIndex--;
                if (checkIndex < 0) {
                    return i;
                }
            }
        }

        return 0;
    }
}
