package codeforces.spbsu_train_8_geometry_base;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_C_PolygonArea implements Runnable {

    private static BufferedReader reader;
    private static StringTokenizer st;
    private static final String filename = "area";

    public static void main(String[] args) {
        new Thread(new Problem_C_PolygonArea()).start();
    }

    @Override
    public void run() {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void solve() throws IOException {
        reader = new BufferedReader(new FileReader(filename + ".in"));
        PrintWriter writer = new PrintWriter(new FileWriter(filename + ".out"));

        int n = nextInt();
        int firstX = nextInt();
        int firstY = nextInt();
        int lastX = firstX;
        int lastY = firstY;

        double ans = 0;
        for (int i = 0; i < n - 1; i++) {
            int curX = nextInt();
            int curY = nextInt();
            ans += (curX + lastX) * (lastY - curY);
            lastX = curX;
            lastY = curY;
        }
        ans += (lastX + firstX) * (lastY - firstY);

        ans = Math.abs(ans) / 2;
        writer.println(ans);

        writer.close();
    }

    private static String nextToken() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(reader.readLine());
        }
        return st.nextToken();
    }

    private static int nextInt() throws NumberFormatException, IOException {
        return Integer.parseInt(nextToken());
    }
}
