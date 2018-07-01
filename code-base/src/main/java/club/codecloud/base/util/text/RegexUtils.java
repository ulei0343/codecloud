package club.codecloud.base.util.text;

import club.codecloud.base.constant.RegexConsts;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.regex.Pattern;

/**
 * 通过正则表达判断是否正确的URL， 邮箱，手机号，固定电话，身份证，邮箱等.
 * <p>
 * 从AndroidUtilCode的RegexUtils移植, 性能优化将正则表达式为预编译, 并修改了TEL的正则表达式.
 * <p>
 * https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/src/main/java/com/blankj/utilcode/util/RegexUtils.java
 * https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/src/main/java/com/blankj/utilcode/constant/RegexConstants.java
 */
public class RegexUtils {


    /**
     * 验证手机号（简单）
     */
    public static boolean isMobileSimple(@Nullable CharSequence input) {
        return isMatch(RegexConsts.PATTERN_REGEX_MOBILE_SIMPLE, input);
    }

    /**
     * 验证手机号（精确）
     */
    public static boolean isMobileExact(@Nullable CharSequence input) {
        return isMatch(RegexConsts.PATTERN_REGEX_MOBILE_EXACT, input);
    }

    /**
     * 验证固定电话号码
     */
    public static boolean isTel(@Nullable CharSequence input) {
        return isMatch(RegexConsts.PATTERN_REGEX_TEL, input);
    }

    /**
     * 验证15或18位身份证号码
     */
    public static boolean isIdCard(@Nullable CharSequence input) {
        return isMatch(RegexConsts.PATTERN_REGEX_ID_CARD, input);
    }

    /**
     * 验证邮箱
     */
    public static boolean isEmail(@Nullable CharSequence input) {
        return isMatch(RegexConsts.PATTERN_REGEX_EMAIL, input);
    }

    /**
     * 验证URL
     */
    public static boolean isUrl(@Nullable CharSequence input) {
        return isMatch(RegexConsts.PATTERN_REGEX_URL, input);
    }

    /**
     * 验证yyyy-MM-dd格式的日期校验，已考虑平闰年
     */
    public static boolean isDate(@Nullable CharSequence input) {
        return isMatch(RegexConsts.PATTERN_REGEX_DATE, input);
    }

    /**
     * 验证IP地址
     */
    public static boolean isIp(@Nullable CharSequence input) {
        return isMatch(RegexConsts.PATTERN_REGEX_IP, input);
    }

    /**
     * 验证中文
     */
    public static boolean isZh(@Nullable CharSequence input) {
        return isMatch(RegexConsts.PATTERN_REGEX_ZH, input);
    }

    public static boolean isMatch(Pattern pattern, CharSequence input) {
        return StringUtils.isNotEmpty(input) && pattern.matcher(input).matches();
    }
}
