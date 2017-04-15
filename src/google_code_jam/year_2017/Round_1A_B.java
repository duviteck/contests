package google_code_jam.year_2017;

import java.io.*;
import java.util.Arrays;

public class Round_1A_B {

    private static final String FILE_ROOT = "src/google_code_jam/year_2017/";
    private static final String FILE_INPUT = FILE_ROOT + "b.in";
    private static final String FILE_OUTPUT = FILE_ROOT + "b.out";

    private static BufferedReader mReader;
    private static PrintWriter mWriter;

    public static void main(String[] args) throws IOException {
        mReader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_INPUT)));
        mWriter = new PrintWriter(FILE_OUTPUT);
        int testCasesCount = Integer.parseInt(mReader.readLine());

        for (int testCase = 1; testCase <= testCasesCount; testCase++) {
            int[] temp = parseIntArray(mReader.readLine());
            int n = temp[0];
            int p = temp[1];
            int[] standard = parseIntArray(mReader.readLine());
            int[][] packages = new int[n][];
            for (int i = 0; i < n; i++) {
                packages[i] = parseIntArray(mReader.readLine());
                Arrays.sort(packages[i]);
            }

            Segment[][] segments = prepareInputData(n, p, standard, packages);
            int answer = calcSolution(n, p, segments);
            printAnswer(testCase, answer);
        }

        mWriter.close();
        mReader.close();
    }

    private static Segment[][] prepareInputData(int n, int p, int[] standard, int[][] packages) {
        Segment[][] result = new Segment[n][];

        for (int i = 0; i < n; i++) {
            int curStandard = standard[i];
            int[] packs = packages[i];

            Segment[] segments = new Segment[p];
            for (int j = 0; j < p; j++) {
                segments[j] = prepareSegment(packs[j], curStandard);
            }

            result[i] = segments;
        }

        return result;
    }

    private static Segment prepareSegment(int pack, int standard) {
        int max = (int)(pack / 0.9f / standard);

        int min = (int)(pack / 1.1f / standard);
        if (min * standard * 1.1 < pack) {
            min++;
        }

        return new Segment(min, max);
    }

    private static int calcSolution(int n, int p, Segment[][] segments) {
//        for (Segment[] ingredient : segments) {
//            for (Segment s : ingredient) {
//                System.out.println(s);
//            }
//            System.out.println();
//        }

        int[] pos = new int[n];
        int result = 0;

        int kitSize = 1;
        while (true) {
            if (result == p) {
                break;
            }

            boolean allOk = true;

            for (int i = 0; i < n; i++) {
                if (!allOk) {
                    break;
                }

                while (segments[i][pos[i]].isLess(kitSize)) {
                    allOk = false;
                    pos[i]++;
                    if (pos[i] == p) {
                        return result;
                    }
                }

                if (segments[i][pos[i]].isMore(kitSize)) {
                    allOk = false;
                    kitSize = segments[i][pos[i]].from;
                }
            }

            if (allOk) {
                result++;
                for (int i = 0; i < n; i++) {
                    pos[i]++;
                    if (pos[i] == p) {
                        return result;
                    }
                }
            }
        }

        return result;
    }


    private static int[] parseIntArray(String text) {
        String[] tokens = text.split(" ");
        int[] result = new int[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            result[i] = Integer.parseInt(tokens[i]);
        }

        return result;
    }

    private static void printAnswer(int testCase, int answer) {
//        System.out.format("Case #%d: %d\n", testCase, answer);
        mWriter.printf("Case #%d: %d\n", testCase, answer);
    }


    private static class Segment {
        final int from;
        final int to;

        public Segment(int from, int to) {
            this.from = from;
            this.to = to;
        }


        public boolean isLess(int kitSize) {
            return to < kitSize;
        }

        public boolean isMore(int kitSize) {
            return from > kitSize;
        }

        @Override
        public String toString() {
            return "[" +  from + "," + to + "]";
        }
    }
}
