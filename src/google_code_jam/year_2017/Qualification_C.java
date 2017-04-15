package google_code_jam.year_2017;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Qualification_C {

    private static final String FILE_ROOT = "src/google_code_jam/year_2017/qualification/";
    private static final String FILE_INPUT = FILE_ROOT + "c.in";
    private static final String FILE_OUTPUT = FILE_ROOT + "c.out";

    private static BufferedReader mReader;
    private static PrintWriter mWriter;

    public static void main(String[] args) throws IOException {
        mReader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_INPUT)));
        mWriter = new PrintWriter(FILE_OUTPUT);
        int testCasesCount = Integer.parseInt(mReader.readLine());

        for (int testCase = 1; testCase <= testCasesCount; testCase++) {
            String[] tokens = mReader.readLine().split(" ");
            long n = Long.parseLong(tokens[0]);
            long k = Long.parseLong(tokens[1]);

            long[] answer = calcSolution(n, k);
            printAnswer(testCase, answer);
        }

        mWriter.close();
        mReader.close();
    }

    private static void printAnswer(int testCase, long[] answer) {
//        System.out.format("Case #%d: %d %d\n", testCase, answer[0], answer[1]);
        mWriter.printf("Case #%d: %d %d\n", testCase, answer[0], answer[1]);
    }

    private static long[] calcSolution(long n, long k) {
        Map<Long, Long> segmentsSizeToCountMap = new HashMap<>(20);
        segmentsSizeToCountMap.put(n, 1L);

        occupyStalls(segmentsSizeToCountMap, k - 1);

        long largestSegmentSize = Collections.max(segmentsSizeToCountMap.keySet());
        long[] LR = getSegmentSizesAfterSplit(largestSegmentSize);
        return new long[] {Math.max(LR[0], LR[1]), Math.min(LR[0], LR[1])};
    }

    private static void occupyStalls(Map<Long, Long> segmentsSizeToCountMap, long count) {
        if (count == 0) {
            return;
        }

        long largestSegmentSize = Collections.max(segmentsSizeToCountMap.keySet());
        long largestSegmentCount = segmentsSizeToCountMap.get(largestSegmentSize);
        if (count < largestSegmentCount) {
            return;
        }

        // need to split all largest segments
        long[] newSegmentSizes = getSegmentSizesAfterSplit(largestSegmentSize);
        for (long newSize : newSegmentSizes) {
            Long existingCount = segmentsSizeToCountMap.get(newSize);
            if (existingCount == null) {
                existingCount = 0L;
            }
            existingCount += largestSegmentCount;
            segmentsSizeToCountMap.put(newSize, existingCount);
        }

        segmentsSizeToCountMap.remove(largestSegmentSize);
        occupyStalls(segmentsSizeToCountMap, count - largestSegmentCount);
    }

    private static long[] getSegmentSizesAfterSplit(long n) {
        n--;
        long left = n / 2;
        long right = n - left;
        return new long[] {left, right};
    }

}
