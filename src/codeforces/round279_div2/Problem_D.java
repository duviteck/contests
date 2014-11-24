package codeforces.round279_div2;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by duviteck. 23 Nov 2014.
 */
public class Problem_D {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long a1 = s.nextLong();
        long a2 = s.nextLong();
        long b1 = s.nextLong();
        long b2 = s.nextLong();
        s.close();

        // check ability to get to equal size
        long a = a1 * a2;
        long b = b1 * b2;
        boolean isPossible = checkAbility(a, b);
        if (!isPossible) {
            System.out.println("-1");
            return;
        }

        int[] divA23 = calc23divs(a);
        int[] divB23 = calc23divs(b);

        int changeCount = 0;
        // make counts of 3s equal
        while (divA23[1] > divB23[1]) {
            if (a1 % 3 == 0) {
                a1 = a1 / 3 * 2;
            } else {
                a2 = a2 / 3 * 2;
            }
            divA23[1]--;
            divA23[0]++;
            changeCount++;
        }
        while (divB23[1] > divA23[1]) {
            if (b1 % 3 == 0) {
                b1 = b1 / 3 * 2;
            } else {
                b2 = b2 / 3 * 2;
            }
            divB23[1]--;
            divB23[0]++;
            changeCount++;
        }

        // make counts of 2s equal
        while (divA23[0] > divB23[0]) {
            if (a1 % 2 == 0) {
                a1 /= 2;
            } else {
                a2 /= 2;
            }
            divA23[0]--;
            changeCount++;
        }
        while (divB23[0] > divA23[0]) {
            if (b1 % 2 == 0) {
                b1 /= 2;
            } else {
                b2 /= 2;
            }
            divB23[0]--;
            changeCount++;
        }

        System.out.println(changeCount);
        System.out.println(a1 + " " + a2);
        System.out.println(b1 + " " + b2);
    }

    private static boolean checkAbility(long a, long b) {
        long gcd = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).longValue();
        a /= gcd;
        b /= gcd;

        // check that A is 2^k * 3^m
        long divider = 2;
        while (a > 1) {
            if (a % divider == 0) {
                a /= divider;
            } else {
                if (divider < 3) {
                    divider = 3;
                } else {
                    break;
                }
            }
        }
        if (a > 1) {
            return false;
        }

        // check that B is 2^k * 3^m
        divider = 2;
        while (b > 1) {
            if (b % divider == 0) {
                b /= divider;
            } else {
                if (divider < 3) {
                    divider = 3;
                } else {
                    break;
                }
            }
        }
        if (b > 1) {
            return false;
        }

        return true;
    }

    private static int[] calc23divs(long i) {
        int[] ans = new int[2];
        while (i % 2 == 0) {
            i /= 2;
            ans[0]++;
        }
        while (i % 3 == 0) {
            i /= 3;
            ans[1]++;
        }
        return ans;
    }
}
