package club.codecloud.base.constant;

import club.codecloud.base.TimeConsts;

public enum CacheKey {

    USER_ALL("user:all", TimeConsts.REDIS_EXPIRE_ONE_MINUTE, "用户");

    /**
     * 缓存key
     */
    private String key;

    /**
     * 缓存有效时间,单位秒
     */
    private Integer expire;

    private String desc;

    public String getKey() {
        return key;
    }

    public Integer getExpire() {
        return expire;
    }

    /**
     * 拼接key
     *
     * @param args
     * @return
     */
    public String join(String... args) {
        return String.format(this.getKey(), args);
    }

    CacheKey(String key, Integer expire, String desc) {
        this.key = key;
        this.expire = expire;
        this.desc = desc;
    }
}
