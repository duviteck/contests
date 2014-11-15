package codeforces.round217_div2;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_B {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int gamers = s.nextInt();

        HashSet[] sets = new HashSet[gamers];
        for (int i = 0; i < gamers; i++) {
            int n = s.nextInt();
            sets[i] = new HashSet(n);
            for (int j = 0; j < n; j++) {
                sets[i].add(s.nextInt());
            }
        }

        for (int i = 0; i < gamers; i++) {
            boolean ans = true;
            for (int j = 0; j < gamers; j++) {
                if (i == j) {
                    continue;
                }
                if (sets[i].containsAll(sets[j])) {
                    ans = false;
                    break;
                }
            }
            if (ans) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
