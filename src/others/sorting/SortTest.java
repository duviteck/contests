package others.sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duviteck. 15 Nov 2014.
 */
public class SortTest {
    private static final int[] AR_1 = new int[] {5, 4, 3, 2, 1};
    private static final int[] AR_2 = new int[] {5, 4, 3, 5, -1, 0, 0, 1, 2, 1};
    private static final List<int[]> testArrays = new ArrayList<>();

    static {
        testArrays.add(AR_1);
        testArrays.add(AR_2);
    }

    public static void main(String[] args) {
        ISort sorting = new InsertionSort();

        for (int[] ar : testArrays) {
            int[] testAr = new int[ar.length];
            System.arraycopy(ar, 0, testAr, 0, ar.length);
            sorting.sort(testAr);
            printArray(testAr);
            checkArray(testAr);
        }

        System.out.println("All ok");
    }

    private static void printArray(int[] ar) {
        for (int a : ar) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    private static void checkArray(int[] ar) {
        int last = ar[0];
        for (int i = 1; i < ar.length; i++) {
            if (ar[i] < last) {
                throw new IllegalStateException("Incorrect sorting");
            }
            last = ar[i];
        }
    }
}
