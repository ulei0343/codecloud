package club.codecloud.base.common.dao;

import java.util.List;

public interface BaseDao<T> {


    int add(T t);

    int addBatch(List<T> list);

    int update(T t);

    int updateBySelective(T t);

    int delete(Object id);

    int queryCount(T t);

    List<T> queryList(T t);

    T queryById(Object id);
}
