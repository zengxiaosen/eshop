package com.z.eshop.service;

import java.util.List;

/**
 * BaseService接口.
 */
public interface BaseService<T> {
    public void saveEntity(T t);

    public void updateEntity(T t);

    public void saveOrUpdateEntity(T t);

    public void deleteEntity(T t);

    public T getEntity(Integer id);

    /* 按照HQL */
    public List<T> findByHQL(String hql, Object... objects);

    public void execHQL(String hql, Object... objects);

    //查询所有实体
    public List<T> findAllEntities();
}
