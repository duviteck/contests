package leetcode.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by duviteck. 07 Feb 2015.
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int len = s.length();
        Deque<Integer> stack = new LinkedList<>();
        int bestLen = 0;
        int minStart = -1;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (stack.size() == 0 && c == ')') {
                minStart = i;
                continue;
            }

            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                stack.pop();
                int curLen = (stack.isEmpty()) ? (i - minStart) : (i - stack.peek());
                if (curLen > bestLen) {
                    bestLen = curLen;
                }
            } else {
                throw new IllegalArgumentException("invalid input symbol");
            }
        }

        return bestLen;
    }
}
