import club.codecloud.base.constant.RegexConsts;
import club.codecloud.base.util.text.RegexUtils;
import com.google.common.collect.Lists;

import java.util.List;

public class ObjectUtilsTest {

    public static void main(String[] args) {
        System.out.println(RegexUtils.isEmail(null));
        System.out.println(RegexUtils.isMatch(RegexConsts.PATTERN_REGEX_EMAIL,null));
    }
}
