import java.util.ArrayList;

public class SumBruteForce {

    public static void main(String[] args) {
        int[] arr = new int[] {1, 3, 2, 1, 4, 3};
        printCombinations(arr, 5);
    }

    private static void printCombinations(int[] arr, int sum) {
        boolean[] usageFlags = new boolean[arr.length];     // true - used, false - not used yet
        ArrayList<Integer> curCombination = new ArrayList<>(arr.length);

        for (int i = 0; i < arr.length; i++) {
            if (!usageFlags[i] && arr[i] <= sum) {
                boolean isSuccess = bruteForce(arr, usageFlags, i, sum, curCombination);
                if (isSuccess) {
                    printCombo(curCombination);
                    curCombination.clear();
                }
            }
        }
    }

    private static void printCombo(ArrayList<Integer> curCombination) {
        if (!curCombination.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i : curCombination) {
                sb.append(i).append(" ");
            }
            System.out.println(sb.toString());
        }
    }

    private static boolean bruteForce(int[] arr, boolean[] usageFlags, int startIndex, int sum, ArrayList<Integer> curCombination) {
        if (sum == 0) {
            return true;
        }

        for (int i = startIndex; i < arr.length; i++) {
            if (!usageFlags[i] && arr[i] <= sum) {   // element still not used => try to use
                curCombination.add(arr[i]);
                usageFlags[i] = true;
                boolean isSuccess = bruteForce(arr, usageFlags, i + 1, sum - arr[i], curCombination);
                if (isSuccess) {
                    return true;
                } else {
                    curCombination.remove(curCombination.size() - 1);   // remove last elem, equal to current arr[i]
                    usageFlags[i] = false;
                }
            }
        }

        return false;
    }
}
