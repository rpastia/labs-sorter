package ro.pastia.labs.sorter;

/**
 * An object that is able to handle the callbacks of the Sorter class
 *
 * @see ro.pastia.labs.sorter.Sorter
 */
public interface SortCallback {

    /**
     * Called by the Sorter object when sorting begins
     */
    public void handleStart();

    /**
     * Called by the Sorter object when sorting is complete
     */
    public void handleEnd();

    /**
     * Called by the Sorter object each time a comparison is performed
     */
    public void handleComparison();
}
