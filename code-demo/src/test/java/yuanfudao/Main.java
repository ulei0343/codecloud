package yuanfudao;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Scanner;

/**
 * @author: ulei
 * @date: 2019-08-18
 */
public class Main {

    public static void main(String[] args) {
        final String EMPTY = " ";
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        String data = sc.next();
        List<String> names = Lists.newArrayList(data.split(""));

        int high = (size - 1) / 3;
        int index = 0;

        // 上层
        for (int i = 0; i < high; i++) {
            for (int k = 0; k < i; k++) {
                System.out.print(EMPTY);
            }
            System.out.print(names.get(index));
            index++;
            int spaceNum = 2 * (high - i) - 1;
            for (int j = 0; j < spaceNum; j++) {
                System.out.print(EMPTY);
            }
            System.out.println(names.get(index));
            index++;
        }

        //中下层
        for (int i = 0; i < high + 1; i++) {
            for (int k = 0; k < high; k++) {
                System.out.print(EMPTY);
            }
            System.out.println(names.get(index));
            index++;
        }
    }
}
