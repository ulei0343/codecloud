package sort;

import java.util.Arrays;

/**
 * 简单选择排序
 *
 * @author ulei
 * @date 2018/11/12
 */
public class SelectionSort extends AbstractSort {

    public static void main(String[] args) {
        Integer[] arr = {1, 4, 2, 7, 9, 8, 3, 6};
        new SelectionSort().sort(arr);
        System.out.println("结果：" + Arrays.toString(arr));
    }


    /**
     * 简单选择排序
     *
     * @param arr
     */
    @Override
    public void sort(Comparable[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            // 每一趟循环比较时，min用于存放较小元素的数组下标，
            // 这样当前批次比较完毕最终存放的就是此趟内最小的元素的下标，
            // 避免每次遇到较小元素都要进行交换。
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (less(arr[j], arr[min])) {
                    min = j;
                }
            }
            //进行交换，如果min发生变化，则进行交换
            if (min != i) {
                swap(arr, min, i);
            }
        }
    }
}
