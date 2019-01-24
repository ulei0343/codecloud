package sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author ulei
 * @date 2018/11/12
 */
public class BubbleSort extends AbstractSort {

    public static void main(String[] args) {
        Integer[] arr = {1, 4, 2, 7, 9, 8, 3, 6};
        new BubbleSort().sort(arr);
        System.out.println("结果：" + Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    @Override
    public void sort(Comparable[] arr) {
        /*for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;//设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已然完成。
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }*/
    }
}
