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
    private static final int TEST_ATTEMPTS = 100;
    private static final Random random = new Random(System.currentTimeMillis());

    private static final List<ISort> sortings = new ArrayList<>();
    static {
        sortings.add(new InsertionSort());
        sortings.add(new SelectionSort());
    }

    public static void main(String[] args) {
        for (int i = 0; i < TEST_ATTEMPTS; i++) {
            int[] ar = generateTestArray(TEST_ARRAY_LENGTH);
            for (ISort sorting : sortings) {
                checkSorting(sorting, ar);
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
