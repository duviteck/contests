package project_euler;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_23 {

    private static int[] abundant;
    private static long inputNum;
    private static long[][] powers;
    private static long[] allDividers;
    private static int allDividersIndex;

    public static void main(String[] args) {
        abundant = findAbundant();

        long ans = 0;
        for (int i = 1; i <= 28123; i++) {
            if (!canBeWrittenAsSum(i)) {
                ans += i;
                System.out.println(i);
            }
        }

        System.out.println("Ans: " + ans);
    }

    private static int[] findAbundant() {
        List<Integer> list = new ArrayList<>();
        list.add(12);

        for (int i = 14; i <= 28123; i++) {
            if (isAbundant(i)) {
                list.add(i);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    private static boolean canBeWrittenAsSum(int i) {
        if (i <= abundant[0]) {
            return false;
        }

        int l = 0;
        int r = abundant.length - 1;
        while (l <= r) {
            int sum = abundant[l] + abundant[r];
            if (sum < i) {
                l++;
            } else if (sum > i) {
                r--;
            } else {
                return true;
            }
        }

        return false;
    }

    private static boolean isAbundant(int i) {
        inputNum = i;
        getPrimeDividers();
        createAllDividers();

        long sum = 0;
        for (long l : allDividers) {
            sum += l;
        }
        sum -= i;

        return sum > i;
    }

    private static void getPrimeDividers() {
        PollardRho pollard = new PollardRho();
        Object[] factors = pollard.getFactors(inputNum);

        long[] primeDividers = (long[]) factors[0];
        int[] primeDividersPowers = (int[]) factors[1];

        // Prepare powers of each prime divider
        int numOfDividers = primeDividers.length;
        powers = new long[numOfDividers][];
        for (int i = 0; i < numOfDividers; i++) {
            long curDivider = primeDividers[i];
            long[] curDividerPowers = new long[primeDividersPowers[i] + 1];

            curDividerPowers[0] = 1;
            for (int j = 1; j < curDividerPowers.length; j++) {
                curDividerPowers[j] = curDividerPowers[j - 1] * curDivider;
            }

            powers[i] = curDividerPowers;
        }
    }

    private static void createAllDividers() {
        int numOfAllDividers = 1;
        for (long[] powersOfOnePrime : powers) {
            numOfAllDividers *= powersOfOnePrime.length;
        }
        allDividers = new long[numOfAllDividers];
        allDividersIndex = 0;

        calculateAllDividers(1, 0);
    }

    private static void calculateAllDividers(long prevResult, int curIndex) {
        if (curIndex == powers.length) {
            allDividers[allDividersIndex++] = prevResult;
            return;
        }

        long[] curPowers = powers[curIndex];
        for (long curPower : curPowers) {
            calculateAllDividers(prevResult * curPower, curIndex + 1);
        }
    }


    static class PollardRho {
        private static final BigInteger TWO = new BigInteger("2");
        private final SecureRandom random = new SecureRandom();
        private final Map<BigInteger, Integer> factors = new HashMap<>();

        private BigInteger rho(BigInteger N) {
            BigInteger divisor;
            BigInteger c = new BigInteger(N.bitLength(), random);
            BigInteger x = new BigInteger(N.bitLength(), random);
            BigInteger xx = x;

            // check divisibility by 2
            if (N.mod(TWO).compareTo(BigInteger.ZERO) == 0) {
                return TWO;
            }

            do {
                x = x.multiply(x).mod(N).add(c).mod(N);
                xx = xx.multiply(xx).mod(N).add(c).mod(N);
                xx = xx.multiply(xx).mod(N).add(c).mod(N);
                divisor = x.subtract(xx).gcd(N);
            } while ((divisor.compareTo(BigInteger.ONE)) == 0);

            return divisor;
        }

        private void factor(BigInteger N) {
            if (N.compareTo(BigInteger.ONE) == 0) {
                return;
            }
            if (N.isProbablePrime(20)) {
                if (!factors.containsKey(N)) {
                    factors.put(N, 1);
                } else {
                    factors.put(N, factors.get(N) + 1);
                }
                return;
            }
            BigInteger divisor = rho(N);
            factor(divisor);
            factor(N.divide(divisor));
        }

        public Object[] getFactors(long num) {
            factor(BigInteger.valueOf(num));

            int numOfDividers = factors.size();
            long[] dividers = new long[numOfDividers];
            int[] powers = new int[numOfDividers];

            // Get dividers in descending order
            BigInteger[] dividersBig = new BigInteger[numOfDividers];
            factors.keySet().toArray(dividersBig);
            Arrays.sort(dividersBig, Collections.reverseOrder());

            int cnt = 0;
            for (BigInteger divider : dividersBig) {
                dividers[cnt] = divider.longValue();
                powers[cnt] = factors.get(divider);
                cnt++;
            }

            return new Object[]{dividers, powers};
        }
    }
}
