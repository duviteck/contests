package leetcode.medium;

/**
 * Given an input string, reverse the string word by word.
 *
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 *
 * Clarification:
 * What constitutes a word?
 * A sequence of non-space characters constitutes a word.
 *
 * Could the input string contain leading or trailing spaces?
 * Yes. However, your reversed string should not contain leading or trailing spaces.
 *
 * How about multiple spaces between two words?
 * Reduce them to a single space in the reversed string.
 *
 * Created by duviteck. 02 Dec 2014.
 */
public class ReverseWordsInAString {

    public static void main(String[] args) {
        ReverseWordsInAString instance = new ReverseWordsInAString();
        System.out.println(instance.reverseWords("   a   b "));
    }

    public String reverseWords(String s) {
        if (s == null) {
            return null;
        }

        s = s.trim();
        if (s.length() == 0) {
            return s;
        }

        char[] chars = s.toCharArray();
        reverse(chars, 0, chars.length - 1);

        StringBuilder sb = new StringBuilder(chars.length);
        int start = 0;
        while (start < chars.length) {
            if (sb.length() > 0) {
                sb.append(' ');
            }

            int[] wordIndexes = findWord(chars, start);
            int len = wordIndexes[1] - wordIndexes[0] + 1;
            reverse(chars, wordIndexes[0], wordIndexes[1]);
            sb.append(chars, wordIndexes[0], len);
            start = wordIndexes[1] + 1;
        }
        return sb.toString();
    }

    private int[] findWord(char[] chars, int start) {
        int[] res = new int[2];
        boolean isWord = false;
        for (int i = start; i < chars.length; i++) {
            if (!isWord && chars[i] != ' ') {
                res[0] = i;
                isWord = true;
            } else if (isWord && chars[i] == ' ') {
                res[1] = i - 1;
                return res;
            }
        }
        if (isWord) {
            res[1] = chars.length - 1;
        } else {
            res[0] = res[1] = -1;
        }
        return res;
    }

    private void reverse(char[] chars, int start, int end) {
        int i1 = start;
        int i2 = end;
        while (i1 < i2) {
            swap(chars, i1, i2);
            i1++;
            i2--;
        }
    }

    private void swap(char[] ar, int pos1, int pos2) {
        char temp = ar[pos1];
        ar[pos1] = ar[pos2];
        ar[pos2] = temp;
    }
}
