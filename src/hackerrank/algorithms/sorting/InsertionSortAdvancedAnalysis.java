package hackerrank.algorithms.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2014 year
 */
public class InsertionSortAdvancedAnalysis {

    private static final int[] nums = new int[100000];
    private static int n;
    private static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine().trim());

        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(reader.readLine().trim());

            StringTokenizer st = new StringTokenizer(reader.readLine().trim());
            for (int j = 0; j < n; j++) {
                nums[j] = Integer.parseInt(st.nextToken());
            }

            calcAns();
            System.out.println(ans);
        }
    }

    private static void calcAns() {
        ans = 0;
        mergeSort(nums, 0, n - 1);
    }

    private static int[] mergeSort(int[] arr, int start, int end) {
        if (start == end) {
            return new int[] {arr[start]};
        }

        int middle = (start + end) / 2;
        int[] part1 = mergeSort(arr, start, middle);
        int[] part2 = mergeSort(arr, middle + 1, end);
        return merge(part1, part2);
    }

    private static int[] merge(int[] p1, int[] p2) {
        int i1 = 0;
        int i2 = 0;
        int ires = 0;
        int l1 = p1.length;
        int l2 = p2.length;
        int lres = l1 + l2;
        int[] res = new int[lres];

        while (ires < lres) {
            if (i1 == l1) {
                System.arraycopy(p2, i2, res, ires, l2 - i2);
                break;
            } else if (i2 == l2) {
                System.arraycopy(p1, i1, res, ires, l1 - i1);
                break;
            } else if (p1[i1] <= p2[i2]) {  // TODO: remove = if needed
                res[ires++] = p1[i1++];
            } else {
                res[ires++] = p2[i2++];
                ans += l1 - i1;
            }
        }

        return res;
    }
}
