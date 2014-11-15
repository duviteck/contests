package hackerrank.contests.codesprint_5;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2014 year
 */
public class SpecialMultiple {
    private static final PriorityQueue<Long> cases = new PriorityQueue<Long>();
    private static final HashMap<Long, Long> ansHash = new HashMap<Long, Long>(500);

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        StringBuilder sb = new StringBuilder(n * 30);
        Long res;
        for (int i = 0; i < n; i++) {
            long num = s.nextLong();

            res = ansHash.get(num);
            if (res == null) {
                res = calcAns(num);
                ansHash.put(num, res);
            }

            sb.append(res);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static long calcAns(long num) {
        cases.clear();
        cases.add(9L);

        long newElem;
        while (true) {
            long next = cases.poll();
            if (next % num == 0) {
                return next;
            } else {
                newElem = next * 10;
                if (newElem > next) {
                    cases.add(newElem);
                }
                newElem += 9;
                if (newElem > next) {
                    cases.add(newElem);
                }
            }
        }
    }
}
