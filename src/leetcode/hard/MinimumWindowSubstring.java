package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by duviteck. 26 Jan 2015.
 */
public class MinimumWindowSubstring {

    private static char[] s;
    private static char[] t;
    private static Map<Character, Integer> window;
    private static Map<Character, Integer> tMap;
    private static int bestStart;
    private static int bestLen;
    private static int windowStart;
    private static int windowEnd;
    private static int foundCount;

    public String minWindow(String S, String T) {
        if (S == null || T == null) {
            return null;
        }
        if (T.length() == 0) {
            return "";
        }

        init(S, T);

        for (; windowEnd < s.length; windowEnd++) {
            char c = s[windowEnd];
            if (!tMap.containsKey(c)) {
                continue;
            }

            addToMap(window, c);
            if (window.get(c) <= tMap.get(c)) {
                foundCount++;
                if (foundCount == t.length) {
                    // reduce stage
                    for (; windowStart <= windowEnd; windowStart++) {
                        char c1 = s[windowStart];
                        if (!tMap.containsKey(c1)) {
                            continue;
                        }

                        removeFromMap(window, c1);
                        if (!window.containsKey(c1) || window.get(c1) < tMap.get(c1)) {
                            relax(windowStart, windowEnd);
                            foundCount--;
                            windowStart++;
                            break;
                        }
                    }
                }
            }
        }

        if (bestStart == -1) {
            return "";
        } else {
            return new String(s, bestStart, bestLen);
        }
    }

    private static void init(String S, String T) {
        s = S.toCharArray();
        t = T.toCharArray();

        tMap = new HashMap<>(t.length);
        for (char c : t) {
            addToMap(tMap, c);
        }
        window = new HashMap<>(tMap.size());

        bestStart = -1;
        bestLen = Integer.MAX_VALUE;
        windowStart = 0;
        windowEnd = 0;
        foundCount = 0;
    }

    private static void addToMap(Map<Character, Integer> map, char c) {
        Integer curCount = map.get(c);
        if (curCount == null) {
            map.put(c, 1);
        } else {
            map.put(c, curCount + 1);
        }
    }

    private static void removeFromMap(Map<Character, Integer> map, char c) {
        int count = map.get(c);
        if (count == 1) {
            map.remove(c);
        } else {
            map.put(c, count - 1);
        }
    }

    private static void relax(int start, int end) {
        int len = end - start + 1;
        if (len < bestLen) {
            bestStart = start;
            bestLen = len;
        }
    }
}
