package leetcode.hard;

/**
 * Created by duviteck. 01 Dec 2014.
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        MedianOfTwoSortedArrays instance = new MedianOfTwoSortedArrays();
        System.out.println(instance.findMedianSortedArrays(new int[]{1, 2}, new int[]{1, 2}));
    }

    public double findMedianSortedArrays(int A[], int B[]) {
        int totalLength = A.length + B.length;
        if (totalLength % 2 == 1) {
            return findKth(A, B, (totalLength + 1) / 2);
        } else {
            double part1 = findKth(A, B, totalLength / 2);
            double part2 = findKth(A, B, totalLength / 2 + 1);
            return (part1 + part2) / 2;
        }
    }

    private int findKth(int[] a, int[] b, int k) {
        return findKth(a, 0, a.length - 1, b, 0, b.length - 1, k);
    }

    private int findKth(int[] a, int as, int ae, int[] b, int bs, int be, int k) {
        // case of 1 left array
        if (as > ae) {
            return findKth(b, bs, k);
        } else if (bs > be) {
            return findKth(a, as, k);
        }

        // common case
        int am = (as + ae) / 2;
        int bm = (bs + be) / 2;
        if (a[am] < b[bm]) {
            return findKthOrderedArrays(a, as, ae, b, bs, be, k);
        } else {
            return findKthOrderedArrays(b, bs, be, a, as, ae, k);
        }
    }

    private int findKthOrderedArrays(int[] a, int as, int ae, int[] b, int bs, int be, int k) {
        // here surely a[am] <= b[bm]
        int am = (as + ae) / 2;
        int bm = (bs + be) / 2;
        int totalLen = (ae - as + 1) + (be - bs + 1);
        int leftLen = (am - as + 1);

        if (k > totalLen / 2) {
            return findKth(a, am + 1, ae, b, bs, be, k - leftLen);
        } else {
            if (bs == be) { // b.length == 1
                return findKth(a, as, ae, b, bs, bm - 1, k);
            } else {
                return findKth(a, as, ae, b, bs, bm, k);
            }
        }
    }

    private int findKth(int[] a, int as, int k) {
        return a[as + k - 1];
    }
}