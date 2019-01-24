package sort;

public interface Sort {

    void sort(Comparable[] arr);

    /**
     * a、b比较
     * a<b,true
     * a>=b,false
     *
     * @param a
     * @param b
     * @return
     */
    boolean less(Comparable a, Comparable b);

    /**
     * 交换数组元素
     *
     * @param arr
     * @param a
     * @param b
     */
    void swap(Comparable[] arr, int a, int b);

    void print(Comparable[] a);
}
