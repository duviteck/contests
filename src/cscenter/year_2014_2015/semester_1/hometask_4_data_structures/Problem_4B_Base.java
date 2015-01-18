package cscenter.year_2014_2015.semester_1.hometask_4_data_structures;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Childrens are acquainted
 *
 * Created by duviteck. 13 Jan 2015.
 */
public class Problem_4B_Base {

	private static Map<Integer, Child> map;

    public static void main(String[] args) throws IOException {
    	MyReader reader = new MyReader(System.in);
    	PrintWriter writer = new PrintWriter(System.out);
    	int n = reader.nextInt();

    	String[] names = new String[n];
    	for (int i = 0; i < n; i++) {
    		names[i] = reader.nextWord();
    	}

    	initChildrenMap(names);

    	for (int i = 0; i < n - 3; i++) {
    		int query = reader.nextInt();
    		String[] ans = getNeighbours(query);
    		writer.format("%s %s\n", ans[0], ans[1]);
    	}
    	writer.flush();
    }

    private static void initChildrenMap(String[] names) {
        map = new HashMap<>(names.length);

    	Child prevChild = new Child(names[0], null, null);	// first child
    	map.put(1, prevChild);
    	for (int i = 1; i < names.length; i++) {
    		Child newChild = new Child(names[i], prevChild, null);
    		prevChild.right = newChild;
    		map.put(i + 1, newChild);

    		prevChild = newChild;
    	}

    	// prevChild is a last child now
    	Child firstChild = map.get(1);
    	firstChild.left = prevChild;
    	prevChild.right = firstChild;
    }

    private static String[] getNeighbours(int query) {
    	String[] ans = new String[2];
    	Child target = map.get(query);
    	ans[0] = target.left.name;
    	ans[1] = target.right.name;
    	
    	target.left.right = target.right;
    	target.right.left = target.left;
    	
    	target.right = null;
    	target.left = null;

    	return ans;
    }


    private static class Child {
    	String name;
    	Child left;
    	Child right;

    	public Child(String name, Child left, Child right) {
    		this.name = name;
    		this.left = left;
    		this.right = right;
    	}
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
