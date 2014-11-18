package others.selection;

/**
 * Created by duviteck. 18 Nov 2014.
 */
public interface ISelect {
    /**
     * Return the k-th smallest number in the specified array
     */
    int select(int[] ar, int k) throws IllegalArgumentException;

    /**
     * Return name of the selection algorithm
     */
    String getName();
}
