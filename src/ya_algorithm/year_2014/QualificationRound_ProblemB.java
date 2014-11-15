package ya_algorithm.year_2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class QualificationRound_ProblemB {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int pow = (int)(Math.log(n) / Math.log(2));

        Map<String, Integer> map = new HashMap<String, Integer>(n / 2);
        for (int i = 0; i < n; i++) {
            String winner = reader.readLine().split(" ")[0];
            Integer val = map.get(winner);
            if (val == null) {
                val = 0;
            }
            val++;
            map.put(winner, val);
            if (val == pow) {
                System.out.println(winner);
                break;
            }
        }

        reader.close();
    }
}
