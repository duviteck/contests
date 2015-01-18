package facebook_hacker_cup.year_2015.round_1;

import java.io.*;
import java.util.Arrays;

/**
 * Created by duviteck. 18 Jan 2015.
 *
 * Since you crave state-of-the-art technology, you've just purchased a phone with a great new feature: autocomplete!
 * Your phone's version of autocomplete has some pros and cons. On the one hand, it's very cautious.
 * It only autocompletes a word when it knows exactly what you're trying to write. On the other hand,
 * you have to teach it every word you want to use.
 *
 * You have N distinct words that you'd like to send in a text message in order. Before sending each word,
 * you add it to your phone's dictionary. Then, you write the smallest non-empty prefix of the word
 * necessary for your phone to autocomplete the word. This prefix must either be the whole word, or a prefix
 * which is not a prefix of any other word yet in the dictionary.
 *
 * What's the minimum number of letters you must type to send all N words?
 *
 * Input
 * Input begins with an integer T, the number of test cases. For each test case, there is first a line
 * containing the integer N. Then, N lines follow, each containing a word to send in the order you wish to send them.
 *
 * Output
 * For the ith test case, print a line containing "Case #i: " followed by the minimum number of characters
 * you need to type in your text message.
 *
 * Constraints
 * 1 ≤ T ≤ 100
 * 1 ≤ N ≤ 100,000
 * The N words will have a total length of no more than 1,000,000 characters.
 * The words are made up of only lower-case alphabetic characters.
 * The words are pairwise distinct.
 */
public class Problem_B_Autocomplete {

    private static final String FILE_IN = "in.txt";
    private static final String FILE_OUT = "out.txt";

    private static final TrieNode root = new TrieNode();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_IN));
        PrintWriter writer = new PrintWriter(new File(FILE_OUT));

        int tests = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= tests; i++) {
            clearTrie();

            int n = Integer.parseInt(reader.readLine());
            int ans = 0;
            for (int j = 0; j < n; j++) {
                String word = reader.readLine();
                ans += findInTrie(word);
            }
            writer.format("Case #%d: %d\n", i, ans);
        }
        writer.flush();
    }

    private static void clearTrie() {
        Arrays.fill(root.next, null);
    }

    private static int findInTrie(String word) {
        int wordLen = word.length();
        int i = 0;
        TrieNode node = root;

        while (i < wordLen) {
            int nextNodeIndex = word.charAt(i) - 'a';
            if (node.next[nextNodeIndex] != null) {
                node = node.next[nextNodeIndex];
                i++;
            } else {
                addToTrie(node, word, i);
                return i + 1;
            }
        }
        return wordLen;
    }

    private static void addToTrie(TrieNode node, String word, int index) {
        int wordLen = word.length();
        int i = index;
        TrieNode curNode = node;

        while (i < wordLen) {
            int nextNodeIndex = word.charAt(i) - 'a';
            TrieNode newNode = new TrieNode();
            curNode.next[nextNodeIndex] = newNode;
            curNode = newNode;
            i++;
        }
    }


    private static class TrieNode {
        TrieNode[] next = new TrieNode[26];
    }
}
