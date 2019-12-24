package com.gthree.gtbatis.parameter;

import java.sql.PreparedStatement;

/**
 * @Description  处理参数
 * @Author Gthree
 * @Date 2019-12-24 15:02
 */
public interface ParameterHandler {
    /**
     * 处理参数
     * @param paramPreparedStatement
     */
    void setParameters(PreparedStatement paramPreparedStatement);
}
