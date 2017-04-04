package leetcode.hard;

import java.util.*;

/**
 * Created by duviteck. 27 Jan 2015.
 */
public class MaxPointsOnALine {
    private static Map<Pair, Integer> map;
    private static Map<Integer, Integer> verticalMap;

    public static void main(String[] args) {
        MaxPointsOnALine a = new MaxPointsOnALine();
        int ans = a.maxPoints(new Point[] {new Point(0,0), new Point(1, 1), new Point(0, 0)});
        System.out.println(ans);
    }

    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        map = new HashMap<>();
        verticalMap = new HashMap<>();
        List<PointWrapper> pointWrappers = preparePoints(points);
        if (pointWrappers.size() == 1) {
            return pointWrappers.size();
        }

        for (int i = 0; i < pointWrappers.size(); i++) {
            PointWrapper p1 = pointWrappers.get(i);
            for (int j = i + 1; j < pointWrappers.size(); j++) {
                PointWrapper p2 = pointWrappers.get(j);
                calcPoints(p1, p2);
            }
        }

        int bestCount = 0;
        for (int count : map.values()) {
            if (count > bestCount) {
                bestCount = count;
            }
        }
        for (int count : verticalMap.values()) {
            if (count > bestCount) {
                bestCount = count;
            }
        }

        return calcAns(bestCount);
    }

    private static List<PointWrapper> preparePoints(Point[] points) {
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return (p1.x < p2.x) ? -1 : (p1.x > p2.x) ? 1 : 0;
            }
        });

        PointWrapper lastPoint = new PointWrapper(points[0].x, points[0].y);
        List<PointWrapper> res = new ArrayList<>(points.length);
        res.add(lastPoint);

        for (int i = 1; i < points.length; i++) {
            Point curPoint = points[i];
            if (curPoint.x == lastPoint.x && curPoint.y == lastPoint.y) {
                lastPoint.count++;
            } else {
                lastPoint = new PointWrapper(curPoint.x, curPoint.y);
                res.add(lastPoint);
            }
        }

        return res;
    }

    private static int calcAns(int count) {
        return (1 + (int) Math.sqrt(1 + 8 * count)) / 2;
    }

    private static void calcPoints(PointWrapper p1, PointWrapper p2) {
        if (p1.x == p2.x) {
            calcVerticalPoints(p1, p2);
        } else {
            calcCommonPoints(p1, p2);
        }
    }

    private static void calcVerticalPoints(PointWrapper p1, PointWrapper p2) {
        Integer curCount = verticalMap.get(p1.x);
        int addCount = p1.count * p2.count;
        if (curCount == null) {
            verticalMap.put(p1.x, addCount);
        } else {
            verticalMap.put(p1.x, curCount + addCount);
        }
    }

    private static void calcCommonPoints(PointWrapper p1, PointWrapper p2) {
        float k = (float)(p2.y - p1.y) / (float)(p2.x - p1.x);
        float b = (float)p1.y - k * p1.x;
        Pair pair = new Pair(k, b);

        Integer curCount = map.get(pair);
        int addCount = p1.count * p2.count;
        if (curCount == null) {
            map.put(pair, addCount);
        } else {
            map.put(pair, curCount + addCount);
        }
    }


    private static class Pair {
        float k;
        int kBits;
        float b;
        int bBits;

        public Pair(float k, float b) {
            this.k = k;
            this.kBits = Float.floatToIntBits(k);
            this.b = b;
            this.bBits = Float.floatToIntBits(b);
        }

        @Override
        public int hashCode() {
            return kBits + bBits;
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof Pair) {
                Pair p = (Pair) object;
                return (kBits == p.kBits) && (bBits == p.bBits);
            }
            return false;
        }
    }


    private static class PointWrapper {
        int x;
        int y;
        int count;

        public PointWrapper(int x, int y) {
            this.x = x;
            this.y = y;
            count = 1;
        }

        @Override
        public int hashCode() {
            return x + y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof PointWrapper) {
                PointWrapper pw = (PointWrapper) obj;
                return (x == pw.x) && (y == pw.y);
            }
            return false;
        }
    }


    private static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}
