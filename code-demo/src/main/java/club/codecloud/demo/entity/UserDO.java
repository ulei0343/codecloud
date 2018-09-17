package club.codecloud.demo.entity;

import club.codecloud.base.common.entity.BaseDO;

import java.util.Date;

/**
 * @author ulei
 * @date 2018/7/26
 */

public class UserDO extends BaseDO {
    private String username;
    private Date birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
