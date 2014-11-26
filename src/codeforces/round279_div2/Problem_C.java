package codeforces.round279_div2;

import java.util.Scanner;

/**
 * Created by duviteck. 23 Nov 2014.
 */
public class Problem_C {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String numLine = s.nextLine();

        String[] divs = s.nextLine().split(" ");
        s.close();

        if (numLine.length() < divs[0].length() + divs[1].length()) {
            System.out.println("NO");
            return;
        }

        int len = numLine.length();
        int a = Integer.parseInt(divs[0]);
        int b = Integer.parseInt(divs[1]);
        int[] digits = new int[len];
        for (int i = 0; i < len; i++) {
            digits[i] = numLine.charAt(i) - '0';
        }

        int[] remA = new int[len];
        remA[0] = digits[0] % a;
        for (int i = 1; i < len - divs[1].length(); i++) {
            remA[i] = (remA[i - 1] * 10 + digits[i]) % a;
        }

        int[] remB = new int[len];
        remB[len - 1] = digits[len - 1] % b;
        int pow = 1;
        for (int i = len - 2; i >= divs[0].length(); i--) {
            pow = (pow * 10) % b;
            remB[i] = (digits[i] * pow + remB[i + 1]) % b;
        }

        for (int i = divs[0].length(); i <= len - divs[1].length(); i++) {
            if ((digits[i] == 0) || remA[i - 1] != 0 || remB[i] != 0) {
                continue;
            }

            System.out.println("YES");
            System.out.println(numLine.substring(0, i));
            System.out.println(numLine.substring(i));
            return;
        }

        System.out.println("NO");
    }
}
