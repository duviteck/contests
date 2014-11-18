package others.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by duviteck. 15 Nov 2014.
 */
public class SortTest {
    private static final int TEST_ARRAY_LENGTH = 1000;
    private static final int TEST_ATTEMPTS = 1000;
    private static final Random random = new Random(System.currentTimeMillis());
    private static final List<int[]> specialTestCases = new ArrayList<>();
    private static final List<ISort> sortAlgorithms = new ArrayList<>();

    static {
//        sortAlgorithms.add(new InsertionSort());
//        sortAlgorithms.add(new SelectionSort());
        sortAlgorithms.add(new MergeSort());
        sortAlgorithms.add(new QuickSort());
        sortAlgorithms.add(new HeapSort());
        sortAlgorithms.add(new BubbleSort());
        sortAlgorithms.add(new CombSort());
        sortAlgorithms.add(new RadixSort());

        specialTestCases.add(null);
        specialTestCases.add(new int[0]);
        specialTestCases.add(new int[] {1});
    }

    public static void main(String[] args) {
        for (int i = 0; i < TEST_ATTEMPTS; i++) {
            int[] ar = generateTestArray(TEST_ARRAY_LENGTH);
            for (ISort sorting : sortAlgorithms) {
                checkSorting(sorting, ar);
            }
        }

        for (int[] specialCase : specialTestCases) {
            for (ISort sorting : sortAlgorithms) {
                checkSorting(sorting, specialCase);
            }
        }

        System.out.println("All ok");
    }

    private static void printArray(int[] ar) {
        for (int a : ar) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    private static void checkSorting(ISort sorting, int[] ar) {
        if (ar == null || ar.length == 0) {
            sorting.sort(ar);   // will crash in case of error
            return;
        }

        int[] copyArray = Arrays.copyOf(ar, ar.length);
        sorting.sort(copyArray);

        int last = copyArray[0];
        for (int i = 1; i < copyArray.length; i++) {
            if (copyArray[i] < last) {
                printArray(ar);     // print origin array
                throw new IllegalStateException("Incorrect sorting for sorting " + sorting.getName());
            }
            last = copyArray[i];
        }
    }

    private static int[] generateTestArray(int size) {
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = random.nextInt();
        }
        return ans;
    }
}
