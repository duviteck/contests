package hackerrank.algorithms.sorting;

import java.util.Scanner;

/**
 * Created by duviteck. 12 Jun 2014.
 */
public class RunningTimeOfQuickSort {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        int[] originData = new int[n];
        for (int i = 0; i < n; i++) {
            originData[i] = s.nextInt();
        }
        s.close();

        int insertionSortSwaps = insertionSortSwaps(originData);
        int quickSortSwaps = quickSortSwaps(originData);

        System.out.println(insertionSortSwaps - quickSortSwaps);
    }

    private static int insertionSortSwaps(int[] ar) {
        int[] arCopy = new int[ar.length];
        System.arraycopy(ar, 0, arCopy, 0, ar.length);

        int swaps = insertionSort(arCopy);
//        printArray(arCopy);
        return swaps;
    }

    private static int insertionSort(int[] ar) {
        int swaps = 0;
        int n = ar.length;

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int curNum = ar[i];
            int curIndex = i - 1;

            while (curIndex >= 0 && curNum < res[curIndex]) {
                res[curIndex + 1] = res[curIndex];
                curIndex--;
                swaps++;
            }
            res[curIndex + 1] = curNum;
        }

        System.arraycopy(res, 0, ar, 0, n);

        return swaps;
    }

    private static int quickSortSwaps(int[] ar) {
        int[] arCopy = new int[ar.length];
        System.arraycopy(ar, 0, arCopy, 0, ar.length);

        int swaps = quickSort(arCopy, 0, ar.length - 1);
//        printArray(arCopy);
        return swaps;
    }

    private static int quickSort(int[] ar, int start, int end) {
        if (end - start <= 0) {
            return 0;
        }

        int swaps = 0;
        int pivot = ar[end];
        int firstMore = start;
        for (int i = start; i < end; i++) {
            if (ar[i] < pivot) {
                int temp = ar[i];
                ar[i] = ar[firstMore];
                ar[firstMore] = temp;

                firstMore++;
                swaps++;
            }
        }

        ar[end] = ar[firstMore];
        ar[firstMore] = pivot;
        firstMore++;
        swaps++;

        swaps += quickSort(ar, start, firstMore - 2);
        swaps += quickSort(ar, firstMore, end);
        return swaps;
    }

    private static void printArray(int[] ar) {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for(int a : ar) {
            sb.append(a);
            sb.append(' ');
        }
        sb.setCharAt(sb.length() - 1, '}');
        System.out.println(sb.toString());
    }
}