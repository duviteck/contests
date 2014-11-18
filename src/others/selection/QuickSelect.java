package others.selection;

/**
 * Created by duviteck. 18 Nov 2014.
 */
public class QuickSelect implements ISelect {
    @Override
    public int select(int[] ar, int k) {
        if (ar == null || k <= 0 || k > ar.length) {
            throw new IllegalArgumentException("Incorrect input data");
        }

        return select(ar, 0, ar.length, k);
    }

    private int select(int[] ar, int start, int end, int k) {
        if (end - start == 1) {
            return ar[start];
        }

        int pivot = ar[end - 1];
        int firstMore = start;
        for (int i = start; i < end; i++) {
            if (ar[i] < pivot) {
                swap(ar, i, firstMore);
                firstMore++;
            }
        }

        swap(ar, end - 1, firstMore);	// replace pivot
        firstMore++;

        if (k  == firstMore) {	// (k - 1) == (firstMore - 1)
            return pivot;
        } else if (k < firstMore) {
            return select(ar, start, firstMore - 1, k);
        } else {
            return select(ar, firstMore, end, k);
        }
    }

    private void swap(int[] ar, int pos1, int pos2) {
        int temp = ar[pos1];
        ar[pos1] = ar[pos2];
        ar[pos2] = temp;
    }

    @Override
    public String getName() {
        return "QuickSelect";
    }
}
