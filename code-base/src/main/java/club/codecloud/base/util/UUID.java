package club.codecloud.base.util;

/**
 * @author ulei
 */
public class UUID {

    private static class SingletonHolder {
        private static final UUID INSTANCE = new UUID();
    }

    public static final UUID getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private UUID() {

    }

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
