package club.codecloud.base.common.service;

import club.codecloud.base.common.dao.BaseDao;
import club.codecloud.base.common.entity.BaseDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author ulei
 * @date 2018/4/12
 */
public class BaseServiceImpl<T extends BaseDO, D extends BaseDao> implements BaseService<T> {

    @Autowired
    private D dao;

    @Override
    public int insert(T t) {
        return dao.insert(t);
    }

    @Override
    public int deleteById(Serializable id) {
        return dao.deleteById(id);
    }

    @Override
    public int updateById(T t) {
        return dao.updateById(t);
    }

    @Override
    public T selectById(Serializable id) {
        return (T) dao.selectById(id);
    }
}
