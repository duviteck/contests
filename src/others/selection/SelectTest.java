package others.selection;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by duviteck. 18 Nov 2014.
 */
public class SelectTest {
    private static final int TEST_ARRAY_MIN_LENGTH = 1000;
    private static final int TEST_ARRAY_MAX_LENGTH = 5000;
    private static final int TEST_ATTEMPTS = 10000;
    private static final Random random = new Random(System.currentTimeMillis());
    private static final List<Pair<int[], Integer>> specialTestCases = new ArrayList<>();
    private static final List<ISelect> selectAlgorithms = new ArrayList<>();

    static {
        selectAlgorithms.add(new QuickSelect());
        selectAlgorithms.add(new MedianOfMediansSelect());

        specialTestCases.add(Pair.of((int[])null, 0));
        specialTestCases.add(Pair.of((int[])null, 1));
        specialTestCases.add(Pair.of(new int[0], 0));
        specialTestCases.add(Pair.of(new int[0], 1));
        specialTestCases.add(Pair.of(new int[] {5, 7}, 1));
        specialTestCases.add(Pair.of(new int[] {7, 5}, 2));
        specialTestCases.add(Pair.of(new int[] {7, 5}, -101));
    }

    public static void main(String[] args) {
        for (int i = 0; i < TEST_ATTEMPTS; i++) {
            Pair<int[], Integer> testCase = generateTestArray();
            for (ISelect selection : selectAlgorithms) {
                checkSelection(selection, testCase);
            }
        }

        for (Pair<int[], Integer> specialCase : specialTestCases) {
            for (ISelect selection : selectAlgorithms) {
                checkSelection(selection, specialCase);
            }
        }

        System.out.println("All ok");
    }

    private static void checkSelection(ISelect selection, Pair<int[], Integer> testCase) {
        if (testCase.fst == null || testCase.snd <= 0 || testCase.snd > testCase.fst.length ) {
            try {
                // will crash with non-IllegalArgumentException in case of error
                selection.select(testCase.fst, testCase.snd);
            } catch (IllegalArgumentException e) { }
            return;
        }

        int[] copyArray = Arrays.copyOf(testCase.fst, testCase.fst.length);
        Arrays.sort(copyArray);
        int ans = selection.select(testCase.fst, testCase.snd);
        if (ans != copyArray[testCase.snd - 1]) {
            throw new IllegalStateException("Incorrect selection for selection " + selection.getName());
        }
    }

    private static Pair<int[], Integer> generateTestArray() {
        int size = TEST_ARRAY_MIN_LENGTH + random.nextInt(TEST_ARRAY_MAX_LENGTH - TEST_ARRAY_MIN_LENGTH + 1);

        int[] ar = new int[size];
        for (int i = 0; i < size; i++) {
            ar[i] = random.nextInt();
        }
        int k = random.nextInt(size);
        return Pair.of(ar, k);
    }
}
