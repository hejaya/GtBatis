package com.gthree.gtbatis.session;

import java.util.List;

/**
 * @Description 执行curd顶级抽象
 * @Author Gthree
 * @Date 2019-12-24 10:06
 */
public interface SqlSession {

    <T> T selectOne(String statementId, Object parameter);

    <E> List<E> selectList(String statementId, Object parameter);

    void update(String statementId, Object parameter);

    void insert(String statementId, Object parameter);

    <T> T getMapper(Class<T> paramClass);

    Configuration getConfiguration();
}
