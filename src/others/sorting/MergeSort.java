package others.sorting;

/**
 * Created by duviteck. 16 Nov 2014.
 */
public class MergeSort implements ISort {

    @Override
    public void sort(int[] ar) {
        if (ar == null || ar.length <= 1) {
            return;
        }

        int mid = ar.length / 2;

        int[] a1 = new int[mid];
        System.arraycopy(ar, 0, a1, 0, mid);
        sort(a1);

        int[] a2 = new int[ar.length - mid];
        System.arraycopy(ar, mid, a2, 0, ar.length - mid);
        sort(a2);

        int[] mergeRes = merge(a1, a2);
        System.arraycopy(mergeRes, 0, ar, 0, ar.length);
    }

    private int[] merge(int[] a1, int[] a2) {
        int[] res = new int[a1.length + a2.length];
        int resPos = 0;
        int pos1 = 0;
        int pos2 = 0;

        while (resPos < res.length) {
            if (pos1 == a1.length) {
                System.arraycopy(a2, pos2, res, resPos, a2.length - pos2);
                resPos = res.length;
            } else if (pos2 == a2.length) {
                System.arraycopy(a1, pos1, res, resPos, a1.length - pos1);
                resPos = res.length;
            } else if (a1[pos1] <= a2[pos2]) {
                res[resPos++] = a1[pos1++];
            } else {
                res[resPos++] = a2[pos2++];
            }
        }
        return res;
    }

    @Override
    public String getName() {
        return "Merge";
    }
}
