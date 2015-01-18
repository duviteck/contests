package cscenter.year_2014_2015.semester_1.hometask_3_binary_search_and_sortings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Search
 *
 * In fact, just bundle of binarySearch queries
 *
 * Created by duviteck. 08 Jan 2015.
 */

public class Problem_3A_Base {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] nums = new int[n];
		st = new StringTokenizer(reader.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		PrintWriter writer = new PrintWriter(System.out);
		st = new StringTokenizer(reader.readLine());
		for (int i = 0; i < k; i++) {
			int query = Integer.parseInt(st.nextToken());
			int index = Arrays.binarySearch(nums, query);
			if (index >= 0) {
				writer.println("YES");
			} else {
				writer.println("NO");
			}
		}
		writer.flush();
	}
}