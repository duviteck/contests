package cscenter.year_2014_2015.semester_1.hometask_2_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Points in space
 *
 * Created by duviteck. 29 Dec 2014.
 */
public class Problem_2G_Hard {

    // TL

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = parsePoint(reader.readLine());
        }

        long minDist = Long.MAX_VALUE;
        int bestIndex1 = -1;
        int bestIndex2 = -1;

        long dx;
        long dy;
        long dz;
        long dist;
        for (int i = 0; i < n; i++) {
            Point p1 = points[i];
            for (int j = i + 1; j < n; j++) {
                Point p2 = points[j];
                dx = p1.x - p2.x;
                dy = p1.y - p2.y;
                dz = p1.z - p2.z;
                dx *= dx;
                dy *= dy;
                dz *= dz;
                dist = dx + dy + dz;
                if (dist < minDist) {
                    minDist = dist;
                    bestIndex1 = i + 1;
                    bestIndex2 = j + 1;
                }
            }
        }

        System.out.println(Math.sqrt(minDist));
        System.out.println(bestIndex1 + " " + bestIndex2);
    }

    private static Point parsePoint(String line) {
        String[] tokens = line.split(" ");
        return new Point(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
    }


    private static class Point {
        long x;
        long y;
        long z;

        public Point(long x, long y, long z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
