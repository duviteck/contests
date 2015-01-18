package leetcode.hard;

/**
 * Created by duviteck. 01 Dec 2014.
 */
public class MedianOfTwoSortedArrays {

    // not finished yet

//    public double findMedianSortedArrays(int[] a, int[] b) {
//        // TODO: error check
//
//        int size = a.length + b.length;
//        if (size % 2 == 1) {
//            return kth(a, 0, a.length, b, 0, b.length, size / 2);
//        } else {
//            int med1 = kth(a, 0, a.length, b, 0, b.length, size / 2 - 1);
//            int med2 = kth(a, 0, a.length, b, 0, b.length, size / 2);
//            return (med1 + med2) / 2.0;
//        }
//    }
//
//    public int kth(int[] a, int aStart, int aEnd, int[] b, int bStart, int bEnd, int k) {
//        if (aStart == aEnd) {
//            return kth(b, bStart, bEnd, k);
//        } else if (bStart == bEnd) {
//            return kth(a, aStart, aEnd, k);
//        }
//
//        int aMedIndex = (aStart + aEnd) / 2;
//        int aMed = a[aMedIndex];
//        int bMedIndex = (bStart + bEnd) / 2;
//        int bMed = b[bMedIndex];
//
//
//    }
}