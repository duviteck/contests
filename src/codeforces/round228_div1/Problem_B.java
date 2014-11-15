package codeforces.round228_div1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2014 year
 */
public class Problem_B {

    private static char[][] table;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int k = s.nextInt();

        ArrayList<Integer> powers = new ArrayList<Integer>(32);
        int curPow = 0;
        while (k > 0) {
            if ((k & 1) == 1) {
                powers.add(curPow);
            }
            curPow++;
            k = k >> 1;
        }

        initFillGraph();

        for (int pow : powers) {
            emulateWays(pow);
        }

        System.out.println(92);
        printTable(false);
    }

    private static void emulateWays(int pow) {
        int start = 31 - pow;
        int end = 90 - 2 * pow;
        table[start][end] = table[end][start] = 'Y';
    }

    private static void initFillGraph() {
        table = new char[92][92];
        for (char[] a : table) {
            Arrays.fill(a, 'N');
        }

        // Add chain to 1
        table[0][2] = table[2][0] = 'Y';

        int prev = 2;
        int next = 3;
        while (prev < 31) {
            table[prev][next] = table[next][prev] = 'Y';
            prev++;
            next++;
        }

//        printTable(true);
//        System.out.println();
//        System.out.println();

        // Add binary chain to 2
        table[1][90] = table[90][1] = 'Y';
        table[1][91] = table[91][1] = 'Y';

        prev = 90;
        next = 88;
        while (prev > 32) {
            table[prev][next] = table[next][prev] = 'Y';
            table[prev][next + 1] = table[next + 1][prev] = 'Y';
            table[prev + 1][next] = table[next][prev + 1] = 'Y';
            table[prev + 1][next + 1] = table[next + 1][prev + 1] = 'Y';
            prev -= 2;
            next -= 2;
        }

//        printTable(true);
//        System.out.println();
//        System.out.println();
    }

    private static void printTable(boolean withNumbers) {
        StringBuilder sb = new StringBuilder(92 * (92 + 2));
        int cnt = 1;
        for (char[] a : table) {
            if (withNumbers) sb.append(String.format("%2d ", cnt));
            sb.append(a);
            sb.append("\n");
            cnt++;
        }

        System.out.println(sb.toString());
    }
}
