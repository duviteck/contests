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
        swap(ar, mid, end - 1);

        int moreStart = start;
        for (int i = start; i < end; i++) {
            if (ar[i] <= pivot) {
                int temp = ar[i];
                ar[i] = ar[moreStart];
                ar[moreStart] = temp;
                moreStart++;
            }
        }

        // now pivot is at pos (moreStart - 1)
        sort(ar, start, moreStart - 1);
        sort(ar, moreStart, end);
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
