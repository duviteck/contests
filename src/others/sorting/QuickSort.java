package others.sorting;

/**
 * Created by duviteck. 16 Nov 2014.
 */
public class QuickSort implements ISort {

    @Override
    public void sort(int[] ar) {
        if (ar == null || ar.length <= 1) {
            return;
        }

        sort(ar, 0, ar.length);
    }

    private void sort(int[] ar, int start, int end) {
        if (end - start <= 1) {
            return;
        }

        int mid = start + (end - start) / 2;
        int pivot = ar[mid];
        swap(ar, mid, end - 1); // now pivot is stored in last cell

        // keep array as [...less_than_pivot_items..., ...more_than_pivot_items..., pivot]

        int firstMoreIndex = start;     // index of first item which is more than pivot
        for (int i = start; i < end; i++) {
            if (ar[i] <= pivot) {
                swap(ar, i, firstMoreIndex);
                firstMoreIndex++;
            }
        }

        // now pivot is at pos (firstMoreIndex - 1)
        sort(ar, start, firstMoreIndex - 1);
        sort(ar, firstMoreIndex, end);
    }

    private void swap(int[] ar, int pos1, int pos2) {
        int temp = ar[pos1];
        ar[pos1] = ar[pos2];
        ar[pos2] = temp;
    }

    @Override
    public String getName() {
        return "Quick";
    }
}
