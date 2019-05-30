package club.codecloud.demo.entity;

import club.codecloud.base.common.entity.BaseDO;
import club.codecloud.demo.common.constant.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author ulei
 * @date 2018/7/26
 */
@Getter
@Setter
public class UserDO extends BaseDO {
    private String username;
    private Date birthday;
    private UserStatusEnum status;

}
