package club.codecloud.base.util;


/**
 * @author ulei
 */
public class UUID {

    /**
     * 返回不带"-"的UUID
     *
     * @return
     */
    public static String getUUID() {
        return java.util.UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 返回带"-"的UUID
     *
     * @return
     */
    public static String getSplitUUID() {
        return java.util.UUID.randomUUID().toString();
    }


}
