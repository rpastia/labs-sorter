package ro.pastia.labs.sorter;

public class Sorter {

  private SortCallback callbackHandler = null;

  public Sorter(SortCallback callbackHandler) {
    this.callbackHandler = callbackHandler;
  }

  public void setCallbackHandler(SortCallback callbackHandler) {
    this.callbackHandler = callbackHandler;
  }

  public void doQuicksort(Comparable[] array, int low, int high) {
    if(callbackHandler!=null){
      callbackHandler.handleStart();
    }
    quicksort(array, low, high);
    if(callbackHandler!=null){
      callbackHandler.handleEnd();
    }
  }

  public void doMergesort(Comparable[] array, int low, int high) {
    if(callbackHandler!=null){
      callbackHandler.handleStart();
    }
    mergesort(array, low, high);
    if(callbackHandler!=null){
      callbackHandler.handleEnd();
    }
  }

  private void quicksort(Comparable[] array, int low, int high) {
    if(low < high) {
      int pivot_location = qs_partition(array,low,high);
      quicksort(array,low,pivot_location - 1);
      quicksort(array,pivot_location + 1, high);
    }
  }

  @SuppressWarnings("unchecked")
  private int qs_partition(Comparable[] array, int low, int high) {
    Comparable pivot = array[low];
    int leftwall = low;
    for(int i=low+1;i<=high;i++){
      //We are about to do a comparison, update the counter
      if(callbackHandler!=null){
        callbackHandler.handleComparison();
      }
      if(array[i].compareTo(pivot) < 0) {
        leftwall++;
        swap(array,i,leftwall);
      }
    }

    swap(array,low,leftwall);
    return leftwall;
  }

  private static void swap(Comparable[] array, int i1, int i2) {
    Comparable t = array[i1];
    array[i1] = array[i2];
    array[i2] = t;
  }

  private void mergesort (Comparable[] array, int low, int high) {
    if(low<high) {
      int middle = (low+high)/2;
      mergesort(array,low,middle);
      mergesort(array,middle+1,high);
      merge(array, low, middle, high);
    }

  }

  @SuppressWarnings("unchecked")
  private void merge(Comparable[] array, int low, int middle, int high) {
    int i, i1, i2;
    i1 = 0;
    i2 = 0;

    Comparable[] a1 = new Comparable[middle-low+2];
    Comparable[] a2 = new Comparable[high-middle+1];

    for(i=0;i<=(middle-low);i++) {
      a1[i] = array[low+i];
    }
    a1[i] = null; //use null as the largest possible element

    for(i=0;i<(high-middle);i++) {
      a2[i] = array[middle+i+1];
    }
    a2[i] = null; //use null as the largest possible element

    for(i=low;i<=high;i++) {
      //We are about to do a comparison, update the counter
      if(callbackHandler!=null){
        callbackHandler.handleComparison();
      }
      //prevent compareTo call if either one of the elements is null
      if( (a2[i2]==null) || (a1[i1]!=null && a1[i1].compareTo(a2[i2])<0) ) {
        array[i] = a1[i1++];
      } else {
        array[i] = a2[i2++];
      }
    }

  }

}