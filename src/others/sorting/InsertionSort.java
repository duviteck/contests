package others.sorting;

/**
 * Created by duviteck. 15 Nov 2014.
 */
public class InsertionSort implements ISort {

    @Override
    public void sort(int[] ar) {
        if (ar == null || ar.length <= 1) {
            return;
        }

        for (int i = 1; i < ar.length; i++) {
            int cur = ar[i];
            int j = i - 1;
            while (j >= 0 && ar[j] > cur) {
                ar[j+1] = ar[j];
                j--;
            }
            ar[j+1] = cur;
        }
    }

    @Override
    public String getName() {
        return "Insertion";
    }
}
