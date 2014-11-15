package google_code_jam.year_2012;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class QualificationRound_ProblemA implements Runnable {
    private static BufferedReader reader;
    private static StringTokenizer st;
    private static PrintWriter writer;
    private static HashMap<Character, Character> backMap;

    public static void main(String[] args) {
        new Thread(new QualificationRound_ProblemA()).start();
    }

    @Override
    public void run() {
        try {
            solve();
        } catch (IOException e) {}
    }

    private void solve() throws IOException {
        reader = new BufferedReader(new FileReader("a.in"));
        writer = new PrintWriter(new FileWriter("a.out"));

        backMap = new HashMap<Character, Character>(27);
        backMap.put(' ', ' ');
        backMap.put('y', 'a');
        backMap.put('e', 'o');
        backMap.put('q', 'z');
        backMap.put('z', 'q');

        String[] nStrings = new String[3];
        nStrings[0] = "our language is impossible to understand";
        nStrings[1] = "there are twenty six factorial possibilities";
        nStrings[2] = "so it is okay if you want to just give up";
        String[] gStrings = new String[3];
        gStrings[0] = "ejp mysljylc kd kxveddknmc re jsicpdrysi";
        gStrings[1] = "rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd";
        gStrings[2] = "de kr kd eoya kw aej tysr re ujdr lkgc jv";
        for (int i = 0; i < 3; i++) {
            String googleString = gStrings[i];
            String normString = nStrings[i];
            int len = googleString.length();
            for (int j = 0; j < len; j++) {
                Character gC = googleString.charAt(j);
                Character nC = normString.charAt(j);
                if(!backMap.keySet().contains(gC)) {
                    backMap.put(gC, nC);
                }
            }
        }

        int n = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder(110);
        for (int i = 0; i < n; i++) {
            writer.print("Case #" + (i + 1) + ": ");
            String line = reader.readLine();
            int len = line.length();
            for (int j = 0; j < len; j++) {
                sb.append(backMap.get(line.charAt(j)));
            }
            writer.println(sb.toString());
            sb.delete(0, len);
        }

        reader.close();
        writer.close();
    }

    static String nextToken() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(reader.readLine());
        }
        return st.nextToken();
    }

}