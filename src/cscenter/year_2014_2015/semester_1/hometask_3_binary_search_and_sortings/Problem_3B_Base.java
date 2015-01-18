package cscenter.year_2014_2015.semester_1.hometask_3_binary_search_and_sortings;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Quick search in array
 *
 * Created by duviteck. 08 Jan 2015.
 */

public class Problem_3B_Base {

	public static void main(String[] args) throws IOException {
		MyReader reader = new MyReader(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		int n = reader.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = reader.nextInt();
		}
		Arrays.sort(nums);

		int k = reader.nextInt();
		for (int i = 0; i < k; i++) {
			int l = reader.nextInt();
			int r = reader.nextInt();

			int lIndex = firstMoreOrEqualIndex(nums, l);
			int rIndex = lastLessOrEqualIndex(nums, r);
			int res = Math.max(0, rIndex - lIndex + 1);
			writer.write(String.valueOf(res) + " ");
		}
		writer.println();
		writer.flush();
	}

	private static int firstMoreOrEqualIndex(int[] nums, int target) {
		int l = 0;
		int r = nums.length - 1;
		while (l <= r) {
			int m = (l + r) / 2;
			if (nums[m] >= target) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return r + 1;
	}

	private static int lastLessOrEqualIndex(int[] nums, int target) {
		int l = 0;
		int r = nums.length - 1;
		while (l <= r) {
			int m = (l + r) / 2;
			if (nums[m] <= target) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return l - 1;
	}


	private static class MyReader {
		BufferedInputStream in;

		final int bufSize = 1 << 16;
		final byte b[] = new byte[bufSize];

		MyReader(InputStream in) {
			this.in = new BufferedInputStream(in, bufSize);
		}

		int nextInt() throws IOException {
			int c;
			while ((c = nextChar()) <= 32);

			int x = 0, sign = 1;
			if (c == '-') {
				sign = -1;
				c = nextChar();
			}
			while (c >= '0') {
				x = x * 10 + (c - '0');
				c = nextChar();
			}
			return x * sign;
		}

		StringBuilder _buf = new StringBuilder();

		String nextWord() throws IOException {
			int c;
			_buf.setLength(0);
			while ((c = nextChar()) <= 32)
				;
			while (c > 32) {
				_buf.append((char) c);
				c = nextChar();
			}
			return _buf.toString();
		}

		int bn = bufSize, k = bufSize;

		int nextChar() throws IOException {
			if (bn == k) {
				k = in.read(b, 0, bufSize);
				bn = 0;
			}
			return bn >= k ? -1 : b[bn++];
		}

		int nextNotSpace() throws IOException {
			int ch;
			while ((ch = nextChar()) <= 32 && ch != -1)
				;
			return ch;
		}
	}
}