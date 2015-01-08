package cscenter.year_2014_2015.semester_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Nearest points (in 2-dimensional space)
 */

public class Problem_2F_Advanced {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());

		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			points[i] = parsePoint(reader.readLine());
		}

		double dist = calcAns(points);
		System.out.println(dist);
	}

	private static Point parsePoint(String line) {
		String[] tokens = line.split(" ");
		return new Point(Long.parseLong(tokens[0]), Long.parseLong(tokens[1]));
	}

	private static double calcAns(Point[] points) {
		// sort by X coordinate
		Arrays.sort(points, Point.X_ASCENDING);

		double bestDist = findBestDist(points, 0, points.length);
		return Math.sqrt(bestDist);
	}

	private static double findBestDist(Point[] points, int start, int end) {
		int arrayLength = end - start;
		if (arrayLength <= 3) {
			if (arrayLength == 2) {
				return dist(points[start], points[start + 1]);
			} else {
				double d1 = dist(points[start], points[start + 1]);
				double d2 = dist(points[start], points[start + 2]);
				double d3 = dist(points[start + 1], points[start + 2]);
				return Math.min(d1, Math.min(d2, d3));
			}
		}

		int mid = (start + end) / 2;
		double bestDistLeft = findBestDist(points, start, mid);
		double bestDistRight = findBestDist(points, mid, end);

		double xMiddle = (double)(points[mid - 1].x + points[mid].x) / 2;
		double bestDist = Math.min(bestDistLeft, bestDistRight);
		return mergeBestDists(points, start, end, xMiddle, bestDist);
	}

	private static double mergeBestDists(Point[] allPoints, int start, int end, double xMiddle, double bestDist) {
		double h = Math.sqrt(bestDist);

		// get only points, which should be checked
		List<Point> points = new ArrayList<>(end - start);
		for (int i = start; i < end; i++) {
			Point p = allPoints[i];
			if (Math.abs(xMiddle - p.x) <= h) {
				points.add(p);
			}
		}

		// sort target points by Y coordinate
		Collections.sort(points, Point.Y_DESCENDING);

		// try to relax best distance
		int size = points.size();
		for (int i = 0; i < size - 1; i++) {
			Point p1 = points.get(i);
			int checkCount = Math.min(8, size - i - 1);
			for (int j = 1; j <= checkCount; j++) {
				Point p2 = points.get(i + j);
				if (p1.y - p2.y > h) {
					break;
				}

				double dist = dist(p1, p2);
				if (dist < bestDist) {
					bestDist = dist;
				}
			}
		}

		return bestDist;
	}

	private static double dist(Point p1, Point p2) {
		long dx = p1.x - p2.x;
		long dy = p1.y - p2.y;
		return dx * dx + dy * dy;
	}

	private static class Point {
		public static Comparator<Point> X_ASCENDING = new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				return (p1.x < p2.x) ? -1 : (p1.x > p2.x) ? 1 : 0;
			}
		};

		public static Comparator<Point> Y_DESCENDING = new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				return (p1.y > p2.y) ? -1 : (p1.y < p2.y) ? 1 : 0;
			}
		};

		long x;
		long y;

		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
}