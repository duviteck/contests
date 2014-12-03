package leetcode.medium;

/**
 * Created by duviteck. 04 Dec 2014.
 */
public class RotateImage {
    public void rotate(int[][] m) {
        if (m == null || m.length <= 1) {
            return;
        }

        int n = m.length;
        for (int i = 0; i < n/2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int temp = m[i][j];
                m[i][j] = m[n - 1 - j][i];  // left down
                m[n - 1 - j][i] = m[n - 1 - i][n - 1 - j];  // right down
                m[n - 1 - i][n - 1 - j] = m[j][n - 1 - i];  // right top
                m[j][n - 1 - i] = temp;
            }
        }
    }
}
