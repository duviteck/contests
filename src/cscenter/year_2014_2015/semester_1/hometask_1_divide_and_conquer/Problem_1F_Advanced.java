package cscenter.year_2014_2015.semester_1.hometask_1_divide_and_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Little fridge
 *
 * Created by duviteck. 24 Dec 2014.
 */
public class Problem_1F_Advanced {

    private static long n;
    private static List<PrimeMult> primes;
    private static long[] bestParams;
    private static long[] curParams;
    private static long bestSquare;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        initParams(s.nextLong());

        calcAns();

        printAns();
    }

    private static void initParams(long num) {
        n = num;
        primes = factorize(n);

        bestParams = new long[3];
        Arrays.fill(bestParams, -1L);
        curParams = new long[3];
        Arrays.fill(curParams, -1L);
        bestSquare = Long.MAX_VALUE;
    }

    private static void printAns() {
        System.out.format("%d %d %d %d\n", 2 * bestSquare, bestParams[0], bestParams[1], bestParams[2]);
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

    private static List<PrimeMult> factorize(long n) {
        List<PrimeMult> res = new ArrayList<>();
        PrimeEratosGenerator primeGen = new PrimeEratosGenerator(n);
        while (n > 1) {
            int prime = primeGen.getNext();
            if (prime == PrimeEratosGenerator.NO_VALUE) {
                // n is already prime
                res.add(new PrimeMult(n, 1));
                break;
            }

            int power = 0;
            while (n % prime == 0) {
                power++;
                n /= prime;
            }
            if (power > 0) {
                res.add(new PrimeMult(prime, power));
            }
        }
        return res;
    }


    private static class PrimeMult {
        long prime;
        int order;

        public PrimeMult(long prime, int order) {
            this.prime = prime;
            this.order = order;
        }
    }


    private static class PrimeEratosGenerator {
        static final int NO_VALUE = -1;

        byte[] sieve;
        int max;
        List<Integer> primes = new ArrayList<>();

        public PrimeEratosGenerator(long maxVal) {
            max = (int) Math.sqrt(maxVal);
            sieve = new byte[max + 1];
            sieve[0] = sieve[1] = 1;
        }

        public int getNext() {
            if (max <= 1) {
                return NO_VALUE;
            }

            int curPrimesCount = primes.size();
            if (primes.isEmpty()) {
                findNext(2);
            } else {
                findNext(primes.get(primes.size() - 1) + 1);
            }

            if (primes.size() == curPrimesCount) {
                return NO_VALUE;
            } else {
                return primes.get(primes.size() - 1);
            }
        }

        private void findNext(int val) {
            while (val <= max) {
                if (sieve[val] == 0) {
                    primes.add(val);
                    for (int i = val * 2; i <= max; i += val) {
                        sieve[i] = 1;
                    }
                    return;
                }

                val++;
            }
        }
    }
}
