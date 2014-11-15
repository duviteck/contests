package hackerrank.algorithms.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by duviteck. 22 Jun 2014.
 */
public class AngryChildren {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(reader.readLine());
        }

        Arrays.sort(a);

        int min = Integer.MAX_VALUE;
        int count = n - k + 1;
        for (int i = 0; i < count; i++) {
            int newMin = a[i + k - 1] - a[i];
            if (newMin < min) {
                min = newMin;
            }
        }

        System.out.println(min);
    }
}
