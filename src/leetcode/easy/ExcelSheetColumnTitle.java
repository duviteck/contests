package leetcode.easy;

/**
 * Created by duviteck. 07 Feb 2015.
 */
public class ExcelSheetColumnTitle {

    public String convertToTitle(int n) {
        String res = "";
        while (n > 0) {
            char c = (char) (((n - 1) % 26) + 'A');
            res = c + res;
            n = (n - 1) / 26;
        }
        return res;
    }
}
