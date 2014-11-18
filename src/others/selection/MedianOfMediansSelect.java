package others.selection;

/**
 * Created by duviteck. 19 Nov 2014.
 */
public class MedianOfMediansSelect implements ISelect {
    private static final int BUCKET_SIZE = 5;
    private static final int INSERTION_SORT_THRESHOLD = 16;

    @Override
    public int select(int[] ar, int k) throws IllegalArgumentException {
        if (ar == null || k < 1 || k > ar.length) {
            throw new IllegalArgumentException("Incorrect input data");
        }

        final int bucketSize = BUCKET_SIZE;
        return selectImpl(ar, 0, ar.length, k - 1, bucketSize);
    }

    private int selectImpl(int[] ar, int start, int end, int k, int bucketSize) {
        if (end - start == 1) {
            return ar[start];
        } else if (end - start <= INSERTION_SORT_THRESHOLD) {
            insertionSort(ar, start, end);
            return ar[k];
        }

        int pivot = determinePivot(ar, start, end, bucketSize);
        int firstMore = start;
        for (int i = start; i < end; i++) {
            if (ar[i] < pivot) {
                swap(ar, i, firstMore);
                firstMore++;
            }
        }

        if (k < firstMore) {
            return selectImpl(ar, start, firstMore, k, bucketSize);
        } else {
            return selectImpl(ar, firstMore, end, k, bucketSize);
        }
    }

    private void swap(int[] ar, int pos1, int pos2) {
        int temp = ar[pos1];
        ar[pos1] = ar[pos2];
        ar[pos2] = temp;
    }

    private void insertionSort(int[] ar, int start, int end) {
        for (int i = start + 1; i < end; i++) {
            int cur = ar[i];
            int j = i - 1;
            while (j >= start && ar[j] > cur) {
                swap(ar, j, j + 1);
                j--;
            }
            ar[j + 1] = cur;
        }
    }

    private int getMedianByInsertionSort(int[] ar, int start, int end) {
        insertionSort(ar, start, end);
        int midIndex = start + (end - start) / 2;
        return ar[midIndex];
    }

    private int determinePivot(int[] ar, int start, int end, int bucketSize) {
        if (end - start <= INSERTION_SORT_THRESHOLD) {
            return getMedianByInsertionSort(ar, start, end);
        }

        int bucketCount = (end - start + bucketSize - 1) / bucketSize;
        int[] medians = new int[bucketCount];
        for (int i = 0, bucketStart = start; i < bucketCount; i++, bucketStart += bucketSize) {
            int bucketEnd = (i == bucketCount - 1) ? end : bucketStart + bucketSize;
            medians[i] = getMedianByInsertionSort(ar, bucketStart, bucketEnd);
        }

        return determinePivot(medians, 0, bucketCount, bucketSize);
    }

    @Override
    public String getName() {
        return "MedianOfMediansSelect";
    }
}
