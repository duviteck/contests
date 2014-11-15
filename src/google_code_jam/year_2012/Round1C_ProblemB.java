package google_code_jam.year_2012;

import java.io.*;
import java.util.StringTokenizer;

public class Round1C_ProblemB implements Runnable {
    private static BufferedReader reader;
    private static StringTokenizer st;
    private static double D;
    private static double[] time;
    private static double[] speed;
    private static double[] pos;
//    private static int len;


    public static void main(String[] args) {
        new Thread(new Round1C_ProblemB()).start();
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
            writer.println("Case #" + i + ":");
            D = nextDouble();
            int n = nextInt();
            time = new double[n];
            pos = new double[n];
            speed = new double[n];
            int k = nextInt();
            for (int j = 0; j < n; j++) {
                time[j] = nextDouble();
                pos[j] = nextDouble();
                if (pos[j] > D) {
                    time[j] = time[j - 1] + (D - pos[j - 1]) / ((pos[j] - pos[j - 1]) / (time[j] - time[j - 1])) ;
                    pos[j] = D;
                }
            }
            for (int j = 1; j < n; j++) {
                speed[j] = (pos[j] - pos[j - 1]) / (time[j] - time[j - 1]);
            }
            for (int j = 0; j < k; j++) {
                writer.println(calcAns(nextDouble()));
            }
//            for (double x : time) {
//                System.out.print(x + " ");
//            }
//            System.out.println();
//            for (double x : pos) {
//                System.out.print(x + " ");
//            }
//            System.out.println();
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

    static double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    static double calcAns(double a) {
        if (time.length < 2) return Math.sqrt(2 * D / a);

        double curStartTime = 0;
        int cur = 0;
        double dist;
        while (cur < time.length) {
            dist = (time[cur] - curStartTime) * (time[cur] - curStartTime) * a / 2;
            if (pos[cur] < dist) {
                curStartTime = time[cur] - Math.sqrt(2 * pos[cur] / a);
                //if (curStartTime > t) {
                //    System.out.println("AHTUNG");
                //}
            }
            cur++;
        }
        return curStartTime + Math.sqrt(2 * D / a);
    }

}