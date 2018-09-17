package club.codecloud.base.common.entity;

import java.io.Serializable;

/**
 * @author ulei
 * @date 2018/4/12
 */
public class BaseDO implements Serializable {
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
