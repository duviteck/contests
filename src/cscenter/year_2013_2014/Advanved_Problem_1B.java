package cscenter.year_2013_2014;

import java.util.Scanner;

public class Advanved_Problem_1B {

    private static final int[] buffer = new int[40];
    private static final StringBuilder sb = new StringBuilder(120);


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.close();

        showAllPartitions(n, n, 0);
    }

    private static void showAllPartitions(int rest, int prevVal, int pos) {
        if (rest == 0) {
            printBuffer(pos);
            return;
        }

        int count = Math.min(rest, prevVal);
        for (int i = 1; i <= count; i++) {
            buffer[pos] = i;
            showAllPartitions(rest - i, i, pos + 1);
        }
    }

    private static void printBuffer(int len) {
        sb.setLength(0);
        for (int i = 0; i < len; i++) {
            sb.append(buffer[i]);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}
