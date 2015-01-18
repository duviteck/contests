package cscenter.year_2014_2015.semester_1.hometask_4_data_structures;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Bored professor
 *
 * Created by duviteck. 13 Jan 2015.
 */
public class Problem_4A_Base {

    private static final long BASE = 1L << 30;

    private static Deque<Long> deque;

	public static void main(String[] args) throws IOException {
    	MyReader reader = new MyReader(System.in);
    	int n = reader.nextInt();
    	int k = reader.nextInt();

        deque = new LinkedList<>();
    	for (int i = 0; i < n; i++) {
    		deque.addLast((long)reader.nextInt());
    	}

        for (int i = 0; i < k; i++) {
            makeStep();
        }

        printResult();
	}

    private static void makeStep() {
        long first = deque.peekFirst();
        long last = deque.peekLast();

        if (first < last) {
            deque.pollFirst();
            long newValue = (first + last) % BASE;
            if (newValue < 0) {
                newValue += BASE;
            }
            deque.addLast(newValue);
        } else {
            deque.pollLast();
            long newValue = (last - first) % BASE;
            if (newValue < 0) {
                newValue += BASE;
            }
            deque.addFirst(newValue);
        }
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder(11 * deque.size());
        for (Long i : deque) {
            sb.append(i);
            sb.append(" ");
        }
        sb.setCharAt(sb.length() - 1, '\n');
        System.out.println(sb.toString());
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
