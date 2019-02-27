package algorithm;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;

/**
 * 超大数计算
 * 一共有三张业务场景：
 * 1、两个正整数相加，执行加法运算
 * 2、一整一负相加，执行减法运算
 * 3、两负相加，先执行加法运算，后添加负号
 * <p>
 * 计算步骤
 * 1、处理符号
 * 2、反转字符串，缺位算0，
 * 3、对应位进行计算
 * 4、进位处理
 *
 * @author ulei
 * @date 2018/12/21
 */
public class BigNumberCal {


    private static final char ZERO_CHAR = '0';
    private static final String ZERO = "0";

    public static String add(String a, String b) {

        char[] aChars = new StringBuffer(a).reverse().toString().toCharArray();
        char[] bChars = new StringBuffer(b).reverse().toString().toCharArray();

        int aLength = aChars.length;
        int bLength = bChars.length;

        int maxLength = NumberUtils.max(a.length(), b.length());
        int[] result = new int[maxLength + 1];
        for (int i = 0; i < maxLength; i++) {
            int aValue = i > aLength - 1 ? 0 : aChars[i] - ZERO_CHAR;
            int bValue = i > bLength - 1 ? 0 : bChars[i] - ZERO_CHAR;
            result[i] = aValue + bValue;
        }
        System.out.println(Arrays.toString(result));

        for (int i = 0; i < result.length; i++) {
            if (result[i] >= 10) {
                result[i + 1] += result[i] / 10;
                result[i] %= 10;
            }
        }

        System.out.println(Arrays.toString(result));

        StringBuffer sb = new StringBuffer(result.length);
        for (int i = result.length - 1; i >= 0; i--) {
            sb.append(result[i]);
        }
        String data = sb.toString();
        System.out.println(data);

        data = StringUtils.startsWith(data, ZERO) ? StringUtils.substringAfter(data, ZERO) : data;
        System.out.println(data);

        return null;
    }


    public static void main(String[] args) {
//        add("456", "789");
        add("123", "456");
        boolean b = StringUtils.startsWith("0123", "0");
        System.out.println(b);
    }

}
