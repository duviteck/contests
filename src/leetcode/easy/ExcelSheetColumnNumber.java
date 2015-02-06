package leetcode.easy;

/**
 * Created by duviteck. 07 Feb 2015.
 */
public class ExcelSheetColumnNumber {

    public int titleToNumber(String s) {
        int ans = 0;
        for (char c : s.toCharArray()) {
            ans = ans * 26 + c - 'A' + 1;
        }
        return ans;
    }
}
