package codeforces.spbsu_train_9_geometry_continue;

import java.io.*;
import java.util.*;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_B_HullPerimeter implements Runnable {

    private static BufferedReader reader;
    private static StringTokenizer st;
    private static final String filename = "hullp";

    public static void main(String[] args) {
        new Thread(new Problem_B_HullPerimeter()).start();
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

        // Read data
        int n = (int)nextLong();
        Set<MyPoint> pointSet = new HashSet<MyPoint>(n);
        for (int i = 0; i < n; i++) {
            MyPoint p = new MyPoint(nextLong(), nextLong());
            pointSet.add(p);
        }
        reader.close();

        n = pointSet.size();
        MyPoint[] points = new MyPoint[n];
        pointSet.toArray(points);

        long minY = points[0].y;
        long minX = points[0].x;
        int baseIndex = 0;
        for (int i = 1; i < n; i++) {
            MyPoint p = points[i];
            if (p.y < minY || (p.y == minY && p.x < minX)) {
                minY = p.y;
                minX = p.x;
                baseIndex = i;
            }
        }

        // Calc angles for each point
        for (MyPoint point : points) {
            point.calcMeasures(points[baseIndex]);
        }

        // Sort points via polar angle
        Arrays.sort(points, new PointPolarAngleComparator());

        // Prepare hull
        MyPoint[] hull = new MyPoint[n];
        hull[0] = points[0];
        hull[1] = points[1];
        int hullLen = 2;
        for (int i = 2; i < n; i++) {
            long check = isLeft(hull[hullLen - 2], hull[hullLen - 1], points[i]);
            while (check < 0) {
                hullLen--;
                check = isLeft(hull[hullLen - 2], hull[hullLen - 1], points[i]);
            }
            if (check > 0) {
                hull[hullLen] = points[i];
                hullLen++;
            } else {
                hull[hullLen - 1] = points[i];
            }
        }
        if (isLeft(hull[hullLen - 2], hull[hullLen - 1], hull[0]) == 0) {
            hullLen--;
        }

        writer.println(calcPerimeter(hull, hullLen));

        writer.close();
    }

    private long isLeft(MyPoint p0, MyPoint p1, MyPoint p2) {
        return (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y);
    }

    private static String nextToken() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(reader.readLine());
        }
        return st.nextToken();
    }

    private static long nextLong() throws NumberFormatException, IOException {
        return Long.parseLong(nextToken());
    }

    private static double calcPolarAngle(double x, double y) {
        double angle = Math.atan2(y, x);
        if (angle < 0) {
            angle += 2 * Math.PI;
        }
        return angle;
    }

    private static double calcPerimeter(MyPoint[] points, int len) {
        double ans = 0;
        for (int i = 1; i < len; i++) {
            MyPoint p1 = points[i];
            MyPoint p2 = points[i - 1];
            ans += Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
        }

        MyPoint p1 = points[0];
        MyPoint p2 = points[len - 1];
        ans += Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));

        return ans;
    }

    private class MyPoint {
        public final long x;
        public final long y;
        public double polarAngle;
        public long distToBase;

        public MyPoint(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public void calcMeasures(MyPoint basePoint) {
            // calc distance to base point
            distToBase = (x - basePoint.x) * (x - basePoint.x) + (y - basePoint.y) * (y - basePoint.y);

            // calc angle
            polarAngle = calcPolarAngle(x - basePoint.x, y - basePoint.y);
        }

        public boolean equals(Object obj) {
            MyPoint p = (MyPoint)obj;
            return (x == p.x && y == p.y);
        }

        public int hashCode() {
            return (int)(x + y);
        }

    }

    class PointPolarAngleComparator<MyPoint> implements Comparator<MyPoint> {
        @Override
        public int compare(MyPoint o1, MyPoint o2) {
            Problem_B_HullPerimeter.MyPoint p1 = (Problem_B_HullPerimeter.MyPoint)o1;
            Problem_B_HullPerimeter.MyPoint p2 = (Problem_B_HullPerimeter.MyPoint)o2;
            if (p1.polarAngle == p2.polarAngle) {
                return (p1.distToBase < p2.distToBase) ? -1 : (p1.distToBase > p2.distToBase) ? 1 : 0;
            } else {
                return (p1.polarAngle < p2.polarAngle) ? -1 : 1;
            }


        }
    }

}
