package sort;

import java.util.Arrays;

/**
 * @author ulei
 * @date 2018/11/12
 */
public abstract class AbstractSort implements Sort {

    @Override
    public void swap(Comparable[] arr, int a, int b) {
        /*arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];*/
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

    }

    @Override
    public boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    @Override
    public void print(Comparable[] a) {
        System.out.println(Arrays.toString(a));
    }
}
