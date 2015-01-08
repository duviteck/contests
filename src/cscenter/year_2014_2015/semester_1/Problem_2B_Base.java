package cscenter.year_2014_2015.semester_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Testing system
 *
 * In fact, problem about stable sorting
 *
 * Created by duviteck. 28 Dec 2014.
 */
public class Problem_2B_Base {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        Tuple[] elems = new Tuple[n];
        for (int i = 0; i < n; i++) {
            int[] temp = parseLine(reader.readLine());
            Tuple newElem = new Tuple(temp[0], temp[1], i + 1);
            elems[i] = newElem;
        }

        mergeSort(elems);

        printAns(elems);
    }

    private static int[] parseLine(String line) {
        String[] temp = line.split(" ");
        int[] res = new int[2];
        res[0] = Integer.parseInt(temp[0]);
        res[1] = Integer.parseInt(temp[1]);
        return res;
    }

    private static void printAns(Tuple[] elems) {
        StringBuilder sb = new StringBuilder(elems.length * 7);
        for (Tuple elem : elems) {
            sb.append(elem.order).append(" ");
        }
        sb.setLength(sb.length() - 1);

        System.out.println(sb.toString());
    }

    private static void mergeSort(Tuple[] ar) {
        if (ar == null || ar.length <= 1) {
            return;
        }

        int mid = ar.length / 2;

        Tuple[] a1 = new Tuple[mid];
        System.arraycopy(ar, 0, a1, 0, mid);
        mergeSort(a1);

        Tuple[] a2 = new Tuple[ar.length - mid];
        System.arraycopy(ar, mid, a2, 0, ar.length - mid);
        mergeSort(a2);

        Tuple[] mergeRes = merge(a1, a2);
        System.arraycopy(mergeRes, 0, ar, 0, ar.length);
    }

    private static Tuple[] merge(Tuple[] a1, Tuple[] a2) {
        Tuple[] res = new Tuple[a1.length + a2.length];
        int resPos = 0;
        int pos1 = 0;
        int pos2 = 0;

        while (resPos < res.length) {
            if (pos1 == a1.length) {
                System.arraycopy(a2, pos2, res, resPos, a2.length - pos2);
                resPos = res.length;
            } else if (pos2 == a2.length) {
                System.arraycopy(a1, pos1, res, resPos, a1.length - pos1);
                resPos = res.length;
            } else {
                int compareRes = a1[pos1].compareTo(a2[pos2]);
                if (compareRes < 0) {
                    res[resPos++] = a1[pos1++];
                } else {
                    res[resPos++] = a2[pos2++];
                }
            }
        }
        return res;
    }


    private static class Tuple implements Comparable<Tuple> {
        int problemCount;
        int time;
        int order;

        public Tuple(int problemCount, int time, int order) {
            this.problemCount = problemCount;
            this.time = time;
            this.order = order;
        }

        @Override
        public String toString() {
            return problemCount + " " + time + " " + order;
        }

        @Override
        public int compareTo(Tuple o) {
            if (problemCount != o.problemCount) {
                return o.problemCount - problemCount;
            } else if (time != o.time) {
                return time - o.time;
            } else {
                return order - o.order;
            }
        }
    }
}
