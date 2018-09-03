package club.codecloud.base;

/**
 * @author ulei
 * @date 2018/6/12
 */
public class TimeConsts {

    /**
     * 缓存失效时间：60秒
     */
    public static final Integer REDIS_EXPIRE_ONE_MINUTE = 60;

    /**
     * 缓存失效时间：10分钟
     */
    public static final Integer REDIS_EXPIRE_TEN_MINUTE = 10 * REDIS_EXPIRE_ONE_MINUTE;

    /**
     * 缓存失效时间：30分钟
     */
    public static final Integer REDIS_EXPIRE_HALF_HOUR = 30 * REDIS_EXPIRE_ONE_MINUTE;

    /**
     * 缓存失效时间：1小时
     */
    public static final Integer REDIS_EXPIRE_ONE_HOUR = 60 * REDIS_EXPIRE_ONE_MINUTE;

    /**
     * 缓存失效时间：24小时
     */
    public static final Integer REDIS_EXPIRE_ONE_DAY = 24 * REDIS_EXPIRE_ONE_HOUR;
}
