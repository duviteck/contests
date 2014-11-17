package others.sorting;

/**
 * Created by duviteck. 16 Nov 2014.
 */
public class BubbleSort implements ISort {

    @Override
    public void sort(int[] ar) {
        if (ar == null || ar.length <= 1) {
            return;
        }

        for (int i = ar.length - 1; i >= 0; i--) {
            boolean hasChanges = false;
            for (int j = 0; j < i; j++) {
                if (ar[j] > ar[j+1]) {
                    swap(ar, j, j + 1);
                    hasChanges = true;
                }
            }
            if (!hasChanges) {
                return;
            }
        }
    }

    private void swap(int[] ar, int pos1, int pos2) {
        int temp = ar[pos1];
        ar[pos1] = ar[pos2];
        ar[pos2] = temp;
    }

    @Override
    public String getName() {
        return "Bubble";
    }
}
