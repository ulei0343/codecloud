package club.codecloud.demo.entity;

import club.codecloud.base.util.time.DateFormatUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author ulei
 * @date 2018/9/17
 */
public class UserResponse {

    private String id;
    private String username;
    @JsonFormat(pattern = DateFormatUtils.DATE_FORMAT_CN)
    private Date birthday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
