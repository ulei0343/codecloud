package club.codecloud.base.util.number;

import com.google.common.math.IntMath;
import com.google.common.math.LongMath;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author ulei
 */
public class MathUtils {
    //默认除法运算精度
    private static final int DEFAULT_DIV_SCALE = 10;

    /**
     * 提供精确的加法运算。
     *
     * @param v1
     * @param v2
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1
     * @param v2
     * @return 两个参数数学加和，以字符串格式返回
     */
    public static String add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).toString();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1
     * @param v2
     * @return 两个参数的差
     */
    public static double subtract(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1
     * @param v2
     * @return 两个参数数学差，以字符串格式返回
     */
    public static String subtract(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).toString();
    }


    /**
     * 提供精确的乘法运算。
     *
     * @param v1
     * @param v2
     * @return 两个参数的积
     */
    public static double multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1
     * @param v2
     * @return 两个参数的数学积，以字符串格式返回
     */
    public static String multiply(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).toString();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
     *
     * @param v1
     * @param v2
     * @return 两个参数的商
     */
    public static double divide(double v1, double v2) {
        return divide(v1, v2, DEFAULT_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
     *
     * @param v1
     * @param v2
     * @param scale 表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double divide(double v1, double v2, int scale) {
        return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
     *
     * @param v1
     * @param v2
     * @param scale      表示需要精确到小数点以后几位
     * @param round_mode 表示用户指定的舍入模式
     * @return 两个参数的商
     */
    public static double divide(double v1, double v2, int scale, int round_mode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, round_mode).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
     *
     * @param v1
     * @param v2
     * @return 两个参数的商，以字符串格式返回
     */
    public static String divide(String v1, String v2) {
        return divide(v1, v2, DEFAULT_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
     *
     * @param v1
     * @param v2
     * @param scale 表示需要精确到小数点以后几位
     * @return 两个参数的商，以字符串格式返回
     */
    public static String divide(String v1, String v2, int scale) {
        return divide(v1, v2, DEFAULT_DIV_SCALE, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
     *
     * @param v1
     * @param v2
     * @param scale      表示需要精确到小数点以后几位
     * @param round_mode 表示用户指定的舍入模式
     * @return 两个参数的商，以字符串格式返回
     */
    public static String divide(String v1, String v2, int scale, int round_mode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, round_mode).toString();
    }

    /**
     * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v          需要四舍五入的数字
     * @param scale      小数点后保留几位
     * @param round_mode 指定的舍入模式
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale, int round_mode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(scale, round_mode).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果，以字符串格式返回
     */
    public static String round(String v, int scale) {
        return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v          需要四舍五入的数字
     * @param scale      小数点后保留几位
     * @param round_mode 指定的舍入模式
     * @return 四舍五入后的结果，以字符串格式返回
     */
    public static String round(String v, int scale, int round_mode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, round_mode).toString();
    }

    /////// 2 的倍数的计算////

    /**
     * 往上找出最接近的2的倍数，比如15返回16， 17返回32.
     *
     * @param value 必须为正数，否则抛出异常.
     */
    public static int nextPowerOfTwo(int value) {
        return IntMath.ceilingPowerOfTwo(value);
    }

    /**
     * 往上找出最接近的2的倍数，比如15返回16， 17返回32.
     *
     * @param value 必须为正数，否则抛出异常.
     */
    public static long nextPowerOfTwo(long value) {
        return LongMath.ceilingPowerOfTwo(value);
    }

    /**
     * 往下找出最接近2的倍数，比如15返回8， 17返回16.
     *
     * @param value 必须为正数，否则抛出异常.
     */
    public static int previousPowerOfTwo(int value) {
        return IntMath.floorPowerOfTwo(value);
    }

    /**
     * 往下找出最接近2的倍数，比如15返回8， 17返回16.
     *
     * @param value 必须为正数，否则抛出异常.
     */
    public static long previousPowerOfTwo(long value) {
        return LongMath.floorPowerOfTwo(value);
    }

    /**
     * 是否2的倍数
     *
     * @param value 不是正数时总是返回false
     */
    public static boolean isPowerOfTwo(int value) {
        return IntMath.isPowerOfTwo(value);
    }

    /**
     * 是否2的倍数
     *
     * @param value <=0 时总是返回false
     */
    public static boolean isPowerOfTwo(long value) {
        return LongMath.isPowerOfTwo(value);
    }

    /**
     * 当模为2的倍数时，用比取模块更快的方式计算.
     *
     * @param value 可以为负数，比如 －1 mod 16 = 15
     */
    public static int modByPowerOfTwo(int value, int mod) {
        return value & (mod - 1);
    }

    ////////////// 其他函数//////////

    /**
     * 保证结果为正数的取模.
     *
     * 如果(v = x/m) <0，v+=m.
     */
    public static int mod(int x, int m) {
        return IntMath.mod(x, m);
    }

    /**
     * 保证结果为正数的取模.
     *
     * 如果(v = x/m) <0，v+=m.
     */
    public static long mod(long x, long m) {
        return LongMath.mod(x, m);
    }

    /**
     * 保证结果为正数的取模
     */
    public static int mod(long x, int m) {
        return LongMath.mod(x, m);
    }

    /**
     * 能控制rounding方向的int相除.
     *
     * jdk的'/'运算符，直接向下取整
     */
    public static int divide(int p, int q, RoundingMode mode) {
        return IntMath.divide(p, q, mode);
    }

    /**
     * 能控制rounding方向的long相除
     *
     * jdk的'/'运算符，直接向下取整
     */
    public static long divide(long p, long q, RoundingMode mode) {
        return LongMath.divide(p, q, mode);
    }

    /**
     * 平方
     *
     * @param k 平方次数, 不能为负数, k=0时返回1.
     */
    public static int pow(int b, int k) {
        return IntMath.pow(b, k);
    }

    /**
     * 平方
     *
     * @param k 平方次数,不能为负数, k=0时返回1.
     */
    public static long pow(long b, int k) {
        return LongMath.pow(b, k);
    }

    /**
     * 开方
     */
    public static int sqrt(int x, RoundingMode mode) {
        return IntMath.sqrt(x, mode);
    }

    /**
     * 开方
     */
    public static long sqrt(long x, RoundingMode mode) {
        return LongMath.sqrt(x, mode);
    }
}

