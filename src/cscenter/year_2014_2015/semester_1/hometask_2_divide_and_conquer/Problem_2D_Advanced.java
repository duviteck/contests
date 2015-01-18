package cscenter.year_2014_2015.semester_1.hometask_2_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Mega-inversions
 * i < j < k, p[i] > p[j] > p[k]
 *
 * Created by duviteck. 28 Dec 2014.
 */
public class Problem_2D_Advanced {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        // read input
        Tuple[] elems = new Tuple[n];
        StringTokenizer st = null;
        for (int i = 0; i < n; i++) {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(reader.readLine());
            }
            elems[i] = new Tuple(Integer.parseInt(st.nextToken()));
        }

        long ans = calcMegaInversions(elems);
        System.out.println(ans);
    }

    private static long calcMegaInversions(Tuple[] elems) {
        calcMoreAndBefore(elems);
        calcLessAndAfter(elems);

        long ans = 0;
        for (Tuple elem : elems) {
            ans += elem.moreAndBefore * elem.lessAndAfter;
        }
        return ans;
    }

    private static void calcMoreAndBefore(Tuple[] elems) {
        mergeSortMoreAndBefore(elems, 0, elems.length - 1);
    }

    private static void calcLessAndAfter(Tuple[] elems) {
        mergeSortLessAndAfter(elems, 0, elems.length - 1);
    }

    private static Tuple[] mergeSortMoreAndBefore(Tuple[] arr, int start, int end) {
        if (start == end) {
            return new Tuple[] {arr[start]};
        }

        int middle = (start + end) / 2;
        Tuple[] part1 = mergeSortMoreAndBefore(arr, start, middle);
        Tuple[] part2 = mergeSortMoreAndBefore(arr, middle + 1, end);
        return mergeMoreAndBefore(part1, part2);
    }

    private static Tuple[] mergeMoreAndBefore(Tuple[] p1, Tuple[] p2) {
        int i1 = 0;
        int i2 = 0;
        int iRes = 0;
        int l1 = p1.length;
        int l2 = p2.length;
        int lRes = l1 + l2;
        Tuple[] res = new Tuple[lRes];

        while (iRes < lRes) {
            if (i1 == l1) {
                System.arraycopy(p2, i2, res, iRes, l2 - i2);
                break;
            } else if (i2 == l2) {
                System.arraycopy(p1, i1, res, iRes, l1 - i1);
                break;
            } else if (p1[i1].val < p2[i2].val) {
                res[iRes++] = p1[i1++];
            } else {
                res[iRes++] = p2[i2];
                p2[i2++].moreAndBefore += l1 - i1;
            }
        }

        return res;
    }

    private static Tuple[] mergeSortLessAndAfter(Tuple[] arr, int start, int end) {
        if (start == end) {
            return new Tuple[] {arr[start]};
        }

        int middle = (start + end) / 2;
        Tuple[] part1 = mergeSortLessAndAfter(arr, start, middle);
        Tuple[] part2 = mergeSortLessAndAfter(arr, middle + 1, end);
        return mergeLessAndAfter(part1, part2);
    }

    private static Tuple[] mergeLessAndAfter(Tuple[] p1, Tuple[] p2) {
        int i1 = p1.length - 1;
        int i2 = p2.length - 1;
        int iRes = p1.length + p2.length - 1;
        Tuple[] res = new Tuple[p1.length + p2.length];

        while (iRes >= 0) {
            if (i1 == -1) {
                System.arraycopy(p2, 0, res, 0, i2 + 1);
                break;
            } else if (i2 == -1) {
                System.arraycopy(p1, 0, res, 0, i1 + 1);
                break;
            } else if (p2[i2].val > p1[i1].val) {
                res[iRes--] = p2[i2--];
            } else {
                res[iRes--] = p1[i1];
                p1[i1--].lessAndAfter += i2 + 1;
            }
        }

        return res;
    }


    private static class Tuple  {
        int val;
        long moreAndBefore;
        long lessAndAfter;

        public Tuple(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val + " " + moreAndBefore + " " + lessAndAfter;
        }
    }
}
