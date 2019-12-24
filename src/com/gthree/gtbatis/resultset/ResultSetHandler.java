package com.gthree.gtbatis.resultset;

import java.sql.ResultSet;
import java.util.List;

/**
 * @Description 处理结果映射
 * @Author Gthree
 * @Date 2019-12-24 15:12
 */
public interface ResultSetHandler {
    /**
     * 处理结果映射
     * @param resultSet
     * @param <E>
     * @return
     */
    <E> List<E> handleResultSets(ResultSet resultSet);
}
