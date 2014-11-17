package others.sorting;

/**
 * Created by duviteck. 17 Nov 2014.
 */
public class CombSort implements ISort {

    @Override
    public void sort(int[] ar) {
        if (ar == null || ar.length <= 1) {
            return;
        }

        final double shrinkFactor = 1.3f;
        int gap = (int)(ar.length / shrinkFactor);
        boolean hasChanges = false;

        while (gap > 0 || hasChanges) {
            hasChanges = false;
            if (gap == 0) {
                gap = 1;
            }

            for (int i = 0; i < ar.length - gap; i++) {
                if (ar[i] > ar[i + gap]) {
                    swap(ar, i, i + gap);
                    hasChanges = true;
                }
            }
            gap = (int)(gap / shrinkFactor);
        }
    }

    private void swap(int[] ar, int pos1, int pos2) {
        int temp = ar[pos1];
        ar[pos1] = ar[pos2];
        ar[pos2] = temp;
    }

    @Override
    public String getName() {
        return "Comb";
    }
}
