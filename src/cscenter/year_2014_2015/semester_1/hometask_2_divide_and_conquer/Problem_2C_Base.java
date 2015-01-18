package cscenter.year_2014_2015.semester_1.hometask_2_divide_and_conquer;

import java.util.Scanner;

/**
 * Number of inversions
 *
 * Created by duviteck. 28 Dec 2014.
 */
public class Problem_2C_Base {

    private static long inversions;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        int a = s.nextInt();
        int b = s.nextInt();

        int[] ar = generateArray(n, m, a, b);

        calcInversions(ar);

        System.out.println(inversions);
    }

    private static void calcInversions(int[] ar) {
        inversions = 0;
        mergeSort(ar, 0, ar.length - 1);
    }

    private static int[] mergeSort(int[] arr, int start, int end) {
        if (start == end) {
            return new int[] {arr[start]};
        }

        int middle = (start + end) / 2;
        int[] part1 = mergeSort(arr, start, middle);
        int[] part2 = mergeSort(arr, middle + 1, end);
        return merge(part1, part2);
    }

    private static int[] merge(int[] p1, int[] p2) {
        int i1 = 0;
        int i2 = 0;
        int iRes = 0;
        int l1 = p1.length;
        int l2 = p2.length;
        int lRes = l1 + l2;
        int[] res = new int[lRes];

        while (iRes < lRes) {
            if (i1 == l1) {
                System.arraycopy(p2, i2, res, iRes, l2 - i2);
                break;
            } else if (i2 == l2) {
                System.arraycopy(p1, i1, res, iRes, l1 - i1);
                break;
            } else if (p1[i1] <= p2[i2]) {
                res[iRes++] = p1[i1++];
            } else {
                res[iRes++] = p2[i2++];
                inversions += l1 - i1;
            }
        }

        return res;
    }

    private static int[] generateArray(int n, int m, int a, int b) {
        int[] res = new int[n];

        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur = cur * a + b;
            res[i] = (cur >>> 8) % m;
        }

        return res;
    }
}
