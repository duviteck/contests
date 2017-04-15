package google_code_jam.year_2017;

import java.io.*;

public class Qualification_A {
    private static final String FILE_ROOT = "src/google_code_jam/year_2017/qualification/";
    private static final String FILE_INPUT = FILE_ROOT + "a.in";
    private static final String FILE_OUTPUT = FILE_ROOT + "a.out";

    private static BufferedReader mReader;
    private static PrintWriter mWriter;

    public static void main(String[] args) throws IOException {
        mReader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_INPUT)));
        mWriter = new PrintWriter(FILE_OUTPUT);
        int testCasesCount = Integer.parseInt(mReader.readLine());

        for (int testCase = 1; testCase <= testCasesCount; testCase++) {
            String[] tokens = mReader.readLine().split(" ");
            char[] data = tokens[0].toCharArray();
            int k = Integer.parseInt(tokens[1]);

            int answer = calcSolution(data, k);
            printAnswer(testCase, answer);
        }

        mWriter.close();
        mReader.close();
    }

    private static void printAnswer(int testCase, int answer) {
        String answerText = answer < 0 ? "IMPOSSIBLE" : String.valueOf(answer);
//        System.out.format("Case #%d: %s\n", testCase, answerText);
        mWriter.printf("Case #%d: %s\n", testCase, answerText);
    }

    private static int calcSolution(char[] data, int k) {
        int flipCount = 0;
        for (int i = 0; i <= data.length - k; i++) {
            if (data[i] == '-') {
                flip(data, i, k);
                flipCount++;
            }
        }

        boolean areAllPositive = true;
        for (char c : data) {
            areAllPositive &= c == '+';
        }

        return areAllPositive ? flipCount : -1;
    }

    private static void flip(char[] data, int startIndex, int k) {
        for (int i = 0; i < k; i++) {
            int j = i + startIndex;
            data[j] = data[j] == '-' ? '+' : '-';
        }
    }
}
