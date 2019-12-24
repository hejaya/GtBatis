package com.gthree.gtbatis.executor;

import com.gthree.gtbatis.mapping.MappedStatement;

import java.util.List;

public interface Executor {

    /**
     * 查询操作
     * @param ms
     * @param parameter
     * @param <E>
     * @return
     */
    <E> List<E> doQuery(MappedStatement ms, Object parameter);

    /**
     * 更新操作
     * @param ms
     * @param parameter
     */
    void doUpdate(MappedStatement ms, Object parameter);
}
