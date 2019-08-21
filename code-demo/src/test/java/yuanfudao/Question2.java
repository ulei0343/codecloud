package yuanfudao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 13
 * abcdefghijklm
 *
 * @author: ulei
 * @date: 2019-08-18
 */
public class Question2 {

    public static void main(String[] args) {
        final String EMPTY = " ";
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        String data = sc.next();
        String[] strChar = data.split("");
        List<String> names = new ArrayList();

        Collections.addAll(names, strChar);
//        for(int i = 0;i< strChar.length;i++){
//            names.add(strChar[i]);
//        }

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
