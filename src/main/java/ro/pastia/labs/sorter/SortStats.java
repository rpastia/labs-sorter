package ro.pastia.labs.sorter;

public class SortStats implements SortCallback {

  private long startTime;

  private long endTime;

  private long numComparisons;


  @Override
  public void handleStart() {
    numComparisons = 0;
    startTime = System.currentTimeMillis();
  }

  @Override
  public void handleEnd() {
    endTime = System.currentTimeMillis();
  }

  @Override
  public void handleComparison() {
    numComparisons++;
  }

  public long getNumComparisons() {
    return numComparisons;
  }

  public int getStartUnixTime(){
    return (int)(startTime/1000);
  }

  public long getDuration(){
    return endTime - startTime;
  }

}
