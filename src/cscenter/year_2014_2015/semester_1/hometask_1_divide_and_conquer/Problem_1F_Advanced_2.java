package cscenter.year_2014_2015.semester_1.hometask_1_divide_and_conquer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Little fridge
 *
 * Created by duviteck. 24 Dec 2014.
 */
public class Problem_1F_Advanced_2 {

    // Incorrect solution

    private static FactorizedNumber input;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        long n = s.nextLong();
        input = factorize(n);

        long[] sides = calcAns(n);
        printAns(sides);
    }

    private static long[] calcAns(long n) {
        // first side will be <= cube_root_N, but as close to cube_root_N as possible
        FactorizedNumber aFactored = findClosest((long) Math.cbrt(n));
        input.decrease(aFactored);

        FactorizedNumber bFactored = findClosest((long) Math.sqrt(n / aFactored.val));
        input.decrease(bFactored);

        long c = n / aFactored.val / bFactored.val;
        return new long[] {aFactored.val, bFactored.val, c};
    }

    private static void printAns(long[] sides) {
        long square = sides[0] * sides[1] + sides[0] * sides[2] + sides[1] * sides[2];
        System.out.format("%d %d %d %d\n", 2 * square, sides[0], sides[1], sides[2]);
    }

    private static FactorizedNumber findClosest(long n) {
        FactorizedNumber current = new FactorizedNumber();
        return traverseInputCases(0, current, n);
    }

    private static FactorizedNumber traverseInputCases(int index, FactorizedNumber current, long n) {
        FactorizedNumber best = new FactorizedNumber(current);
        if (index == input.size()) {
            return best;
        }

        PrimeMult prime = input.get(index);
        long mult = 1;
        for (int i = 0; i <= prime.order; i++) {
            if (current.val * mult > n) {
                break;
            }

            current.mult(index, prime.prime, i);
            FactorizedNumber bestCandidate = traverseInputCases(index + 1, current, n);
            current.div(index, i);

            mult *= prime.prime;

            if (bestCandidate.val > best.val) {
                best = bestCandidate;
            }
        }
        return best;
    }

    private static FactorizedNumber factorize(long n) {
        FactorizedNumber res = new FactorizedNumber();
        PrimeEratosGenerator primeGen = new PrimeEratosGenerator(n);
        while (n > 1) {
            int prime = primeGen.getNext();
            if (prime == PrimeEratosGenerator.NO_VALUE) {
                // n is already prime
                res.add(new PrimeMult(n, 1));
                break;
            }

            int order = 0;
            while (n % prime == 0) {
                order++;
                n /= prime;
            }
            if (order > 0) {
                res.add(new PrimeMult(prime, order));
            }
        }
        return res;
    }


    private static class FactorizedNumber {
        long val;
        List<PrimeMult> primes;

        public FactorizedNumber() {
            val = 1L;
            primes = new ArrayList<>();
        }

        public FactorizedNumber(FactorizedNumber fn) {
            val = fn.val;
            primes = new ArrayList<>(fn.primes.size());
            for (PrimeMult prime : fn.primes) {
                primes.add(new PrimeMult(prime));
            }
        }

        public void mult(int index, long prime, int order) {
            if (index >= primes.size()) {
                primes.add(new PrimeMult(prime, order));
            } else {
                PrimeMult primeMult = primes.get(index);
                primeMult.order += order;
            }

            val *= (long) Math.pow(prime, order);
        }

        public void div(int index, int order) {
            PrimeMult primeMult = primes.get(index);
            primeMult.order -= order;

            val /= (long) Math.pow(primeMult.prime, order);
        }

        public int size() {
            return primes.size();
        }

        public PrimeMult get(int index) {
            return primes.get(index);
        }

        public void add(PrimeMult primeMult) {
            primes.add(primeMult);
        }

        public void decrease(FactorizedNumber num) {
            int size = num.size();
            for (int i = 0; i < size; i++) {
                primes.get(i).order -= num.get(i).order;
            }
            val /= num.val;
        }
    }


    private static class PrimeMult {
        long prime;
        int order;

        public PrimeMult(long prime, int order) {
            this.prime = prime;
            this.order = order;
        }

        public PrimeMult(PrimeMult mult) {
            this.prime = mult.prime;
            this.order = mult.order;
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
