package cscenter.year_2013_2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Advanced_Problem_5E {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = new ArrayList<String>(100);

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.length() == 0) {
                break;
            }
            lines.add(line);
        }

        Collections.sort(lines, new MyComparator());

        StringBuilder sb = new StringBuilder(20000);
        for (String l : lines) {
            sb.append(l);
        }
        System.out.println(sb.toString());
    }


    private static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            String ss1 = s1 + s2;
            String ss2 = s2 + s1;
            return ss1.compareTo(ss2) * (-1);
        }
    }
}
