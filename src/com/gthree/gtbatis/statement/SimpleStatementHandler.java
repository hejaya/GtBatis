package com.gthree.gtbatis.statement;

import com.gthree.gtbatis.common.CommonUtils;
import com.gthree.gtbatis.mapping.MappedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author Gthree
 * @Date 2019-12-24 14:52
 */
public class SimpleStatementHandler implements StatementHandler {

    private MappedStatement mappedStatement;

    // #{}正则匹配
    private static Pattern param_pattern = Pattern.compile("#\\{([^\\{\\}]*)\\}");

    public SimpleStatementHandler(MappedStatement mappedStatement) {
        this.mappedStatement = mappedStatement;
    }

    @Override
    public PreparedStatement prepare(Connection paramConnection) throws SQLException {
        //得到xml里的SQL语句
        String originalSql = mappedStatement.getSql();
        if (CommonUtils.isNotEmpty(originalSql)) {
            // 替换#{}，预处理，防止SQL注入
            return paramConnection.prepareStatement(parseSymbol(originalSql));
        } else {
            throw new SQLException("SQL语句不能为空！");
        }
    }

    @Override
    public ResultSet query(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }

    @Override
    public void update(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.execute();
    }

    /**
     * 将#{}替换为?
     *
     * @param source
     * @return
     */
    private static String parseSymbol(String source) {
        source = source.trim();
        Matcher matcher = param_pattern.matcher(source);
        return matcher.replaceAll("?");
    }

}
