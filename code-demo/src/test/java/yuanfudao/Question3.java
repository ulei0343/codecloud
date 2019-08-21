package yuanfudao;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * [1, 9, 8, 8] + 34 -> [2, 0, 2, 2]
 *
 * @author: ulei
 * @date: 2019-08-18
 */
public class Question3 {
    public static void main(String[] args) {
        int[] nums = {1, 9, 8, 8};
        int add = 34;

        List<Integer> result = Lists.newArrayList();
        int temp = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int data = 0;
            if (i == nums.length - 1) {
                data = nums[i] + add;
            } else {
                data = nums[i] + temp;
            }
            int mod = data % 10;
            result.add(mod);
            if (data >= 10) {
                temp = data / 10;
            } else {
                temp = 0;
            }
            // 处理被加数的长度大于加数的情况
            if (i == 0 && temp != 0) {
                result.add(temp);
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int i = result.size() - 1; i >= 0; i--) {
            answer.append(result.get(i));
        }
        System.out.println(answer.toString());
    }
}
