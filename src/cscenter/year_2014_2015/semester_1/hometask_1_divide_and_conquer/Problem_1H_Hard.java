package cscenter.year_2014_2015.semester_1.hometask_1_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Big fridge
 *
 * Created by duviteck. 03 Jan 2015.
 */
public class Problem_1H_Hard {

    // TL

    private static long n;
    private static List<PrimeMult> primes;
    private static long[] bestParams;
    private static long[] curParams;
    private static long bestSquare;

    private static StringBuilder sb = new StringBuilder(500 * 40);

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tests = Integer.parseInt(reader.readLine());

        for (int i = 0; i < tests; i++) {
            initParams(reader);

            calcAns();

            printAns();
        }
        System.out.println(sb.toString());
    }

    private static void initParams(BufferedReader reader) throws IOException {
        int temp = Integer.parseInt(reader.readLine());
        primes = new ArrayList<>(temp);
        n = 1;

        for (int i = 0; i < temp; i++) {
            String[] tokens = reader.readLine().split(" ");
            long prime = Integer.parseInt(tokens[0]);
            int order = Integer.parseInt(tokens[1]);
            primes.add(new PrimeMult(prime, order));
            n *= (long) Math.pow(prime, order);
        }

        bestParams = new long[3];
        Arrays.fill(bestParams, -1L);
        curParams = new long[3];
        Arrays.fill(curParams, -1L);
        bestSquare = Long.MAX_VALUE;
    }

    private static void printAns() {
        sb.append(2 * bestSquare).append(" ");
        sb.append(bestParams[0]).append(" ");
        sb.append(bestParams[1]).append(" ");
        sb.append(bestParams[2]).append("\n");
    }

    private static void calcAns() {
        // try all first elems (less or equal cube root N)
        traverseFirst(0, 1, (long) Math.cbrt(n));
    }

    private static void traverseFirst(int index, long curMult, long multFrame) {
        if (index == primes.size()) {
            curParams[0] = curMult;
            traverseSecond(0, 1, (long) Math.sqrt(n / curMult));
            return;
        }

        PrimeMult prime = primes.get(index);
        if (prime.order == 0) {
            traverseFirst(index + 1, curMult, multFrame);
            return;
        }

        long mult = 1;
        long newCurMult;
        for (int i = 0; i <= prime.order; i++) {
            newCurMult = curMult * mult;
            if (newCurMult > multFrame) {
                break;
            }

            prime.order -= i;
            traverseFirst(index + 1, curMult * mult, multFrame);
            prime.order += i;

            mult *= prime.prime;
        }
    }

    private static void traverseSecond(int index, long curMult, long multFrame) {
        if (index == primes.size()) {
            if (curMult >= curParams[0]) {
                curParams[1] = curMult;
                relaxAnswer();
            }
            return;
        }

        PrimeMult prime = primes.get(index);
        if (prime.order == 0) {
            traverseSecond(index + 1, curMult, multFrame);
            return;
        }

        long mult = 1;
        long newCurMult;
        for (int i = 0; i <= prime.order; i++) {
            newCurMult = curMult * mult;
            if (newCurMult > multFrame) {
                break;
            }

            prime.order -= i;
            traverseSecond(index + 1, curMult * mult, multFrame);
            prime.order += i;

            mult *= prime.prime;
        }
    }

    private static void relaxAnswer() {
        curParams[2] = n / curParams[0] / curParams[1];
        long curSquare = curParams[0] * curParams[1] + curParams[0] * curParams[2] + curParams[1] * curParams[2];
        if (curSquare < bestSquare) {
            bestSquare = curSquare;
            System.arraycopy(curParams, 0, bestParams, 0, 3);
        }
    }


    private static class PrimeMult {
        long prime;
        int order;

        public PrimeMult(long prime, int order) {
            this.prime = prime;
            this.order = order;
        }
    }
}
