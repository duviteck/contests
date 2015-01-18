package cscenter.year_2014_2015.semester_1.hometask_3_binary_search_and_sortings;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Strings
 *
 * Created by duviteck. 08 Jan 2015.
 */

public class Problem_3C_Base {

	public static void main(String[] args) throws IOException {
		MyReader reader = new MyReader(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		int n = reader.nextInt();
		int targetCount = reader.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = reader.nextInt();
		}

		int ans = calcAns(nums, targetCount);
		
		writer.println(String.valueOf(ans));
		writer.flush();
	}

	private static int calcAns(int[] nums, int targetCount) {
		// define min and max possible values for answer
		int minValue = 1;
		int maxValue = Integer.MIN_VALUE;
		for (int num : nums) {
			if (num > maxValue) {
				maxValue = num;	// max value is equal to length of biggest element
			}
		}

		int bestAns = 0;
		while (minValue <= maxValue) {
			int curValue = (minValue + maxValue) / 2;
			boolean isPossible = checkAnswerPossibility(nums, curValue, targetCount);
			if (isPossible) {
				minValue = curValue + 1;
				bestAns = curValue;
			} else {
				maxValue = curValue - 1;
			}
		}
		return bestAns;
	}

	private static boolean checkAnswerPossibility(int[] nums, int answer, int targetCount) {
		for (int num : nums) {
			targetCount -= num / answer;
		}
		return (targetCount <= 0);
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