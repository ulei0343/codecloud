package club.codecloud.base.common.dao;

import java.io.Serializable;

public interface BaseDao<T> {

    int insert(T t);

    int deleteById(Serializable id);

    int updateById(T t);

    T selectById(Serializable id);

}
