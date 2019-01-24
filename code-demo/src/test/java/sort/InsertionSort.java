package sort;

import java.util.Arrays;

/**
 * 直接插入排序
 *
 * @author ulei
 * @date 2018/11/12
 */
public class InsertionSort extends AbstractSort {
    public static void main(String[] args) {
        Integer[] arr = {1, 4, 2, 7, 9, 8, 3, 6};
        new InsertionSort().sort(arr);
        System.out.println("结果：" + Arrays.toString(arr));
    }


    /**
     * 插入排序
     *
     * @param arr
     */
    @Override
    public void sort(Comparable[] arr) {
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && less(arr[j], arr[j - 1]); j--) {
                swap(arr, j, j - 1);
            }
        }
    }

}
