package cscenter.year_2013_2014;

import java.util.Arrays;
import java.util.Scanner;

public class Advanced_Problem_4E {

    private static int[] b1;
    private static int[] b2;
    private static int lenB1;
    private static int lenB2;


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int sum = s.nextInt();

        int[] a1 = readArray(s);
        int[] a2 = readArray(s);
        int[] a3 = readArray(s);

        b1 = new int[a2.length];
        lenB1 = b1.length;
        System.arraycopy(a2, 0, b1, 0, a2.length);
        Arrays.sort(b1);

        b2 = new int[a3.length];
        lenB2 = b2.length;
        System.arraycopy(a3, 0, b2, 0, a3.length);
        Arrays.sort(b2);

        for (int i = 0; i < a1.length; i++) {
            if (hasSum(sum - a1[i])) {
                int newSum = sum - a1[i];
                for (int j = 0; j < a2.length; j++) {
                    for (int k = 0; k < a3.length; k++) {
                        if (a2[j] + a3[k] == newSum) {
                            System.out.println(i + " " + j + " " + k);
                            System.exit(0);
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }

    private static int[] readArray(Scanner s) {
        int n = s.nextInt();
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            res[i] = s.nextInt();
        }

        return res;
    }

    private static boolean hasSum(int checkSum) {
        int p1 = 0;
        int p2 = lenB2 - 1;

        int sum;
        while (p1 < lenB1 && p2 >= 0) {
            sum = b1[p1] + b2[p2];
            if (sum == checkSum) {
                return true;
            } else if (sum < checkSum) {
                p1++;
            } else {
                p2--;
            }
        }

        return false;
    }
}
