package club.codecloud.demo.entity;

import java.io.Serializable;

/**
 * @author ulei
 * @date 2018/7/26
 */
public class UserDO implements Serializable {
    private String id;
    private String username;

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
}
