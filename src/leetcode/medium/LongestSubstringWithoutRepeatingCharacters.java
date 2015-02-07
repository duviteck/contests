package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by duviteck. 07 Feb 2015.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();
        Map<Character, Integer> indexes = new HashMap<>(chars.length);

        int bestLen = -1;
        int start = 0;
        indexes.put(chars[0], 0);

        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (!indexes.containsKey(c)) {
                indexes.put(c, i);
            } else {
                if (indexes.size() > bestLen) {
                    bestLen = indexes.size();
                }

                int newStart = indexes.get(c) + 1;
                for (int j = start; j < newStart; j++) {
                    indexes.remove(chars[j]);
                }

                start = newStart;
                indexes.put(c, i);
            }
        }

        return Math.max(bestLen, indexes.size());
    }
}
