package google_code_jam.year_2014;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class QualificationRound_ProblemA {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("A.in"));
        PrintWriter writer = new PrintWriter(new File("a.out"));

        int testCount = s.nextInt();
        s.nextLine();

        for (int testNum = 1; testNum <= testCount; testNum++) {
            int r1 = s.nextInt();
            s.nextLine();
            Set<Integer> set1 = null;

            for (int i = 1; i <= 4; i++) {
                String line = s.nextLine();
                if (i == r1) {
                    set1 = parseSet(line);
                }
            }

            int r2 = s.nextInt();
            s.nextLine();
            Set<Integer> set2 = null;

            for (int i = 1; i <= 4; i++) {
                String line = s.nextLine();
                if (i == r2) {
                    set2 = parseSet(line);
                }
            }

            set1.retainAll(set2);
            if (set1.size() == 1) {
                writer.format("Case #%d: %d\n", testNum, set1.iterator().next());
            } else if (set1.size() > 1) {
                writer.format("Case #%d: Bad magician!\n", testNum);
            } else {
                writer.format("Case #%d: Volunteer cheated!\n", testNum);
            }
        }

        writer.close();
        s.close();
    }

    private static Set<Integer> parseSet(String line) {
        Set<Integer> result = new HashSet<Integer>(4);
        String[] parts = line.split(" ");
        for (String part : parts) {
            result.add(Integer.parseInt(part));
        }
        return result;
    }
}
