package club.codecloud.base.util.text;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 字符集工具类
 * 尽量使用Charsets.UTF8而不是"UTF-8"，减少JDK里的Charset查找消耗.
 * 使用JDK7的StandardCharsets，同时留了标准名称的字符串
 *
 * @author ulei
 */
public class CharsetUtils {

    /**
     * ISO-8859-1
     */
    public static final String ISO_8859_1 = StandardCharsets.ISO_8859_1.name();
    /**
     * UTF-8
     */
    public static final String UTF_8 = StandardCharsets.UTF_8.name();

    /**
     * ISO-8859-1
     */
    public static final Charset CHARSET_ISO_8859_1 = StandardCharsets.ISO_8859_1;
    /**
     * UTF-8
     */
    public static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;

    private CharsetUtils() {
        // 静态类不可实例化
    }

    /**
     * 转换为Charset对象
     *
     * @param charset 字符集，为空则返回默认字符集
     * @return Charset
     */
    public static Charset charset(String charset) {
        return StringUtils.isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset);
    }

    /**
     * 转换字符串的字符集编码
     *
     * @param source      字符串
     * @param srcCharset  源字符集，默认ISO-8859-1
     * @param destCharset 目标字符集，默认UTF-8
     * @return 转换后的字符集
     */
    public static String convert(String source, String srcCharset, String destCharset) {
        return convert(source, Charset.forName(srcCharset), Charset.forName(destCharset));
    }

    /**
     * 转换字符串的字符集编码
     *
     * @param source      字符串
     * @param srcCharset  源字符集，默认ISO-8859-1
     * @param destCharset 目标字符集，默认UTF-8
     * @return 转换后的字符集
     */
    public static String convert(String source, Charset srcCharset, Charset destCharset) {
        if (null == srcCharset) {
            srcCharset = CHARSET_ISO_8859_1;
        }
        if (null == destCharset) {
            srcCharset = CHARSET_UTF_8;
        }
        if (StringUtils.isBlank(source) || srcCharset.equals(destCharset)) {
            return source;
        }
        return new String(source.getBytes(srcCharset), destCharset);
    }

    /**
     * @return 系统字符集编码
     */
    public static String systemCharset() {
        return Charset.defaultCharset().name();
    }

}
