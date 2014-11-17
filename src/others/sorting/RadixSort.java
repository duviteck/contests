package others.sorting;

/**
 * Created by duviteck. 18 Nov 2014.
 */
public class RadixSort implements ISort {

    @Override
    public void sort(int[] ar) {
        if (ar == null || ar.length <=1) {
            return;
        }

        sortFirst(ar, 0, ar.length, (1 << 31));
    }

    private void sortFirst(int[] ar, int start, int end, int mask) {
        if (end - start <= 1) {
            return;
        }

        int firstMore = start;
        for (int i = start; i < end; i++) {
            int digit = ar[i] & mask;
            if (digit != 0) {
                swap(ar, firstMore, i);
                firstMore++;
            }
        }

        mask = mask >>> 1;
        sort(ar, start, firstMore, mask);
        sort(ar, firstMore, end, mask);
    }

    private void sort(int[] ar, int start, int end, int mask) {
        if (end - start <= 1 || mask == 0) {
            return;
        }

        int firstMore = start;
        for (int i = start; i < end; i++) {
            int digit = ar[i] & mask;
            if (digit == 0) {
                swap(ar, firstMore, i);
                firstMore++;
            }
        }

        mask = mask >>> 1;
        sort(ar, start, firstMore, mask);
        sort(ar, firstMore, end, mask);
    }

    private void swap(int[] ar, int pos1, int pos2) {
        int temp = ar[pos1];
        ar[pos1] = ar[pos2];
        ar[pos2] = temp;
    }

    @Override
    public String getName() {
        return "Radix";
    }
}
