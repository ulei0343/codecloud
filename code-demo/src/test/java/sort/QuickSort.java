package sort;

import java.util.Arrays;

/**
 * 快速排序算法
 * 分而治之思想
 *
 * @author: ulei
 * @date: 2019-06-20
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        System.out.println(Arrays.toString(arr));
        new QuickSort().sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }


    private void sort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        int i, j, temp, t;
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];
        while (i < j) {
            while (temp <= arr[j] && i < j) {
                j--;
            }
            while (temp >= arr[i] && i < j) {
                i++;
            }
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        // 最后将基准为与low和high相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        System.out.println(Arrays.toString(arr));
        //递归调用左半数组
        sort(arr, low, j - 1);
        //递归调用右半数组
        sort(arr, j + 1, high);
    }
}
