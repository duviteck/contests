package cscenter.year_2014_2015.semester_2.hometask_6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * http://acm.spbgu.ru/trains/150323_cs.pdf
 *
 * Created by duviteck on 04/04/15.
 */
public class Problem_6A_Base {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        final char[] line = s.nextLine().toCharArray();
        final int n = line.length;

        Integer[] sa = new Integer[n];
        for (int i = 0; i < n; i++) {
            sa[i] = i + 1;
        }

        Arrays.sort(sa, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int i1 = o1 - 1;
                int i2 = o2 - 1;

                int maxCheckLen = n - Math.max(i1, i2);
                for (int i = 0; i < maxCheckLen; i++) {
                    int c1 = line[i1];
                    int c2 = line[i2];
                    if (c1 < c2) {
                        return -1;
                    } else if (c2 < c1) {
                        return 1;
                    } else {
                        i1++;
                        i2++;
                    }
                }

                return (i1 == n) ? -1 : 1;
            }
        });

        printArray(sa);
    }

    private static void printArray(Integer[] sa) {
        StringBuilder sb = new StringBuilder(sa.length * 6);
        for (Integer i : sa) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}
