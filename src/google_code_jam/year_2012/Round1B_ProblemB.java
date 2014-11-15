package google_code_jam.year_2012;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Round1B_ProblemB implements Runnable {
    private static BufferedReader reader;
    private static StringTokenizer st;
    private static int waterLevel;
    private static int n;
    private static int m;
    private static Point[][] cave;
    private static double[][] times;

    public static void main(String[] args) {
        new Thread(new Round1B_ProblemB()).start();
    }

    @Override
    public void run() {
        try {
            solve();
        } catch (IOException e) {}
    }

    private void solve() throws IOException {
        reader = new BufferedReader(new FileReader("b.in"));
        PrintWriter writer = new PrintWriter(new FileWriter("b.out"));

        int numOfTests = nextInt();
        for (int i = 1; i <= numOfTests; i++) {
            waterLevel = nextInt();
            n = nextInt();
            m = nextInt();
            cave = new Point[n][m];
            for (int i1 = 0; i1 < n; i1++) {
                for (int i2 = 0; i2 < m; i2++) {
                    cave[i1][i2] = new Point();
                    cave[i1][i2].x = nextInt();
                }
            }
            for (int i1 = 0; i1 < n; i1++) {
                for (int i2 = 0; i2 < m; i2++) {
                    cave[i1][i2].y = nextInt();
                }
            }
            double ans = calcAns();
            writer.println("Case #" + i + ": " + ans);
        }

        writer.close();
    }

    static String nextToken() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(reader.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    static double calcAns() {
        times = new double[n][m];
        for (double[] a : times) {
            Arrays.fill(a, Double.MAX_VALUE);
        }
        pseudoDfs(0, 0);
        if (times[n - 1][m - 1] == 0) {
            return 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (times[i][j] != 0) {
                    times[i][j] = getMinTime(i, j);
                }
            }
        }

        return times[n - 1][m - 1];
    }

    static void pseudoDfs(int i, int j) {
        times[i][j] = 0;
        Point cur, adj;
        if (i + 1 < n && times[i + 1][j] != 0) {
            cur = cave[i][j];
            adj = cave[i + 1][j];
            if (adj.x - waterLevel >= 50 && adj.x - cur.y >= 50 && adj.x - adj.y >= 50 &&
                    cur.x - adj.y >= 50) {
                pseudoDfs(i+1, j);
            }
        }
        if (i - 1 >= 0 && times[i - 1][j] != 0) {
            cur = cave[i][j];
            adj = cave[i - 1][j];
            if (adj.x - waterLevel >= 50 && adj.x - cur.y >= 50 && adj.x - adj.y >= 50 &&
                    cur.x - adj.y >= 50) {
                pseudoDfs(i - 1, j);
            }
        }
        if (j + 1 < m && times[i][j + 1] != 0) {
            cur = cave[i][j];
            adj = cave[i][j + 1];
            if (adj.x - waterLevel >= 50 && adj.x - cur.y >= 50 && adj.x - adj.y >= 50 &&
                    cur.x - adj.y >= 50) {
                pseudoDfs(i, j + 1);
            }
        }
        if (j - 1 >= 0 && times[i][j - 1] != 0) {
            cur = cave[i][j];
            adj = cave[i][j - 1];
            if (adj.x - waterLevel >= 50 && adj.x - cur.y >= 50 && adj.x - adj.y >= 50 &&
                    cur.x - adj.y >= 50) {
                pseudoDfs(i, j - 1);
            }
        }
    }

    static double getMinTime(int i, int j) {
        Point cur = cave[i][j];
        double min = Double.MAX_VALUE;
        if (cur.x - cur.y < 50) return min;

        if (i + 1 < n) {
            min = Math.min(min, getTransferTime(i, j, i + 1, j));
        }
        if (i - 1 >= 0) {
            min = Math.min(min, getTransferTime(i, j, i - 1, j));
        }
        if (j + 1 < m) {
            min = Math.min(min, getTransferTime(i, j, i, j + 1));
        }
        if (j - 1 >= 0) {
            min = Math.min(min, getTransferTime(i, j, i, j - 1));
        }
        return min;
    }

    static double getTransferTime(int s1, int s2, int e1, int e2) {
        Point dest = cave[s1][s2];
        Point src = cave[e1][e2];
        if (dest.x - dest.y < 50) return Double.MAX_VALUE;
        if (src.x - dest.y < 50) return Double.MAX_VALUE;
        if (dest.x - src.y < 50) return Double.MAX_VALUE;

        double waterLevelInSrc = waterLevel - times[e1][e2];
        double waitTime = Math.max(0, (50 - dest.x + waterLevelInSrc) / 10);
        waterLevelInSrc -= waitTime * 10;
        if (waterLevelInSrc - src.y < 20) {
            return times[e1][e2] + waitTime + 10;
        } else {
            return times[e1][e2] + waitTime + 1;
        }
    }

}