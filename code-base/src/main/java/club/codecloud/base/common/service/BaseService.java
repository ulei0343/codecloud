package club.codecloud.base.common.service;

import java.io.Serializable;

/**
 * @author ulei
 * @date 2018/4/12
 */
public interface BaseService<T> {
    int insert(T t);

    int deleteById(Serializable id);

    int updateById(T t);

    T selectById(Serializable id);
}
