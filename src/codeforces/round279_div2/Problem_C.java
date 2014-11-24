package codeforces.round279_div2;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by duviteck. 23 Nov 2014.
 */
public class Problem_C {

    public static final String YES = "Yes";
    public static final String NO = "NO";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String numLine = s.nextLine();

        String[] divs = s.nextLine().split(" ");
        s.close();

        String a = divs[0];
        String b = divs[1];
        BigInteger aNum = new BigInteger(a);
        BigInteger bNum = new BigInteger(b);

        if (numLine.length() < divs[0].length() + divs[1].length()) {
            System.out.println(NO);
            return;
        }

        char[] chars = numLine.toCharArray();
        for (int i = a.length(); i <= numLine.length() - b.length(); i++) {
            if (chars[i] == '0') {
                continue;
            }

            String aLine = numLine.substring(0, i);
            BigInteger aBig = new BigInteger(aLine);
            if (aBig.divideAndRemainder(aNum)[1].equals(BigInteger.ZERO)) {
                String bLine = numLine.substring(i);
                BigInteger bBig = new BigInteger(bLine);
                if (bBig.divideAndRemainder(bNum)[1].equals(BigInteger.ZERO)) {
                    System.out.println(YES);
                    System.out.println(aLine);
                    System.out.println(bLine);
                    return;
                }
            }
        }

        System.out.println(NO);
    }
}
