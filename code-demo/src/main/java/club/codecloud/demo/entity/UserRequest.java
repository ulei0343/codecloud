package club.codecloud.demo.entity;

import club.codecloud.base.util.time.DateFormatUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ulei
 * @date 2018/9/17
 */
public class UserRequest {

    private Integer id;
    private String username;
    @DateTimeFormat(pattern = DateFormatUtils.DATE_FORMAT)
    private Date birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
