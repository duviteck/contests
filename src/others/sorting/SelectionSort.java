package others.sorting;

/**
 * Created by duviteck. 16 Nov 2014.
 */
public class SelectionSort implements ISort {

    @Override
    public void sort(int[] ar) {
        if (ar == null || ar.length <= 1) {
            return;
        }

        for (int i = 0; i < ar.length - 1; i++) {
            int min = ar[i];
            int minPos = i;
            for (int j = i + 1; j < ar.length; j++) {
                if (ar[j] < min) {
                    min = ar[j];
                    minPos = j;
                }
            }
            ar[minPos] = ar[i];
            ar[i] = min;
        }
    }

    @Override
    public String getName() {
        return "Selection";
    }
}
