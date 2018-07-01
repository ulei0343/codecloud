package club.codecloud.base.constant;

import java.util.regex.Pattern;

/**
 * 正则表达式
 */
public class RegexConsts {

    /**
     * 正则：手机号（简单）, 1字头＋10位数字即可.
     */
    public static final Pattern PATTERN_REGEX_MOBILE_SIMPLE = Pattern.compile("^[1]\\d{10}$");

    /**
     * 正则：手机号（精确）, 已知3位前缀＋8位数字
     * <p>
     * 移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
     * </p>
     * <p>
     * 联通：130、131、132、145、155、156、166、171、175、176、185、186
     * </p>
     * <p>
     * 电信：133、153、173、177、180、181、189、199
     * </p>
     * <p>
     * 全球星：1349
     * </p>
     * <p>
     * 虚拟运营商：170
     * </p>
     */
    public static final Pattern PATTERN_REGEX_MOBILE_EXACT = Pattern.compile("^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|(18[0-9])|(19[8,9]))\\d{8}$");

    /**
     * 正则：固定电话号码，可带区号，然后6至少8位数字
     */
    public static final Pattern PATTERN_REGEX_TEL = Pattern.compile("^(\\d{3,4}-)?\\d{6,8}$");

    /**
     * 正则：身份证号码18位, 数字且关于生日的部分必须正确
     */
    public static final Pattern PATTERN_REGEX_ID_CARD = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$");

    /**
     * 正则：邮箱, 有效字符(不支持中文), 且中间必须有@，后半部分必须有.
     */
    public static final Pattern PATTERN_REGEX_EMAIL = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");

    /**
     * 正则：URL, 必须有"://",前面必须是英文，后面不能有空格
     */
    public static final Pattern PATTERN_REGEX_URL = Pattern.compile("[a-zA-z]+://[^\\s]*");

    /**
     * 正则：yyyy-MM-dd格式的日期校验，已考虑平闰年
     */
    public static final Pattern PATTERN_REGEX_DATE = Pattern.compile("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$");

    /**
     * 正则：IP地址
     */
    public static final Pattern PATTERN_REGEX_IP = Pattern.compile("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");

    /**
     * 正则：中文
     */
    public static final Pattern PATTERN_REGEX_ZH = Pattern.compile("^[\\u4e00-\\u9fa5]+$");

}
