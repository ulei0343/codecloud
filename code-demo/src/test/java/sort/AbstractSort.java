package sort;

/**
 * @author ulei
 * @date 2018/11/12
 */
public abstract class AbstractSort implements Sort {

    @Override
    public void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }
}
