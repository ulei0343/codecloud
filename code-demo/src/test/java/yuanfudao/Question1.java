package yuanfudao;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Scanner;

/**
 * @author: ulei
 * @date: 2019-08-18
 */
public class Question1 {
    public static void main(String[] args) {
        Scanner sr = new Scanner(System.in);
        int studentSize = sr.nextInt();
        int carSize = sr.nextInt();
//        int carSize = 3;
        int[] student = new int[studentSize];
        for (int i = 0; i < studentSize; i++) {
            student[i] = sr.nextInt();
        }
//        int[] student = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        int mod = student.length % carSize;
        int carNumber = mod == 0 ? student.length / carSize : student.length / carSize + 1;
        System.out.println("carNumber:" + carNumber);

        List<Integer> studentSort = Lists.newArrayList();
        // 如果存在不满的情况
        if (mod != 0) {
            for (int i = 0; i < mod; i++) {
                studentSort.add(student[(carNumber - 1) * carSize + i]);
            }
        }
        int car = mod != 0 ? carNumber - 1 : carNumber;
        for (int i = car; i > 0; i--) {
            for (int j = 0; j < carSize; j++) {
                studentSort.add(student[(i - 1) * carSize + j]);
            }
        }
        System.out.println(studentSort);
    }

}
