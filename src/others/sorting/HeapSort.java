package others.sorting;

/**
 * Created by duviteck. 16 Nov 2014.
 */
public class HeapSort implements ISort {

    @Override
    public void sort(int[] ar) {
        if (ar == null || ar.length <= 1) {
            return;
        }

        buildHeap(ar);
        for (int len = ar.length - 1; len > 0; len--) {
            swap(ar, 0, len);
            heapify(ar, len, 0);
        }
    }

    private void buildHeap(int[] ar) {
        for (int i = ar.length / 2; i >= 0; i--) {
            heapify(ar, ar.length, i);
        }
    }

    private void heapify(int[] ar, int len, int cur) {
        int left = left(cur);
        int right = right(cur);
        int largest = cur;
        if (left < len && ar[left] > ar[largest]) {
            largest = left;
        }
        if (right < len && ar[right] > ar[largest]) {
            largest = right;
        }
        if (largest != cur) {
            swap(ar, cur, largest);
            heapify(ar, len, largest);
        }
    }

    private void swap(int[] ar, int pos1, int pos2) {
        int temp = ar[pos1];
        ar[pos1] = ar[pos2];
        ar[pos2] = temp;
    }

    private int left(int cur) {
        return 2 * cur + 1;
    }

    private int right(int cur) {
        return 2 * (cur + 1);
    }

    @Override
    public String getName() {
        return "Heap";
    }
}
