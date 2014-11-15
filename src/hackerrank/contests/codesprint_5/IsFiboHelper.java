package hackerrank.contests.codesprint_5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2014 year
 */
public class IsFiboHelper {

    private static final long MAX = 10000000000L;

    public static void main(String[] args) {
        List<Long> fib = new ArrayList<Long>();
        fib.add(0L);
        fib.add(1L);

        long a = 0;
        long b = 1;
        long temp;
        while (b <= MAX) {
            temp = a + b;
            fib.add(temp);
            a = b;
            b = temp;
        }

        System.out.print("[");
        for (long l : fib) {
            System.out.print(l);
            System.out.print(",");
        }
        System.out.println("]");
    }
}
