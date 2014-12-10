package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duviteck. 11 Dec 2014.
 */
public class LetterCombinationsOfAPhoneNumber {

    public List<String> letterCombinations(String line) {
        List<String> res = new ArrayList<>();
        if (line == null) {
            return null;
        }
        if (line.length() == 0) {
            res.add("");
            return res;
        }

        int[] digits = new int[line.length()];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = line.charAt(i) - '0';
        }

        char[] buffer = new char[line.length()];
        combos(buffer, digits, 0, res);
        return res;
    }

    private void combos(char[] buf, int[] digits, int index, List<String> res) {
        if (index == buf.length) {
            res.add(new String(buf));
            return;
        }

        char[] letters = getLetters(digits[index]);
        if (letters == null) {
            return;    // don’t add anything
        }
        for (char l : letters) {
            buf[index] = l;
            combos(buf, digits, index + 1, res);
        }
        buf[index] = 0;
    }

    private char[] getLetters(int digit) {
        switch (digit) {
            case 0:
                return new char[]{'_'};
            case 1:
                return null;
            case 2:
                return new char[]{'a', 'b', 'c'};
            case 3:
                return new char[]{'d', 'e', 'f'};
            case 4:
                return new char[]{'g', 'h', 'i'};
            case 5:
                return new char[]{'j', 'k', 'l'};
            case 6:
                return new char[]{'m', 'n', 'o'};
            case 7:
                return new char[]{'p', 'q', 'r', 's'};
            case 8:
                return new char[]{'t', 'u', 'v'};
            case 9:
                return new char[]{'w', 'x', 'y', 'z'};
        }
        return null;
    }
}
