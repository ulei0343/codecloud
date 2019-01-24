package sort;


import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author ulei
 * @date 2018/11/12
 */
public class ShellSort extends AbstractSort {
    public static void main(String[] args) {
        Integer[] arr = {1, 4, 2, 7, 9, 8, 3, 6};
        new ShellSort().sort(arr);
        System.out.println("结果：" + Arrays.toString(arr));
//        int []arr1 ={1,4,2,7,9,8,3,6};
//        sort1(arr1);
//        System.out.println(Arrays.toString(arr1));
    }

    /**
     * 希尔排序
     *
     * @param arr
     */
    public void sort(Comparable[] arr) {
        int length = arr.length;
        int gap = 1;
        while (gap < length / 3) {
            gap = 3 * gap + 1;

        }
        while (gap >= 1) {
            for (int i = gap; i < length; i++) {
                for (int j = i; j >= gap && less(arr[j], arr[j - gap]); j -= gap) {
                    swap(arr, j, j - gap);
                }
            }
            gap = gap / 3;
        }
    }

    /**
     * 希尔排序 针对有序序列在插入时采用移动法。
     *
     * @param arr
     */
    public void sort1(int[] arr) {
        //增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动法
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }

    public void sort2(Comparable[] arr) {
        //增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j - gap >= 0 && less(arr[j], arr[j - gap])) {
                    //插入排序采用交换法
                    swap(arr, j, j - gap);
                    j -= gap;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }


}
