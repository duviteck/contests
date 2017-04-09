package google_code_jam.year_2017.qualification;

//public abstract class TaskSolver {
//
//
//    public abstract void readInput();
//    public abstract void writeOutput(int testCaseIndex);
//
//}

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Qualification_B {

    private static final String FILE_ROOT = "src/google_code_jam/year_2017/qualification/";
    private static final String FILE_INPUT = FILE_ROOT + "b.in";
    private static final String FILE_OUTPUT = FILE_ROOT + "b.out";

    private static BufferedReader mReader;
    private static PrintWriter mWriter;

    public static void main(String[] args) throws IOException {
        mReader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_INPUT)));
        mWriter = new PrintWriter(FILE_OUTPUT);
        int testCasesCount = Integer.parseInt(mReader.readLine());

        for (int testCase = 1; testCase <= testCasesCount; testCase++) {
            long input = Long.parseLong(mReader.readLine());
            long answer = calcSolution(input);
            printAnswer(testCase, answer);
        }

        mWriter.close();
        mReader.close();
    }

    private static void printAnswer(int testCase, long answer) {
//        System.out.format("Case #%d: %d\n", testCase, answer);
        mWriter.printf("Case #%d: %d\n", testCase, answer);
    }

    private static long calcSolution(long input) {
        if (input < 10) {
            return input;
        }

        long[] digits = toDigits(input);
        modifyToTidy(digits);
        return toLong(digits);
    }

    private static long toLong(long[] digits) {
        long result = 0;
        for (long digit : digits) {
            result = result * 10L + digit;
        }
        return result;
    }

    private static void modifyToTidy(long[] digits) {
        canBeTidy(digits, 0, -1);
    }

    private static boolean canBeTidy(long[] digits, int startIndex, long minValue) {
        if (startIndex == digits.length) {
            return true;
        }

        long curValue = digits[startIndex];
        if (curValue < minValue) {
            return false;
        }

        boolean canUseCurValue = canBeTidy(digits, startIndex + 1, curValue);
        if (canUseCurValue) {
            return true;
        }

        // can't use current - decrease it if possible
        if (curValue == minValue) {
            return false;   // can't decrease
        } else {
            digits[startIndex] = curValue - 1;
            fillWithNines(digits, startIndex + 1);
            return true;
        }
    }

    private static void fillWithNines(long[] digits, int start) {
        for (int i = start; i < digits.length; i++) {
            digits[i] = 9;
        }
    }

    private static long[] toDigits(long input) {
        List<Long> digits = new ArrayList<>(18);
        while (input > 0) {
            long digit = (input % 10);
            digits.add(digit);
            input /= 10;
        }
        Collections.reverse(digits);

        long[] result = new long[digits.size()];
        for (int i = 0; i < result.length; i++)
            result[i] = digits.get(i);
        return result;
    }
}
