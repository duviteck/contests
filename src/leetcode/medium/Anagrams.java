package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by duviteck. 05 Feb 2015.
 */
public class Anagrams {

    public List<String> anagrams(String[] strs) {
        Map<LetterSet, List<String>> map = new HashMap<>(strs.length);
        for (String line : strs) {
            LetterSet letters = new LetterSet(line);
            List<String> list = map.get(letters);
            if (list == null) {
                list = new ArrayList<>();
                list.add(line);
                map.put(letters, list);
            } else {
                list.add(line);
            }
        }

        List<String> ans = new ArrayList<>(strs.length);
        for (List<String> list : map.values()) {
            if (list.size() > 1) {
                ans.addAll(list);
            }
        }
        return ans;
    }


    private static class LetterSet {
        int[] letters = new int[26];
        int hashCode;

        public LetterSet(String line) {
            for (char c : line.toCharArray()) {
                int i = c - 'a';
                letters[i]++;
            }

            hashCode = 0;
            for (int i = 0; i < 26; i++) {
                hashCode += letters[i] * (i + 1);
            }
        }

        @Override
        public int hashCode() {
            return hashCode;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof LetterSet) {
                LetterSet set = (LetterSet) obj;
                boolean equal = true;
                for (int i = 0; i < 26; i++) {
                    equal &= (letters[i] == set.letters[i]);
                }
                return equal;
            }
            return false;
        }
    }
}
