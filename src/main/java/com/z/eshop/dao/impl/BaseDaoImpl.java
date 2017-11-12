package com.z.eshop.dao.impl;

import com.z.eshop.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *BaseDao接口的实现类
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    //接受T的具体类型
    private Class<T> clazz ;

    /**
     * hibernate会话工厂,相当于连接池，数据源。
     */
    private SessionFactory sf ;

    //设置sf
    @Resource(name="sf")
    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public BaseDaoImpl(){
        //取得子类的泛型化超类
        ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
        //得到第一个实际参数
        clazz = (Class)type.getActualTypeArguments()[0];
    }

    public void saveEntity(T t) {
        sf.getCurrentSession().save(t);
    }

    public void updateEntity(T t) {
        sf.getCurrentSession().update(t);
    }

    public void saveOrUpdateEntity(T t) {
        sf.getCurrentSession().saveOrUpdate(t);
    }

    public void deleteEntity(T t) {
        sf.getCurrentSession().delete(t);
    }
    /*按照id查询对象实体*/
    public T getEntity(Integer id) {
        return (T)sf.getCurrentSession().get(clazz,id);
    }

    /**
     * 按照hql查询数据,绑定多个参数
     */
    public List<T> findByHQL(String hql, Object... objects) {
        Query q = sf.getCurrentSession().createQuery(hql);
        for(int i = 0 ; i < objects.length ; i ++ ){
            q.setParameter(i , objects[i]);
        }
        return q.list();
    }

    /**
     * 按照hql进行批量写操作,绑定多个参数
     */
    public void execHQL(String hql, Object... objects) {
        Query q = sf.getCurrentSession().createQuery(hql);
        for (int i = 0; i < objects.length; i++) {
            q.setParameter(i, objects[i]);
        }
        q.executeUpdate();
    }
}
