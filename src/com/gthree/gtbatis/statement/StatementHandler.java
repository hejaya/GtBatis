package com.gthree.gtbatis.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description sql 处理
 * @Author Gthree
 * @Date 2019-12-24 14:51
 */
public interface StatementHandler {

    PreparedStatement prepare(Connection paramConnection) throws SQLException;

    /**
     * 查询数据库
     * @param preparedStatement
     * @return
     * @throws SQLException
     */
    ResultSet query(PreparedStatement preparedStatement) throws SQLException;

    /**
     * 修改数据库
     * @param preparedStatement
     * @throws SQLException
     */
    void update(PreparedStatement preparedStatement) throws SQLException;
}
