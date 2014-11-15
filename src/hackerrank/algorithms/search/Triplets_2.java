package hackerrank.algorithms.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2014 year
 */
public class Triplets_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine().trim());

        StringTokenizer st = new StringTokenizer(reader.readLine().trim());
        long[] a = new long[N];
        for (int i = 0; i < N; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }

        int total_count = 0;
        // the number of larger elements after the current one
        HashMap<Long, Integer> nextMap = new HashMap<Long, Integer>();
        // previous count for the current number (avoid double counting)
        HashMap<Long, Integer> countMap = new HashMap<Long, Integer>();
        // for every element, mark if an element has been visited or not
        HashSet<Long> markSet = new HashSet<Long>(N);

        nextMap.put(a[N - 1], 0);
        countMap.put(a[N - 1], 0);

        for (int i = N - 2; i >= 0; i--) {
            int next = 0;
            int count = 0;

            markSet.clear();

            for (int j = i + 1; j < N; j++) {
                if (a[j] <= a[i] || markSet.contains(a[j])) {
                    continue;
                }
                markSet.add(a[j]);
                next++;
                count += nextMap.get(a[j]);
            }

            nextMap.put(a[i], next);
            total_count += count;

            if (countMap.get(a[i]) != null) {
                total_count -= countMap.get(a[i]);
            }
            countMap.put(a[i], count);
        }

        System.out.println(total_count);
    }

}
