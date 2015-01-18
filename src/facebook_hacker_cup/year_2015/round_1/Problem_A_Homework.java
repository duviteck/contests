package facebook_hacker_cup.year_2015.round_1;

import java.io.*;

/**
 * Created by duviteck. 17 Jan 2015.
 *
 * Your first-grade math teacher, Mr. Book, has just introduced you to an amazing new concept — primes!
 * According to your notes, a prime is a positive integer greater than 1 that is divisible by only 1 and itself.
 *
 * Primes seem fun, but without giving you and your 6-year-old colleagues time to consider their implications,
 * he's promptly gone on to define another term: primacity. He explains that the primacity of an integer
 * is the number of distinct primes which divide it. For example, the primacity of 12 is 2
 * (as it's divisible by primes 2 and 3), the primacity of 550 is 3 (as it's divisible by primes 2, 5, and 11),
 * and the primacity of 7 is 1 (as the only prime it's divisible by is 7).
 *
 * Following his lesson, Mr. Book has given you homework with some rather mean questions of the following form:
 * Given 3 integers A, B, and K, how many integers in the inclusive range [A, B] have a primacity of exactly K?
 *
 * Mr. Book probably expects his little homework assignment to take you and your classmates the rest
 * of the year to complete, giving him time to slack off and nap during the remaining math classes.
 * However, you want to learn more things from him instead! Can you use the skills you've learned
 * in your first-grade computer science classes to finish Mr. Book's homework before tomorrow's math class?
 *
 * Input
 * Input begins with an integer T, the number of homework questions. For each question, there is one line
 * containing 3 space-separated integers: A, B, and K.
 *
 * Output
 * For the ith question, print a line containing "Case #i: " followed by the number of integers
 * in the inclusive range [A, B] with a primacity of K.
 *
 * Constraints
 * 1 ≤ T ≤ 100
 * 2 ≤ A ≤ B ≤ 10^7
 * 1 ≤ K ≤ 10^9
 */
public class Problem_A_Homework {

    private static final String FILE_IN = "in.txt";
    private static final String FILE_OUT = "out.txt";

    private static final int SIEVE_SIZE = 10000001;
    private static final int[] sieve = new int[SIEVE_SIZE];

    public static void main(String[] args) throws IOException {
        buildSieve();

        MyReader reader = new MyReader(new FileInputStream(new File(FILE_IN)));
        PrintWriter writer = new PrintWriter(new File(FILE_OUT));

        int tests = reader.nextInt();
        for (int i = 1; i <= tests; i++) {
            int a = reader.nextInt();
            int b = reader.nextInt();
            int k = reader.nextInt();

            int ans = calcAns(a, b, k);
            writer.format("Case #%d: %d\n", i, ans);
        }
        writer.flush();
    }

    private static void buildSieve() {
        for (int i = 2; i < SIEVE_SIZE; i++) {
            if (sieve[i] > 0) { // non-prime
                continue;
            }

            sieve[i] = 1;
            for (int j = i * 2; j < SIEVE_SIZE; j += i) {
                sieve[j]++;
            }
        }
    }

    private static int calcAns(int a, int b, int k) {
        if (k > 8) {
            return 0;
        }

        int ans = 0;
        for (int i = a; i <= b; i++) {
            if (sieve[i] == k) {
                ans++;
            }
        }
        return ans;
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
